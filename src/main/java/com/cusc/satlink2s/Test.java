/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cusc.satlink2s;

import static com.cusc.satlink2s.algorithm.AntennaCapability.antennaGain;
import static com.cusc.satlink2s.algorithm.AntennaCapability.gToTRate;
import static com.cusc.satlink2s.algorithm.AntennaCapability.rxLevel;
import static com.cusc.satlink2s.algorithm.AntennaCapability.systemNoiseTemperature;
import static com.cusc.satlink2s.algorithm.AntennaCapability.turningNoiseCoiefficient2Temprature;
import static com.cusc.satlink2s.algorithm.AntennaDirection.buildTrackingAngles;
import static com.cusc.satlink2s.algorithm.InformatinoRate.ebToN0;
import static com.cusc.satlink2s.algorithm.InformatinoRate.theoreticalRate;
import static com.cusc.satlink2s.algorithm.LinkCapability.distance;
import static com.cusc.satlink2s.algorithm.LinkCapability.freeSpaceLoss;
import static com.cusc.satlink2s.algorithm.LinkCapability.lBandRxFrequency;
import static com.cusc.satlink2s.algorithm.LinkCapability.lBandTxFrequency;
import static com.cusc.satlink2s.algorithm.LinkCapability.rxCarrierToNoiseRate;
import static com.cusc.satlink2s.algorithm.LinkCapability.rxSfd;
import static com.cusc.satlink2s.algorithm.LinkCapability.totalCarrierToNoise0Rate;
import static com.cusc.satlink2s.algorithm.LinkCapability.totalCarrierToNoiseRate;
import static com.cusc.satlink2s.algorithm.LinkCapability.transponderBandNoise;
import static com.cusc.satlink2s.algorithm.LinkCapability.txBandNoise;
import static com.cusc.satlink2s.algorithm.LinkCapability.txCarrierToNoiseRate;
import static com.cusc.satlink2s.data.Constant.EAST_OR_WEST.E;
import static com.cusc.satlink2s.data.Constant.KILO;
import static com.cusc.satlink2s.data.Constant.LEFT_OR_RIGHT.L;
import static com.cusc.satlink2s.data.Constant.NORTH_OR_SOUTH.N;
import static com.cusc.satlink2s.data.Converter.formatDouble;
import com.cusc.satlink2s.data.JsonReader;
import com.cusc.satlink2s.status.Antenna;
import com.cusc.satlink2s.status.Carrier;
import com.cusc.satlink2s.status.Information;
import com.cusc.satlink2s.status.Link;
import com.cusc.satlink2s.status.Satellite;
import java.text.DecimalFormat;

/**
 *
 * @author maxli
 */
public class Test {

    public static void setCarrier() {
        Carrier.latitude = 36.05;
        Carrier.northOrSouth = N;
        Carrier.longtitude = 121.27;
        Carrier.eastOrWest = E;
        Carrier.heading = 47.7;
        Carrier.minOcclusion = 155.0;
        Carrier.maxOcclusion = 170.0;
    }

    public static void getAntennaCapability() {
        antennaGain();
        systemNoiseTemperature();
        gToTRate();

    }

    public static void getLinkCapability() {

        Link.weatherLoss = 0.2;
        Link.otherLoss = 0.1;
        lBandTxFrequency();
        lBandRxFrequency();
        distance();
        freeSpaceLoss();
        transponderBandNoise();
        rxSfd();
        rxCarrierToNoiseRate();

        txBandNoise();
        txCarrierToNoiseRate();
        totalCarrierToNoiseRate();
        totalCarrierToNoise0Rate();
        rxLevel();
    }

    public static void getInformationCapability() {
        ebToN0();
        theoreticalRate();
    }

    public static void loadData() throws Exception { //加载卫星和天线数据文件
        String satelliteDataPath = JsonReader.FILEPATH + "satellite_data";
        String antennaDataPath = JsonReader.FILEPATH + "antenna_data";
        JsonReader.loadSatelliteList(satelliteDataPath);
        JsonReader.loadAntennaList(antennaDataPath);
    }

    public static void test() {
//
//        JsonReader.FILEPATH = "src/main/resources/";
//        loadData();

        setCarrier();

        Link.UpLink.txLband = 1033;
        Link.DownLink.rxLband = 1108;
        Link.UpLink.txBandWidth = 1;
        Link.DownLink.rxBandWidth = 1;

        Antenna.specifiedAntenna(0);
        Satellite.specifiedSatellite(0);
        Satellite.specifiedTransponder(0);
        Satellite.specifiedEirp(1);
        Satellite.specifiedFrequency();

        getAntennaCapability();
        getLinkCapability();
        getInformationCapability();
        buildTrackingAngles();

        linkCapabilityDisplay();
        trackAnglesDisplay();
    }

    public static void linkCapabilityDisplay() {

        System.out.println("----------卫星指标----------");
        System.out.println(Satellite.SpecifiedSatelliteResource.name + Satellite.SpecifiedTransponderResource.name + "波束");
        System.out.println("卫星经度    : " + Satellite.SpecifiedSatelliteResource.longtitude + Satellite.SpecifiedSatelliteResource.eastOrWest);
        System.out.println("EIRP场强    : " + Satellite.SpecifiedEirpValue.eirp + " dBw");
        System.out.println("G/T         : " + Satellite.SpecifiedTransponderResource.gt + " dB/K");
        System.out.println("Tx频点      : " + formatDouble(Link.UpLink.txFrequency) + " GHz");
        System.out.println("Rx频点      : " + formatDouble(Link.DownLink.rxFrequency) + " GHz");
        System.out.println("L-Band-Tx   : " + formatDouble(Link.UpLink.txLband) + " MHz");
        System.out.println("L-Band-Rx   : " + formatDouble(Link.DownLink.rxLband) + " MHz");

        System.out.println("----------天线指标----------");
        System.out.println("天线型号     : " + Antenna.SpecifiedModel.model);
        System.out.println("天线接收增益 : " + formatDouble(Antenna.Capability.rxGain) + " dBi");
        System.out.println("天线发射增益 : " + formatDouble(Antenna.Capability.txGain) + " dBi");
        System.out.println("系统噪声温度 : " + formatDouble(Antenna.Capability.systemNoiseTemperature) + " dB");
        System.out.println("天线系统 G/T : " + formatDouble(Antenna.Capability.GT) + " dB/K");
        System.out.println("接收参考电平 : " + formatDouble(Antenna.Capability.rxLevel) + " dBm");

        System.out.println("----------链路指标----------");
        System.out.println("星站间距离   : " + new DecimalFormat("#.000").format(Link.distance / KILO) + " Km");
        System.out.println("rx自由空间损耗 : " + formatDouble(Link.DownLink.rxFreeSpaceLoss) + " dB");
        System.out.println("tx自由空间损耗 : " + formatDouble(Link.UpLink.txFreeSpaceLoss) + " dB");
        System.out.println("Rx  C/N     : " + formatDouble(Link.DownLink.cn) + " dB");
        System.out.println("Rx  C/N0    : " + formatDouble(Link.DownLink.cn0) + " dB");
        System.out.println("Rx  C/T     : " + formatDouble(Link.DownLink.ct) + " dB");
        System.out.println("Tx  C/N     : " + formatDouble(Link.UpLink.cn) + " dB");
        System.out.println("Tx  C/N0    : " + formatDouble(Link.UpLink.cn0) + " dB");
        System.out.println("Total C/N   : " + formatDouble(Link.totalCn) + " dB");
        System.out.println("Total C/N0  : " + formatDouble(Link.totalCn0) + " dB");

        System.out.println("----------信息速率----------");
        System.out.println("Rx  Eb/N0   : " + formatDouble(Information.ebToN0) + " dB");
        System.out.println("Rx 理论速率  : " + formatDouble(Information.theoreticalRate) + " Mbit/s");
        turningNoiseCoiefficient2Temprature(0.8);   //转换lnb温度

    }

    public static void trackAnglesDisplay() {

        String lr;
        String oc = "无遮挡";
        if (Carrier.leftOrRight.equals(L)) {
            lr = "左舷";
        } else {
            lr = "右舷";
        }
        if (Antenna.Direction.BowAngle >= Carrier.minOcclusion && Antenna.Direction.BowAngle <= Carrier.maxOcclusion) {
            oc = "遮挡";
        }
        System.out.println("----------天线指向----------");
        System.out.println("方位角:" + formatDouble(Antenna.Direction.Azimuth));
        System.out.println("俯仰角:" + formatDouble(Antenna.Direction.Elevation));
        System.out.println("极化角:" + formatDouble(Antenna.Direction.Polarization));
        System.out.println("天线指向" + lr + "相对船艏" + formatDouble(Antenna.Direction.BowAngle) + "度方向,处于" + oc + "区域内");

    }
}
