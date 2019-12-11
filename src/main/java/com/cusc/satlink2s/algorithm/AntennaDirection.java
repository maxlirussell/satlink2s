/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cusc.satlink2s.algorithm;

import static com.cusc.satlink2s.data.Constant.ANGLETURNING;
import static com.cusc.satlink2s.data.Constant.EARTHRADIUS;
import com.cusc.satlink2s.data.Constant.EAST_OR_WEST;
import static com.cusc.satlink2s.data.Constant.EAST_OR_WEST.W;
import static com.cusc.satlink2s.data.Constant.LEFT_OR_RIGHT.L;
import static com.cusc.satlink2s.data.Constant.LEFT_OR_RIGHT.R;
import com.cusc.satlink2s.data.Constant.NORTH_OR_SOUTH;
import static com.cusc.satlink2s.data.Constant.NORTH_OR_SOUTH.N;
import static com.cusc.satlink2s.data.Constant.ORBITRADIUS;
import static com.cusc.satlink2s.data.Constant.VERITAL_OR_HORIZONTAL.V;
import com.cusc.satlink2s.status.Antenna;
import com.cusc.satlink2s.status.Carrier;
import com.cusc.satlink2s.status.Satellite;
import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.tan;

/**
 *
 * @author maxli
 */
public class AntennaDirection {
    //计算方位角

    public static void azimuthAngle(double longtitude, EAST_OR_WEST ew, double latitude, NORTH_OR_SOUTH ns, double satLongtitude, EAST_OR_WEST sew) {

        if (ew.equals(W) && longtitude > 0) { //如果是西经，取负值
            longtitude = 0 - longtitude;
        }
        if (sew.equals(W) && satLongtitude > 0) {//如果是西经，取负值
            satLongtitude = 0 - satLongtitude;
        }

        double result = Math.toDegrees(Math.atan(tan((satLongtitude - longtitude) * ANGLETURNING) / Math.sin(latitude * ANGLETURNING)));

        if (ns.equals(N)) //位于北半球
        {
            if (result >= 0) {  //南偏东
                Antenna.Direction.Azimuth = 180 - result;
            } else {            //南偏西
                Antenna.Direction.Azimuth = 180 + Math.abs(result);
            }
        } else {                //位于南半球
            if (result >= 0) {  //北偏东
                Antenna.Direction.Azimuth = result;
            } else {            //北偏西
                Antenna.Direction.Azimuth = 360 - result;
            }
        }

    }

    //计算俯仰角
    public static void elevationAngle(double longtitude, EAST_OR_WEST ew, double latitude, double satLongtitude, EAST_OR_WEST sew) {

        if (ew.equals(W) && longtitude > 0) { //如果是西经，取负值
            longtitude = 0 - longtitude;
        }
        if (sew.equals(W) && satLongtitude > 0) {//如果是西经，取负值
            satLongtitude = 0 - satLongtitude;
        }

        Antenna.Direction.Elevation = Math.toDegrees(atan((cos((satLongtitude - longtitude) * ANGLETURNING) * cos(latitude * ANGLETURNING) - EARTHRADIUS / ORBITRADIUS)
                / Math.sqrt(1 - (cos((satLongtitude - longtitude) * ANGLETURNING) * cos(latitude * ANGLETURNING) * cos((satLongtitude - longtitude) * ANGLETURNING) * cos(latitude * ANGLETURNING)))));
    }

    //计算极化角
    public static void porlarizationAngle(double longtitude, EAST_OR_WEST ew, double latitude, double satLongtitude, EAST_OR_WEST sew) {
        if (ew.equals(W) && longtitude > 0) { //如果是西经，取负值
            longtitude = 0 - longtitude;
        }
        if (sew.equals(W) && satLongtitude > 0) {//如果是西经，取负值
            satLongtitude = 0 - satLongtitude;
        }

        Antenna.Direction.Polarization = Math.toDegrees(Math.sin((satLongtitude - longtitude) * ANGLETURNING) / tan(latitude * ANGLETURNING));
        if (Satellite.SpecifiedTransponderResource.vOrH.equals(V)) {
            Antenna.Direction.Polarization += 90.0;
        }
    }

    public static void bowAngle() { //计算左右舷航向夹角

        Antenna.Direction.BowAngle = Antenna.Direction.Azimuth - Carrier.heading;
        if (Antenna.Direction.BowAngle >= 0) {
            Carrier.leftOrRight = R;
        } else {
            Carrier.leftOrRight = L;
        }

    }

    public static void buildTrackingAngles() {  //计算所有对星角
        azimuthAngle(Carrier.longtitude, Carrier.eastOrWest.E, Carrier.latitude, Carrier.northOrSouth, Satellite.SpecifiedSatelliteResource.longtitude, Satellite.SpecifiedSatelliteResource.eastOrWest);
        elevationAngle(Carrier.longtitude, Carrier.eastOrWest.E, Carrier.latitude, Satellite.SpecifiedSatelliteResource.longtitude, Satellite.SpecifiedSatelliteResource.eastOrWest);
        porlarizationAngle(Carrier.longtitude, Carrier.eastOrWest.E, Carrier.latitude, Satellite.SpecifiedSatelliteResource.longtitude, Satellite.SpecifiedSatelliteResource.eastOrWest);
        bowAngle();
    }
}
