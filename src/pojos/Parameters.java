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
public class Parameters {
@SerializedName("sigma")
    private Double sigma;
@SerializedName("mu")
    private Double mu;
@SerializedName("distance")
    private Double distance;


    public Parameters() {
    }

    public Double getSigma() {
        return sigma;
    }

    public Double getMu() {
        return mu;
    }

    public void setSigma(Double sigma) {
        this.sigma = sigma;
    }

    public void setMu(Double mu) {
        this.mu = mu;
    } 

    public Double getDistance() {
        return distance;
    }
    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
