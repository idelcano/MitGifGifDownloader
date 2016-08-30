/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import apicalls.Feelings;
import apicalls.Feelings.Feel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Objects; 
import language.Language;
import language.Translation; 

/**
 *
 * @author ina
 */
public class GifMetadata {

    @SerializedName("rank")
    private int rank;
    @SerializedName("content_type")
    private String content_type;
    @SerializedName("cID")
    private String cID;
    @SerializedName("parameters")
    private Parameters parameters;
    @SerializedName("content")
    private String content;
    @SerializedName("content_data")
    private ContentData content_data;
    @SerializedName("index")
    private int index;

    private Feel feel;

    public GifMetadata() {
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public String getcID() {
        return cID;
    }

    public void setcID(String cID) {
        this.cID = cID;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ContentData getContent_data() {
        return content_data;
    }

    public void Content_data(ContentData content_data) {
        this.content_data = content_data;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Feel getFeel() {
        return feel;
    }

    public void setFeel(Feel feel) {
        this.feel = feel;
    }

    public String getFeelString() {
        return Translation.getStringFromFeelings(Language.lang, feel);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.content_type);
        hash = 79 * hash + Objects.hashCode(this.cID);
        hash = 79 * hash + Objects.hashCode(this.parameters);
        hash = 79 * hash + Objects.hashCode(this.content);
        hash = 79 * hash + Objects.hashCode(this.content_data);
        hash = 79 * hash + this.index;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GifMetadata other = (GifMetadata) obj;
        if (this.index != other.index) {
            return false;
        }
        if (!Objects.equals(this.content_type, other.content_type)) {
            return false;
        }
        if (!Objects.equals(this.cID, other.cID)) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (!Objects.equals(this.parameters, other.parameters)) {
            return false;
        }
        if (!Objects.equals(this.content_data, other.content_data)) {
            return false;
        }
        return true;
    }
}
