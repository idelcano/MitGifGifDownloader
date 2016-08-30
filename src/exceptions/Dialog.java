/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import apicalls.Feelings;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mitgifdownloader.MitGifDownloader;
import mitgifdownloader.language.Language;
import mitgifdownloader.language.Translation;

/**
 *
 * @author ina
 */
public class Dialog { 
    public static void showExceptionMessage(Exception e) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Button btn= new Button(Language.getString(Language.lang, Translation.Strings.EXIT));
        btn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) { 
                    dialogStage.close();
                }

        });
        dialogStage.setScene(new Scene(VBoxBuilder.create().
                children(new Text(e.getLocalizedMessage()), btn).
                alignment(Pos.CENTER).padding(new Insets(5)).build()));
        dialogStage.show();
    }

    public static void showMessage(String cadena) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Button btn= new Button(Language.getString(Language.lang, Translation.Strings.EXIT));
        btn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) { 
                    dialogStage.close();
                }

        });
        dialogStage.setScene(new Scene(VBoxBuilder.create().
                children(new Text(cadena), btn).
                alignment(Pos.CENTER).padding(new Insets(5)).build()));
        dialogStage.show();//To change body of generated methods, choose Tools | Templates.
    }
}
