/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cusc.satlink2s.status;

import com.cusc.satlink2s.data.Constant.EAST_OR_WEST;
import com.cusc.satlink2s.data.Constant.LEFT_OR_RIGHT;
import com.cusc.satlink2s.data.Constant.NORTH_OR_SOUTH;

/**
 *
 * @author maxli
 */
public class Carrier {
    
    //以下参数输入获得
    public static double longtitude;    //载体经度
    public static EAST_OR_WEST eastOrWest;
    public static double latitude;      //载体纬度
    public static NORTH_OR_SOUTH northOrSouth;
    public static double heading;       //载体航向
    public static double minOcclusion; //相对于船艏向，遮挡范围
    public static double maxOcclusion;
    
    //以下参数计算获得
    public static LEFT_OR_RIGHT leftOrRight;
}
