/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cusc.satlink2s.status;

import com.cusc.satlink2s.data.AntennaTemplate;
import com.cusc.satlink2s.data.Constant.BAND_TYPE;
import java.util.ArrayList;

/**
 *
 * @author maxli
 */
public class Antenna {

    public static class Direction {

        public static double Azimuth;
        public static double Elevation;
        public static double Polarization;
        public static double BowAngle;
    }

    public static class SpecifiedModel {

        public static String model;
        public static double diameter;
        public static BAND_TYPE band;
        public static double efficiency;
        public static double antennaNoise;
        public static double bucPower;
        public static double bucLo;
        public static double lnbLo;
        public static double lnbNoise;
        public static double lnbGain;
        public static double feederLength;

    }

    public static class Capability {

        public static double rxGain;
        public static double txGain;
        public static double GT;
        public static double systemNoiseTemperature;
        public static double rxLevel;
    }

    public static ArrayList<AntennaTemplate> antennasList = new ArrayList();   //保存数据文件天线列表

    public static void specifiedAntenna(int index) {   //根据索引选择天线加载至静态变量

        Antenna.SpecifiedModel.model = antennasList.get(index).getModel();
        Antenna.SpecifiedModel.diameter = antennasList.get(index).getDiameter();
        Antenna.SpecifiedModel.band = antennasList.get(index).getBand();
        Antenna.SpecifiedModel.efficiency = antennasList.get(index).getEfficiency();
        Antenna.SpecifiedModel.bucPower = antennasList.get(index).getBucPower();
        Antenna.SpecifiedModel.bucLo = antennasList.get(index).getBucLo();
        Antenna.SpecifiedModel.lnbLo = antennasList.get(index).getLnbLo();
        Antenna.SpecifiedModel.antennaNoise = antennasList.get(index).getAntennaNoise();
        Antenna.SpecifiedModel.lnbNoise = antennasList.get(index).getLnbNoise();
        Antenna.SpecifiedModel.feederLength = antennasList.get(index).getFeederLength();
        Antenna.SpecifiedModel.lnbGain = antennasList.get(index).getLnbGain();

    }
}
