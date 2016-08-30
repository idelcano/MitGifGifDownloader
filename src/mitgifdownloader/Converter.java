/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mitgifdownloader;

import apicalls.Feelings.Feel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import exceptions.Dialog;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import mitgifdownloader.language.Language;
import mitgifdownloader.language.Translation;
import pojos.ContentData;
import pojos.GifMetadata;

/**
 *
 * @author ina
 */
public class Converter {

    private final String IMAGES_FOLDER = "images";
    private final String GIF_FOLDER = "imagesgif";

    private ArrayList<GifMetadata> gifs;
    private String folder;

    public Converter(String folder) {
        this.folder = folder;
        gifs = new ArrayList<>();
    }

    Converter() {
    }

    public ArrayList<GifMetadata> getGifs() {
        return gifs;
    }

    public void setGifs(ArrayList<GifMetadata> gifs) {
        this.gifs = gifs;
    }

    public void addGif(GifMetadata gif) {
        if (this.gifs == null) {
            this.gifs = new ArrayList<>();
        }
        this.gifs.add(gif);
    }

    public void removeGif(GifMetadata gif) {
        if (this.gifs == null) {
            return;
        }
        this.gifs.remove(gif);
    }

    public GifMetadata convertJsonToPojo(JsonObject jsonMain, Feel feel) {
        Gson gson = new Gson();
        GifMetadata gif = gson.fromJson(jsonMain.toString(), GifMetadata.class);
        gif.setFeel(feel);
        addGif(gif);
        return gif;
    }

    void writeToFile(Feel feel, String page) {
        if (gifs == null) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            System.out.println("Writting: " + Translation.getStringFromFeelings(Language.lang, feel) + page);
            System.out.println(gifs.size());
            Writer writer;
            try {
                PrintWriter oprint;
                writer = new FileWriter(folder + "/" + Translation.getStringFromFeelings(Language.lang, feel) + page + ".json");
                oprint = new PrintWriter(writer);
                Gson gson = new GsonBuilder().create();
                String cadnea = gson.toJson(gifs);
                oprint.println(cadnea);//<- print with new line sign
                oprint.close();
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                downloadAndSaveImages(gifs);
            } catch (IOException ex) {
                if (!ex.getMessage().contains("403")) {
                    Dialog.showExceptionMessage(ex);
                    ex.printStackTrace();
                    Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            gifs.clear();
            gifs = new ArrayList<>();
        }

    }

    private void downloadAndSaveImages(ArrayList<GifMetadata> gifs) throws IOException {
        File imagesFolder = new File(folder + "/" + IMAGES_FOLDER);
        if (!imagesFolder.exists()) {
            imagesFolder.mkdir();
        }
        File gifFolder = new File(folder + "/" + GIF_FOLDER);
        if (!gifFolder.exists()) {
            gifFolder.mkdir();
        }
        int count = 0;
        for (GifMetadata gif : gifs) {
            count++;
            MitGifGifDownloader.updateMessage(Language.getString(Language.lang, Translation.Strings.STATUS)
                    + Language.getString(Language.lang, Translation.Strings.CONVERTING) + " " + count
                    + Language.getString(Language.lang, Translation.Strings.OF) + " " + gifs.size()
                    + Language.getString(Language.lang, Translation.Strings.OF) + " "
                    + Translation.getStringFromFeelings(Language.lang, gif.getFeel()));
            ContentData content = gif.getContent_data();
            saveImage(content.getStill_image(), imagesFolder, gif.getContent() + ".jpg");
            saveImage(content.getEmbedLink(), gifFolder, gif.getContent() + ".gif");
        }
    }

    private void saveImage(String link, File folder, String name) throws MalformedURLException, IOException {
        File file = new File(folder.getAbsolutePath() + "\\" + name);
        try {
            if (!file.exists()) {
                Image image = null;
                URL url = new URL(link);
                InputStream in = new BufferedInputStream(url.openStream());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int n = 0;
                while (-1 != (n = in.read(buf))) {
                    out.write(buf, 0, n);
                }
                out.close();
                in.close();
                byte[] response = out.toByteArray();
                FileOutputStream fos = new FileOutputStream(folder.getAbsolutePath() + "\\" + name);
                fos.write(response);
                fos.close();
            }
        } catch (IOException ex) {
            System.out.println("ERRORRR  Writing file: " + folder.getAbsolutePath() + "\\" + name);
            if (!ex.getMessage().contains("403") && !ex.getMessage().contains("500") && !ex.getMessage().contains("Connection timed out")) {
                Dialog.showExceptionMessage(ex);
                ex.printStackTrace();
                Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
                throw new NoSuchMethodError();
            }
        }
    }
}
