/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cusc.satlink2s.algorithm;

import static com.cusc.satlink2s.data.Constant.MEGA;
import com.cusc.satlink2s.status.Information;
import com.cusc.satlink2s.status.Link;

/**
 *
 * @author maxli
 */
public class InformatinoRate {

    public static void ebToN0() {

        Information.ebToN0 = Link.DownLink.cn - 10 * Math.log10(Math.log(Information.modulation / Math.log(2)) / Information.rollOff);
    }

    public static void theoreticalRate() {
        Information.theoreticalRate = Math.pow(10, (Link.totalCn0 - Information.ebToN0) / 10) * Information.coding / MEGA;
    }
}
