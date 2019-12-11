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
public class Constant {

    public final static double EARTHRADIUS = 6378000.0;
    public final static double LIGHTSPEED = 299792458.0;
    public final static double ORBITRADIUS = 42218000.0;
    public final static double ANGLETURNING = Math.PI / 180;    //角度转弧度系数
    public final static double ABSOLUTE_TEMPERATURE = 290.0;  //绝对温度
    public final static double BOLTZMANNCONSTANT = 10 * Math.log10(1.38 * Math.pow(10, -23));    //波尔兹曼常数
    public final static double GIGA = 1000000000.0;   //GHz转Hz
    public final static double MEGA = 1000000.0;      //MHz转Hz
    public final static double KILO = 1000.0;      //GHz转MHz
    public final static double FEEDERLOSS_100METERS = 23.0;

    public static enum EAST_OR_WEST {
        E, W
    }

    public static enum NORTH_OR_SOUTH {
        N, S
    }

    public static enum LEFT_OR_RIGHT {    //左右
        L, R
    }

    public static enum BAND_TYPE {
        Ku, C
    }

    public static enum VERITAL_OR_HORIZONTAL {
        V, H
    }

    public static enum LINE_OR_CIRCULAR {
        L, C
    }

}
