/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mitgifdownloader;

import apicalls.Feelings;
import apicalls.MitApi;
import com.google.gson.JsonArray;
import exceptions.Dialog;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import mitgifdownloader.language.Language;
import mitgifdownloader.language.Translation;
import utils.ObservableString;

/**
 *
 * @author ina
 */
public class MitGifGifDownloader extends Application {

    public static ObservableString status;
    Label directory;
    File selectedDirectory;
    public static Label statusLabel;
    @Override
    public void start(Stage primaryStage) {
        Language.setLang(Language.ESP);
        StackPane root = new StackPane();
        Label directoryLabel = new Label(Language.getString(Language.lang, Translation.Strings.DIRECTORY));
        statusLabel = new Label(Language.getString(Language.lang, Translation.Strings.STATUS));
        status = new ObservableString();
        status.setValue(Language.getString(Language.lang, Translation.Strings.STATUS));
        statusLabel.textProperty().bind(status.valueProperty());
        directory = new Label("C:\\");
        Button btn = createSelectButton(primaryStage);
        Button btnDownload = createDownloadButton();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        GridPane.setConstraints(directoryLabel, 0, 0);
        grid.getChildren().add(directoryLabel);
        GridPane.setConstraints(directory, 1, 0);
        grid.getChildren().add(directory);
        GridPane.setConstraints(btn, 0, 1);
        grid.getChildren().add(btn);
        GridPane.setConstraints(btnDownload, 1, 1);
        grid.getChildren().add(btnDownload);
        GridPane.setConstraints(statusLabel, 0, 2);
        grid.getChildren().add(statusLabel);
        root.getChildren().add(grid);
        Scene scene = new Scene(root, 500, 75);
        primaryStage.setTitle(Language.getString(Language.lang, Translation.Strings.APP_TITLE));
        primaryStage.setScene(scene); 
        primaryStage.show(); 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private Button createSelectButton(Stage primaryStage) {
        Button btn = new Button();
        btn.setText(Language.getString(Language.lang, Translation.Strings.SELECT_FOLDER));
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                DirectoryChooser chooser = new DirectoryChooser();
                chooser.setTitle(Language.getString(Language.lang, Translation.Strings.SELECT_FOLDER));
                File defaultDirectory = new File("c:/");
                chooser.setInitialDirectory(defaultDirectory);
                selectedDirectory = chooser.showDialog(primaryStage);
                directory.setText(selectedDirectory.getAbsolutePath());
            }
        });
        return btn;
    }

    private Button createDownloadButton() {
        Button btn = new Button();
        btn.setText(Language.getString(Language.lang, Translation.Strings.DOWNLOAD));
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                updateMessage(Language.getString(Language.lang, Translation.Strings.STATUS)
                        + Language.getString(Language.lang, Translation.Strings.START));
                downloadButonAction();
            }

        });
        return btn;
    }
    public Feelings.Feel feeling;

    private void downloadButonAction() {
        for (Feelings.Feel feel : Feelings.Feel.values()) {
            updateMessage(Language.getString(Language.lang, Translation.Strings.STATUS)
                    + Language.getString(Language.lang, Translation.Strings.DOWNLOADING) + " "
                    + Translation.getStringFromFeelings(Language.lang, feel));
            try {
                downloadMetaData(feel);
            } catch (IOException ex) {
                Dialog.showExceptionMessage(ex);
                Logger.getLogger(MitGifGifDownloader.class.getName()).log(Level.SEVERE, null, ex);
            }

            updateMessage(Language.getString(Language.lang, Translation.Strings.STATUS)
                    + Language.getString(Language.lang, Translation.Strings.FINISH));
        }
    }

    private void downloadMetaData(Feelings.Feel feel) throws IOException {
        Converter converter = new Converter(directory.getText());
        JsonArray results;
        MitApi apicalls = new MitApi();
        apicalls.count = 0;
        int total = 0;
        do {
            total = Integer.parseInt(MitApi.LIMIT) + apicalls.count;
            results = apicalls.getJsonFrom(apicalls.getQuery(apicalls.getIdFromEnum(feel)));
            for (int i = 0; i < results.size(); i++) {
                converter.convertJsonToPojo(results.get(i).getAsJsonObject(), feel);
            }
            converter.writeToFile(feel, String.valueOf(results.size() + apicalls.count));
            apicalls.count += results.size();
        } while (apicalls.count == total);
    }

    public static void updateMessage(final String string) {
        System.out.println(string);
        if (1 == 1) {
            return;
        }
        Task task;
        task = new Task<Void>() {
            @Override
            public Void call() {
                status.setValue(string);

                statusLabel.textProperty()
                        .bind(status.valueProperty());
                return null;
            }
        };
        new Thread(task).start();
    }
}
