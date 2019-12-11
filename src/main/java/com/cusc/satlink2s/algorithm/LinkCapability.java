/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cusc.satlink2s.algorithm;

import static com.cusc.satlink2s.algorithm.AntennaCapability.TRACKING_LOSS;
import com.cusc.satlink2s.data.Constant;
import static com.cusc.satlink2s.data.Constant.EAST_OR_WEST.W;
import static com.cusc.satlink2s.data.Constant.KILO;
import com.cusc.satlink2s.status.Antenna;
import com.cusc.satlink2s.status.Carrier;
import com.cusc.satlink2s.status.Link;
import static com.cusc.satlink2s.status.Link.otherLoss;
import static com.cusc.satlink2s.status.Link.weatherLoss;
import com.cusc.satlink2s.status.Satellite;

/**
 *
 * @author maxli
 */
public class LinkCapability {

    public static void distance() {
        if (Carrier.eastOrWest.equals(W) && Carrier.longtitude > 0) { //如果是西经，取负值
            Carrier.longtitude = 0 - Carrier.longtitude;
        }
        if (Satellite.SpecifiedSatelliteResource.eastOrWest.equals(W) && Satellite.SpecifiedSatelliteResource.longtitude > 0) {//如果是西经，取负值
            Satellite.SpecifiedSatelliteResource.longtitude = 0 - Satellite.SpecifiedSatelliteResource.longtitude;
        }
        Link.distance = Math.sqrt(Constant.EARTHRADIUS * Constant.EARTHRADIUS + Constant.ORBITRADIUS * Constant.ORBITRADIUS - 2 * Constant.EARTHRADIUS * Constant.ORBITRADIUS
                * Math.cos((Satellite.SpecifiedSatelliteResource.longtitude - Carrier.longtitude) * Constant.ANGLETURNING) * Math.cos(Carrier.latitude * Constant.ANGLETURNING));
    }

    public static double waveLength(double frequency) {    //根据输入频率计算波长
        double waveLength = Constant.LIGHTSPEED / (frequency * Constant.GIGA);
        return waveLength;
    }

    public static void freeSpaceLoss() {               //计算自由空间损耗
        Link.DownLink.rxWaveLength = waveLength(Link.DownLink.rxFrequency);
        Link.UpLink.txWaveLength = waveLength(Link.UpLink.txFrequency);
        Link.DownLink.rxFreeSpaceLoss = 20 * Math.log10((4 * Math.PI * Link.distance) / Link.DownLink.rxWaveLength);
        Link.UpLink.txFreeSpaceLoss = 20 * Math.log10((4 * Math.PI * Link.distance) / Link.UpLink.txWaveLength);
    }

    public static void rxSfd() {
        Link.DownLink.sfd = Satellite.SpecifiedEirpValue.eirp - 10 * Math.log10(Math.pow(4 * Math.PI * Link.distance, 2));
    }

    public static void transponderBandNoise() {            //计算转发器带宽噪声
        Link.DownLink.transponderBandwidthNoise = 10 * Math.log10(Satellite.SpecifiedTransponderResource.bandwidth * Constant.MEGA);
    }

    public static void txBandNoise() {
        Link.UpLink.txBandNoise = Math.log10(Constant.MEGA * Link.UpLink.txBandWidth) * 10;
    }

    public static void rxCarrierToNoiseRate() {           //计算接收载噪比  EIRP+天线增益-空间损耗-波尔兹曼常熟-转发器噪声温度-系统噪声温度-线损-跟踪损耗-天气损耗-其他损耗
        rxCarrierToTemperature();
        rxCarrierToNoise0Rate();
        Link.DownLink.cn = Link.DownLink.cn0 - Link.DownLink.transponderBandwidthNoise - TRACKING_LOSS - weatherLoss - otherLoss;
    }

    public static void rxCarrierToTemperature() {
        Link.DownLink.ct = Satellite.SpecifiedEirpValue.eirp + Antenna.Capability.GT - Link.DownLink.rxFreeSpaceLoss;
    }

    public static void rxCarrierToNoise0Rate() {
        Link.DownLink.cn0 = Link.DownLink.ct - Constant.BOLTZMANNCONSTANT;
    }

    public static void txCarrierToNoiseRate() {
        txCarrierToNoise0Rate();
        Link.UpLink.cn = Link.UpLink.cn0 - Link.UpLink.txBandNoise - TRACKING_LOSS - weatherLoss - otherLoss;
    }

    public static void txCarrierToNoise0Rate() {
        Link.UpLink.cn0 = Satellite.SpecifiedTransponderResource.gt + Antenna.Capability.txGain + 10 * Math.log10(Antenna.SpecifiedModel.bucPower) - Link.UpLink.txFreeSpaceLoss - Constant.BOLTZMANNCONSTANT;
    }

    public static void totalCarrierToNoiseRate() {
        Link.totalCn = 10 * Math.log10(Math.pow((Math.pow(Math.pow(10, Link.UpLink.cn / 10), -1) + Math.pow(Math.pow(10, Link.DownLink.cn / 10), -1)), -1));
    }

    public static void totalCarrierToNoise0Rate() {
        Link.totalCn0 = 10 * Math.log10(Math.pow((Math.pow(Math.pow(10, Link.UpLink.cn0 / 10), -1) + Math.pow(Math.pow(10, Link.DownLink.cn0 / 10), -1)), -1));
    }

    public static void lBandTxFrequency() {              // 返回GHz
        Link.UpLink.txLband = (Link.UpLink.txFrequency * KILO - Antenna.SpecifiedModel.bucLo);
    }

    public static void lBandRxFrequency() {              // 返回GHz
        Link.DownLink.rxLband = (Link.DownLink.rxFrequency * KILO - Antenna.SpecifiedModel.lnbLo);
    }
}
