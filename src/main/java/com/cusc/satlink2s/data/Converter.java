/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cusc.satlink2s.data;

import static com.cusc.satlink2s.data.Constant.BAND_TYPE.C;
import static com.cusc.satlink2s.data.Constant.BAND_TYPE.Ku;
import static com.cusc.satlink2s.data.Constant.EAST_OR_WEST.E;
import static com.cusc.satlink2s.data.Constant.EAST_OR_WEST.W;
import static com.cusc.satlink2s.data.Constant.NORTH_OR_SOUTH.N;
import static com.cusc.satlink2s.data.Constant.NORTH_OR_SOUTH.S;
import static com.cusc.satlink2s.data.Constant.VERITAL_OR_HORIZONTAL.H;
import static com.cusc.satlink2s.data.Constant.VERITAL_OR_HORIZONTAL.V;
import java.math.BigDecimal;

/**
 *
 * @author maxli
 */
public class Converter {

    public static Constant.EAST_OR_WEST turnEorW(String ew) {
        switch (ew) {
            case "E":
                return E;
            case "W":
                return W;
            default:
                return null;
        }
    }

    public static Constant.NORTH_OR_SOUTH turnNorS(String ns) {
        switch (ns) {
            case "N":
                return N;
            case "S":
                return S;
            default:
                return null;
        }
    }

    public static Constant.BAND_TYPE turnBand(String bd) {
        switch (bd) {
            case "Ku":
                return Ku;
            case "C":
                return C;
            default:
                return null;
        }
    }

    public static Constant.VERITAL_OR_HORIZONTAL turnVorH(String vh) {
        switch (vh) {
            case "V":
                return V;
            case "H":
                return H;
            default:
                return null;
        }
    }

    public static String formatDouble(double original) {
        BigDecimal bd = new BigDecimal(original);
        double formatData = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return Double.toString(formatData);
    }

    public static double formatPercent(double original) {
        double point = original / 100;
        return point;
    }
}
