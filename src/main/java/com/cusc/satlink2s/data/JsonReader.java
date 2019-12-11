/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cusc.satlink2s.data;

import static com.cusc.satlink2s.data.Converter.formatPercent;
import static com.cusc.satlink2s.data.Converter.turnBand;
import static com.cusc.satlink2s.data.Converter.turnEorW;
import static com.cusc.satlink2s.data.Converter.turnVorH;
import static com.cusc.satlink2s.status.Antenna.antennasList;
import com.cusc.satlink2s.status.Satellite;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.JSONArray;

/**
 *
 * @author maxli
 */
public class JsonReader {

    public static String FILEPATH = "";

    public static void loadSatelliteList(String filePath) throws Exception {

        File file = new File(filePath);

        if (!file.exists() || file.isDirectory()) {
            throw new FileNotFoundException();
        }
        StringBuffer sb = new StringBuffer();
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                sb.append(lineTxt);
            }
        } catch (IOException e) {
        }

        String jsonContext = sb.toString();
        JSONArray ja = new JSONArray(jsonContext);

        for (int i = 0; i < ja.length(); i++) {         //获取卫星列表
            SatelliteTemplate st = new SatelliteTemplate();

            st.setName(ja.getJSONObject(i).getString("name"));
            st.setLongtitude(ja.getJSONObject(i).getDouble("longtitude"));
            st.setEastOrWest(turnEorW(ja.getJSONObject(i).getString("EorW")));

            for (int j = 0; j < ja.getJSONObject(i).getJSONArray("transponder").length(); j++) {    //获取转发器列表
                double[] eirps = new double[ja.getJSONObject(i).getJSONArray("transponder").getJSONObject(j).getJSONArray("eirp").length()];
                TransponderTemplate tt = new TransponderTemplate();
                tt.setName(ja.getJSONObject(i).getJSONArray("transponder").getJSONObject(j).getString("beam"));
                tt.setBand(turnBand(ja.getJSONObject(i).getJSONArray("transponder").getJSONObject(j).getString("bandtype")));
                tt.setBandwidth(ja.getJSONObject(i).getJSONArray("transponder").getJSONObject(j).getDouble("bandwidth"));
                tt.setGt(ja.getJSONObject(i).getJSONArray("transponder").getJSONObject(j).getDouble("gt"));
                tt.setSfd(ja.getJSONObject(i).getJSONArray("transponder").getJSONObject(j).getDouble("sfd"));
                tt.setImagePath(ja.getJSONObject(i).getJSONArray("transponder").getJSONObject(j).getString("imagepath"));
                tt.setRxPolar(turnVorH(ja.getJSONObject(i).getJSONArray("transponder").getJSONObject(j).getString("rxPorla")));

                for (int k = 0; k < ja.getJSONObject(i).getJSONArray("transponder").getJSONObject(j).getJSONArray("eirp").length(); k++) {  //获取eirp列表
                    eirps[k] = ja.getJSONObject(i).getJSONArray("transponder").getJSONObject(j).getJSONArray("eirp").getJSONObject(k).getDouble("value");
                }

                tt.setEirps(eirps);
                st.addTransponder(tt);
            }
            Satellite.satellitesList.add(st);
        }

    }

    public static void loadAntennaList(String filePath) throws Exception {

        File file = new File(filePath);

        if (!file.exists() || file.isDirectory()) {
            throw new FileNotFoundException();
        }
        StringBuffer sb = new StringBuffer();
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                sb.append(lineTxt);
            }
        } catch (IOException e) {
        }

        String jsonContext = sb.toString();
        JSONArray ja = new JSONArray(jsonContext);

        for (int i = 0; i < ja.length(); i++) {
            AntennaTemplate at = new AntennaTemplate();
            at.setModel(ja.getJSONObject(i).getString("model"));
            at.setDiameter(ja.getJSONObject(i).getDouble("diameter"));
            at.setBand(turnBand(ja.getJSONObject(i).getString("band")));
            at.setEfficiency(formatPercent(ja.getJSONObject(i).getDouble("efficiency")));
            at.setBucPower(ja.getJSONObject(i).getDouble("bucPower"));
            at.setBucLo(ja.getJSONObject(i).getDouble("bucLo"));
            at.setLnbLo(ja.getJSONObject(i).getDouble("lnbLo"));
            at.setAntennaNoise(ja.getJSONObject(i).getDouble("antennaNoise"));
            at.setLnbNoise(ja.getJSONObject(i).getDouble("lnbNoise"));
            at.setFeederLength(ja.getJSONObject(i).getDouble("feederLength"));
            at.setLnbGain(ja.getJSONObject(i).getDouble("lnbGain"));
            antennasList.add(at);

        }

    }

}
