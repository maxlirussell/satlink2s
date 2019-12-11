/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cusc.satlink2s.status;

/**
 *
 * @author maxli
 */
public class Link {

    public static class UpLink {

        public static double txFrequency;
        public static double txBandWidth;
        public static double txWaveLength;
        public static double txLband;
        public static double txFreeSpaceLoss;
        public static double cn;
        public static double cn0;
        public static double txBandNoise;
    }

    public static class DownLink {

        public static double rxFrequency;
        public static double rxBandWidth;
        public static double rxWaveLength;
        public static double rxLband;
        public static double rxFreeSpaceLoss;
        public static double rxLevel;
        public static double sfd;
        public static double cn;
        public static double ct;
        public static double cn0;
        public static double transponderBandwidthNoise;

    }

    public static double totalCn;
    public static double totalCn0;
    public static double distance;
    public static double weatherLoss;
    public static double otherLoss;
}
