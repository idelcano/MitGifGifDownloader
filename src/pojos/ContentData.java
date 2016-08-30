/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 *
 * @author ina
 */
public class ContentData {

@SerializedName("dataAdded")
    private Float dataAdded;
@SerializedName("embedLink")
    private String embedLink;
@SerializedName("still_image")
    private String still_image;
@Expose
    private List<String> tags;
@SerializedName("added_with_admin")
    private boolean added_with_admin;

    public ContentData() {
    }

    public Float getDataAdded() {
        return dataAdded;
    }

    public void setDataAdded(Float dataAdded) {
        this.dataAdded = dataAdded;
    }

    public String getEmbedLink() {
        return embedLink;
    }

    public void setEmbedLink(String embedLink) {
        this.embedLink = embedLink;
    }

    public String getStill_image() {
        return still_image;
    }

    public void setStill_image(String still_image) {
        this.still_image = still_image;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public boolean isAdded_with_admin() {
        return added_with_admin;
    }

    public void setAdded_with_admin(boolean added_with_admin) {
        this.added_with_admin = added_with_admin;
    }

}
