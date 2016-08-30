/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author ina
 */
class Tag {

    @SerializedName("tag")
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Tag() {
    }

}
