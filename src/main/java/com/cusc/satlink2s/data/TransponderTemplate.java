/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cusc.satlink2s.data;

import com.cusc.satlink2s.data.Constant.VERITAL_OR_HORIZONTAL;

/**
 *
 * @author maxli
 */
public class TransponderTemplate {

//以下需要输入获得
    private String name;
    private String imagePath;
    private double eirps[];
    private Constant.BAND_TYPE band;
    private VERITAL_OR_HORIZONTAL rxPolar;
    private double bandwidth;
    private double gt;
    private double sfd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double[] getEirps() {
        return eirps;
    }

    public void setEirps(double[] eirps) {
        this.eirps = eirps;
    }

    public Constant.BAND_TYPE getBand() {
        return band;
    }

    public void setBand(Constant.BAND_TYPE band) {
        this.band = band;
    }

    public VERITAL_OR_HORIZONTAL getRxPolar() {
        return rxPolar;
    }

    public void setRxPolar(VERITAL_OR_HORIZONTAL rxPolar) {
        this.rxPolar = rxPolar;
    }

    public double getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(double bandwidth) {
        this.bandwidth = bandwidth;
    }

    public double getGt() {
        return gt;
    }

    public void setGt(double gt) {
        this.gt = gt;
    }

    public double getSfd() {
        return sfd;
    }

    public void setSfd(double sfd) {
        this.sfd = sfd;
    }

}
