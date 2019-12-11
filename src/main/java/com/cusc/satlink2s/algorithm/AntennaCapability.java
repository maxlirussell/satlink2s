/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cusc.satlink2s.algorithm;

import static com.cusc.satlink2s.algorithm.LinkCapability.waveLength;
import com.cusc.satlink2s.data.Constant;
import static com.cusc.satlink2s.data.Constant.FEEDERLOSS_100METERS;
import com.cusc.satlink2s.status.Antenna;
import com.cusc.satlink2s.status.Link;
import com.cusc.satlink2s.status.Satellite;

/**
 *
 * @author maxli
 */
public class AntennaCapability {

    public static double TRACKING_LOSS = 0.3;    //跟踪误差损耗
    public static double BEAMWIDTH = 1.4;     //主波束宽度

    public static double antennaGain(double waveLength) {      //根据频率计算天线增益
        double gain = 10 * Math.log10(Math.pow(Math.PI, 2) * Math.pow(Antenna.SpecifiedModel.diameter, 2) * Antenna.SpecifiedModel.efficiency / (Math.pow(waveLength, 2)));
        return gain;
    }

    public static void antennaGain() {
        Antenna.Capability.rxGain = antennaGain(waveLength(Link.DownLink.rxFrequency));
        Antenna.Capability.txGain = antennaGain(waveLength(Link.UpLink.txFrequency));
    }

    public static void systemNoiseTemperature() {           //计算系统噪声
        Antenna.Capability.systemNoiseTemperature = 10 * Math.log10(Antenna.SpecifiedModel.antennaNoise + Antenna.SpecifiedModel.lnbNoise);
    }

    public static void gToTRate() {
        Antenna.Capability.GT = Antenna.Capability.rxGain - Antenna.Capability.systemNoiseTemperature;
    }

    public static void rxLevel() {
        Antenna.Capability.rxLevel = Satellite.SpecifiedEirpValue.eirp + Antenna.Capability.rxGain - Link.DownLink.rxFreeSpaceLoss //+ 10 * Math.log10(KILO)
                + Antenna.SpecifiedModel.lnbGain - FEEDERLOSS_100METERS * Antenna.SpecifiedModel.feederLength / 100;
    }

    public static void turningNoiseCoiefficient2Temprature(double coiefficient) {
        Antenna.SpecifiedModel.lnbNoise = (Math.pow(10, coiefficient / 10) - 1) * Constant.ABSOLUTE_TEMPERATURE;
    }
}
