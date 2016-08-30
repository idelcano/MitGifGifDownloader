/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language;

import apicalls.Feelings;

/**
 *
 * @author ina
 */
public class Translation {

    public enum Strings {
        STATUS, DOWNLOAD, DOWNLOADING, SELECT_FOLDER, FINISH, DIRECTORY, APP_TITLE, START, PROCESSING,
        HAPPINESS, SADNESS, AMUSEMENT, ANGER, CONTEMPT, CONTENTMENT, DISGUST,
        EMBARRASSMENT, EXCITEMENT, FEAR, GUILT, PLEASURE, PRIDE, RELIEF, SATISFACTION, CONVERTING, OF,SAVING,CANCEL,EXIT;

    }

    public static String getStringFromEnum(int lang, Strings stringEnum) {
        switch (stringEnum) {
            case STATUS:
                switch (lang) {
                    case Language.ESP:
                        return "Estado: ";
                    case Language.ENG:
                        return "Status: ";
                }
            case DOWNLOAD:
                switch (lang) {
                    case Language.ESP:
                        return "Descargar";
                    case Language.ENG:
                        return "Download";
                }
            case DOWNLOADING:
                switch (lang) {
                    case Language.ESP:
                        return "Descargando...";
                    case Language.ENG:
                        return "Downloading...";
                }
            case SELECT_FOLDER:
                switch (lang) {
                    case Language.ESP:
                        return "Selecciona carpeta";
                    case Language.ENG:
                        return "Select folder";
                }
            case FINISH:
                switch (lang) {
                    case Language.ESP:
                        return "Proceso finalizado";
                    case Language.ENG:
                        return "Finished";
                }
            case DIRECTORY:
                switch (lang) {
                    case Language.ESP:
                        return "Directorio: ";
                    case Language.ENG:
                        return "Folder: ";
                }
            case APP_TITLE:
                switch (lang) {
                    case Language.ESP:
                        return "Mit Gif Downloader";
                    case Language.ENG:
                        return "Descarga Mit Gif";
                }
            case START:
                switch (lang) {
                    case Language.ESP:
                        return " Empezando proceso";
                    case Language.ENG:
                        return " Starting process";
                }
            case PROCESSING:
                switch (lang) {
                    case Language.ESP:
                        return " Procesando...";
                    case Language.ENG:
                        return "Processing";
                }
            case CONVERTING:
                switch (lang) {
                    case Language.ESP:
                        return " convirtiendo ";
                    case Language.ENG:
                        return " converting ";
                }
            case OF:
                switch (lang) {
                    case Language.ESP:
                        return " de ";
                    case Language.ENG:
                        return " of ";
                }
            case SAVING:
                switch (lang) {
                    case Language.ESP:
                        return " guardando ";
                    case Language.ENG:
                        return " saving ";
                }
            case CANCEL:
                switch (lang) {
                    case Language.ESP:
                        return "Cancelar";
                    case Language.ENG:
                        return "Cancel";
                }
            case EXIT:
                switch (lang) {
                    case Language.ESP:
                        return "Salir";
                    case Language.ENG:
                        return "Exit";
                }
                
                
        }
        return "";
    }
    
    
        public static String getStringFromFeelings(int lang, Feelings.Feel feel) {
            switch (feel) {
                case HAPPINESS:
                    switch (lang) {
                        case Language.ENG:
                            return "Happiness";
                        case Language.ESP:
                            return "Felicidad";
                    }
                case SADNESS:
                    switch (lang) {
                        case Language.ENG:
                            return "Saddness";
                        case Language.ESP:
                            return "Tristeza";
                    }
                case AMUSEMENT:
                    switch (lang) {
                        case Language.ENG:
                            return "Amusement";
                        case Language.ESP:
                            return "Diversión";
                    }
                case ANGER:
                    switch (lang) {
                        case Language.ENG:
                            return "Anger";
                        case Language.ESP:
                            return "Enfado";
                    }
                case PRIDE:
                    switch (lang) {
                        case Language.ENG:
                            return "Pride";
                        case Language.ESP:
                            return "Orgullo";
                    }
                case SATISFACTION:
                    switch (lang) {
                        case Language.ENG:
                            return "Satisfaction";
                        case Language.ESP:
                            return "Satisfacción";
                    }
                case RELIEF:
                    switch (lang) {
                        case Language.ENG:
                            return "Relief";
                        case Language.ESP:
                            return "Alivio";
                    }
                case PLEASURE:
                    switch (lang) {
                        case Language.ENG:
                            return "Pleasure";
                        case Language.ESP:
                            return "Placer";
                    }
                case GUILT:
                    switch (lang) {
                        case Language.ENG:
                            return "Guilt";
                        case Language.ESP:
                            return "Culpa";
                    }
                case FEAR:
                    switch (lang) {
                        case Language.ENG:
                            return "Fear";
                        case Language.ESP:
                            return "Miedo";
                    }
                case EXCITEMENT:
                    switch (lang) {
                        case Language.ENG:
                            return "Excitement";
                        case Language.ESP:
                            return "Emoción";
                    }
                case EMBARRASSMENT:
                    switch (lang) {
                        case Language.ENG:
                            return "Embarrassment";
                        case Language.ESP:
                            return "Verguenza";
                    }
                case DISGUST:
                    switch (lang) {
                        case Language.ENG:
                            return "Disgust";
                        case Language.ESP:
                            return "Asco";
                    }
                case CONTENTMENT:
                    switch (lang) {
                        case Language.ENG:
                            return "Contentment";
                        case Language.ESP:
                            return "Alegria";
                    }
                case CONTEMPT:
                    switch (lang) {
                        case Language.ENG:
                            return "Contempt";
                        case Language.ESP:
                            return "Desprecio";
                    }
            }
            return "";
        }
}
