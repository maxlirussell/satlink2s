package com.cusc.satlink2s;

import static com.cusc.satlink2s.algorithm.AntennaCapability.turningNoiseCoiefficient2Temprature;
import static com.cusc.satlink2s.data.Constant.KILO;
import static com.cusc.satlink2s.data.Converter.formatDouble;
import com.cusc.satlink2s.status.Antenna;
import static com.cusc.satlink2s.status.Antenna.antennasList;
import static com.cusc.satlink2s.status.Antenna.specifiedAntenna;
import com.cusc.satlink2s.status.Information;
import com.cusc.satlink2s.status.Link;
import com.cusc.satlink2s.status.Satellite;
import static com.cusc.satlink2s.status.Satellite.satellitesList;
import static com.cusc.satlink2s.status.Satellite.specifiedSatellite;
import static com.cusc.satlink2s.status.Satellite.specifiedTransponder;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FXMLController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Label labelSfd;
    @FXML
    private Label labelGt;
    @FXML
    private Label labelLongtitude;
    @FXML
    private Label labelBeam;
    @FXML
    private Label labelBandwidth;
    @FXML
    private ComboBox eirpList;
    @FXML
    private ImageView imageEirp;
    @FXML
    private TreeView satelliteListTree;

    @FXML
    private TreeView antennaListTree;
    @FXML
    private Label labelModel;
    @FXML
    private Label labelCaliber;
    @FXML
    private Label labelEffictiveness;
    @FXML
    private Label labelAntennaNoise;
    @FXML
    private Label labelBucPower;
    @FXML
    private Label labelBucLo;
    @FXML
    private Label labelLnbNoise;
    @FXML
    private Label labelLnbLo;

    @FXML
    private TextField textFieldRxLband;
    @FXML
    private TextField textFieldTxLband;
    @FXML
    private Label labelRxFreq;
    @FXML
    private Label labelTxFreq;
    @FXML
    private Label labelRxGain;
    @FXML
    private Label labelTxGain;
    @FXML
    private Label labelSysNoise;
    @FXML
    private Label labelSysGt;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");

    }

//    private final Node rootIcon = new ImageView(
//            new Image(getClass().getResourceAsStream("folder_16.png"))
//    );
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        buildSatListTree();
        buildAntennaListTree();

        satelliteListTree.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                eirpList.getItems().clear();
                TreeItem<String> currentSelectItem = (TreeItem<String>) newValue;
                for (int i = 0; i < satellitesList.size(); i++) {
                    for (int j = 0; j < satellitesList.get(i).getTransponders().size(); j++) {
                        if (currentSelectItem.getValue().equals(satellitesList.get(i).getTransponders().get(j).getName())
                                && currentSelectItem.getParent().getValue().equals(satellitesList.get(i).getName())) {
                            specifiedSatellite(i);
                            specifiedTransponder(j);
                            for (int k = 0; k < satellitesList.get(i).getTransponders().get(j).getEirps().length; k++) {
                                eirpList.getItems().add(satellitesList.get(i).getTransponders().get(j).getEirps()[k]);
                            }
                        }
                    }
                }
                displaySatelliteInfo();
            }
        });

        antennaListTree.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                TreeItem<String> currentSelectItem = (TreeItem<String>) newValue;
                for (int i = 0; i < antennasList.size(); i++) {

                    if (currentSelectItem.getValue().equals(antennasList.get(i).getModel())) {
                        specifiedAntenna(i);
                    }
                }
                dispalyAntennaInfo();
            }
        });

        eirpList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                Satellite.SpecifiedEirpValue.eirp = Double.parseDouble(newValue.toString());
            }
        });
    }

    public void buildSatListTree() {
        TreeItem<String> rootItem = new TreeItem<>("卫星资源");
        rootItem.setExpanded(true);
        for (int i = 0; i < satellitesList.size(); i++) {
            TreeItem<String> satelliteItem = new TreeItem<>(satellitesList.get(i).getName());
            satelliteItem.setExpanded(true);
            for (int j = 0; j < satellitesList.get(i).getTransponders().size(); j++) {
                TreeItem<String> transponderItem = new TreeItem<>(satellitesList.get(i).getTransponders().get(j).getName());
                satelliteItem.getChildren().add(transponderItem);
            }
            rootItem.getChildren().add(satelliteItem);
        }

        satelliteListTree.setRoot(rootItem);
    }

    public void buildAntennaListTree() {
        TreeItem<String> rootItem = new TreeItem<>("天线型号");
        rootItem.setExpanded(true);
        for (int i = 0; i < antennasList.size(); i++) {
            TreeItem<String> antennaItem = new TreeItem<>(antennasList.get(i).getModel());
            rootItem.getChildren().add(antennaItem);
        }
        antennaListTree.setRoot(rootItem);
    }

    public void displaySatelliteInfo() {
        labelSfd.setText(formatDouble(Satellite.SpecifiedTransponderResource.sfd));
        labelGt.setText(formatDouble(Satellite.SpecifiedTransponderResource.gt));
        labelLongtitude.setText(Satellite.SpecifiedSatelliteResource.eastOrWest.toString() + formatDouble(Satellite.SpecifiedSatelliteResource.longtitude));
        labelBeam.setText(Satellite.SpecifiedSatelliteResource.name + Satellite.SpecifiedTransponderResource.name + "波束");
        labelBandwidth.setText(formatDouble(Satellite.SpecifiedTransponderResource.bandwidth));
        imageEirp.setImage(new Image("data/image/" + Satellite.SpecifiedTransponderResource.imagePath, true));
    }

    public void dispalyAntennaInfo() {
        labelModel.setText(Antenna.SpecifiedModel.model);
        labelCaliber.setText(formatDouble(Antenna.SpecifiedModel.diameter));
        labelEffictiveness.setText(formatDouble(Antenna.SpecifiedModel.efficiency * 100));
        labelAntennaNoise.setText(formatDouble(Antenna.SpecifiedModel.antennaNoise));
        labelBucPower.setText(formatDouble(Antenna.SpecifiedModel.bucPower));
        labelBucLo.setText(formatDouble(Antenna.SpecifiedModel.bucLo));
        labelLnbNoise.setText(formatDouble(Antenna.SpecifiedModel.lnbNoise));
        labelLnbLo.setText(formatDouble(Antenna.SpecifiedModel.lnbLo));
    }

    public void setLinkInfo() {
        Link.UpLink.txLband = Double.parseDouble(textFieldTxLband.getText());
        Link.DownLink.rxLband = Double.parseDouble(textFieldRxLband.getText());

    }

    public void displayLinkInfo() {
        labelTxFreq.setText(formatDouble(Link.UpLink.txFrequency));
        labelRxFreq.setText(formatDouble(Link.DownLink.rxFrequency));
        labelTxGain.setText(formatDouble(Antenna.Capability.txGain));
        labelRxGain.setText(formatDouble(Antenna.Capability.rxGain));
        labelSysNoise.setText(formatDouble(Antenna.Capability.systemNoiseTemperature));
        labelSysGt.setText(formatDouble(Antenna.Capability.GT));
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
}
