/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cusc.satlink2s.data;

import java.util.ArrayList;

/**
 *
 * @author maxli
 */
public class SatelliteTemplate {

    private String name;
    private double longtitude;
    private Constant.EAST_OR_WEST eastOrWest;
    private ArrayList<TransponderTemplate> transponders;

    public SatelliteTemplate() {
        this.transponders = new ArrayList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public Constant.EAST_OR_WEST getEastOrWest() {
        return eastOrWest;
    }

    public void setEastOrWest(Constant.EAST_OR_WEST eastOrWest) {
        this.eastOrWest = eastOrWest;
    }

    public void addTransponder(TransponderTemplate tt) {
        this.transponders.add(tt);
    }

    public ArrayList<TransponderTemplate> getTransponders() {
        return this.transponders;
    }
}
