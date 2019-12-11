/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cusc.satlink2s.status;

import com.cusc.satlink2s.data.Constant.BAND_TYPE;
import com.cusc.satlink2s.data.Constant.EAST_OR_WEST;
import static com.cusc.satlink2s.data.Constant.KILO;
import com.cusc.satlink2s.data.Constant.VERITAL_OR_HORIZONTAL;
import com.cusc.satlink2s.data.SatelliteTemplate;
import com.cusc.satlink2s.data.TransponderTemplate;
import static com.cusc.satlink2s.status.Satellite.eirpsList;
import java.util.ArrayList;

/**
 *
 * @author maxli
 */
public class Satellite {

    public static class SpecifiedSatelliteResource {

        public static String name;
        public static double longtitude;
        public static EAST_OR_WEST eastOrWest;

    }

    public static class SpecifiedTransponderResource {

        //以下需要输入获得
        public static String name;
        public static String imagePath;
        public static BAND_TYPE band;
        public static double bandwidth;
        public static double gt;
        public static double sfd;
        public static VERITAL_OR_HORIZONTAL vOrH;
    }

    public static class SpecifiedEirpValue {

        public static double eirp;
    }

    public static void specifiedSatellite(int index) {

        SpecifiedSatelliteResource.name = satellitesList.get(index).getName();
        SpecifiedSatelliteResource.longtitude = satellitesList.get(index).getLongtitude();
        SpecifiedSatelliteResource.eastOrWest = satellitesList.get(index).getEastOrWest();
        transpondersList.clear();
        transpondersList.addAll(satellitesList.get(index).getTransponders());

    }

    public static void specifiedTransponder(int index) {

        SpecifiedTransponderResource.name = transpondersList.get(index).getName();
        SpecifiedTransponderResource.band = transpondersList.get(index).getBand();
        SpecifiedTransponderResource.bandwidth = transpondersList.get(index).getBandwidth();
        SpecifiedTransponderResource.imagePath = transpondersList.get(index).getImagePath();
        SpecifiedTransponderResource.gt = transpondersList.get(index).getGt();
        SpecifiedTransponderResource.sfd = transpondersList.get(index).getSfd();
        SpecifiedTransponderResource.vOrH = transpondersList.get(index).getRxPolar();

//        for (int i = 0; i < transpondersList.get(index).getEirps().length; i++) {
//            eirpsList[i] = transpondersList.get(index).getEirps()[i];
//        }
        System.arraycopy(transpondersList.get(index).getEirps(), 0, eirpsList, 0, transpondersList.get(index).getEirps().length);

    }

    public static void specifiedEirp(int index) {

        SpecifiedEirpValue.eirp = eirpsList[index];

    }

    public static void specifiedFrequency() {
        Link.UpLink.txFrequency = (Link.UpLink.txLband + Antenna.SpecifiedModel.bucLo) / KILO;
        Link.DownLink.rxFrequency = (Link.DownLink.rxLband + Antenna.SpecifiedModel.lnbLo) / KILO;
    }

    public static ArrayList<SatelliteTemplate> satellitesList = new ArrayList();
    public static ArrayList<TransponderTemplate> transpondersList = new ArrayList();
    public static double eirpsList[] = new double[5];
}
