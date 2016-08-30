/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author ina
 */
public class ObservableString{ 

    private SimpleStringProperty value = new SimpleStringProperty(this, "value");

    public ObservableString() {
        value.set("Status");
    }

    public String getValue(){
        return value.get();
    }

    public void setValue(String value){
        this.value.set(value);
    }

    public SimpleStringProperty valueProperty(){
        return value;
    }
}
