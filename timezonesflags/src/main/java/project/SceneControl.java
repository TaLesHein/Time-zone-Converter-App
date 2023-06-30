package project;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import project.entities.LocalTimezone;
import project.entities.PaneInfo;
import project.entities.Scroll;
import project.entities.GlobalTimezone;
import project.entities.ImagesFlags;

public class SceneControl implements Initializable {

    @FXML
    private Label lblMinusDate, lblMinusHours, lblMinusTimezone, lblMinusZone;

    @FXML
    private Label lblPlusDate, lblPlusHours, lblPlusTimezone, lblPlusZone;

    @FXML
    private Label lblUTCHours;

    @FXML
    private ImageView minusFlag, plusFlag, utcFlag;

    @FXML
    private Slider sliderMinus, sliderPlus, sliderUTC;

    private static LocalTimezone minusTimezone, plusTimezone;
    private static GlobalTimezone utcTimezone;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        Scroll scrollMinus = new Scroll(sliderMinus, -11, -1, -3);
        Scroll scrollPlus = new Scroll(sliderPlus, 1, 12, 3);
        Scroll scrollUTC = new Scroll(sliderUTC, 0, 23, 12);

        ImagesFlags imgFlagMinus = new ImagesFlags(11);
        ImagesFlags imgFlagPlus = new ImagesFlags(12);
        ImagesFlags imgFlagsUTC = new ImagesFlags(2);

        PaneInfo minusPane = new PaneInfo(lblMinusDate, lblMinusHours, lblMinusTimezone, lblMinusZone, minusFlag, scrollMinus, imgFlagMinus);
        PaneInfo plusPane = new PaneInfo(lblPlusDate, lblPlusHours, lblPlusTimezone, lblPlusZone, plusFlag, scrollPlus, imgFlagPlus);
        PaneInfo utcPane = new PaneInfo(lblUTCHours, utcFlag, scrollUTC, imgFlagsUTC);
        
        minusTimezone = new LocalTimezone(minusPane);
        plusTimezone = new LocalTimezone(plusPane);
        utcTimezone = new GlobalTimezone(utcPane);


    }

    
    public static LocalTimezone getMinusTimezone(){
        return minusTimezone;

    }

    public static LocalTimezone getPlusTimezone(){
        return plusTimezone;
    }

    public static GlobalTimezone getUTCTimezone(){
        return utcTimezone;
    }



}
