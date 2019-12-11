/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cusc.satlink2s.data;

/**
 *
 * @author maxli
 */
public class AntennaTemplate {

    private String model;
    private double diameter;
    private Constant.BAND_TYPE band;
    private double efficiency;
    private double antennaNoise;
    private double bucPower;
    private double bucLo;
    private double lnbLo;
    private double lnbGain;
    private double lnbNoise;
    private double feederLength;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public Constant.BAND_TYPE getBand() {
        return band;
    }

    public void setBand(Constant.BAND_TYPE band) {
        this.band = band;
    }

    public double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(double efficiency) {
        this.efficiency = efficiency;
    }

    public double getAntennaNoise() {
        return antennaNoise;
    }

    public void setAntennaNoise(double antennaNoise) {
        this.antennaNoise = antennaNoise;
    }

    public double getBucPower() {
        return bucPower;
    }

    public void setBucPower(double bucPower) {
        this.bucPower = bucPower;
    }

    public double getBucLo() {
        return bucLo;
    }

    public void setBucLo(double bucLo) {
        this.bucLo = bucLo;
    }

    public double getLnbLo() {
        return lnbLo;
    }

    public void setLnbLo(double lnbLo) {
        this.lnbLo = lnbLo;
    }

    public double getLnbNoise() {
        return lnbNoise;
    }

    public void setLnbNoise(double lnbNoise) {
        this.lnbNoise = lnbNoise;
    }

    public double getLnbGain() {
        return lnbGain;
    }

    public void setLnbGain(double lnbGain) {
        this.lnbGain = lnbGain;
    }

    public double getFeederLength() {
        return feederLength;
    }

    public void setFeederLength(double feederLength) {
        this.feederLength = feederLength;
    }

}
