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
import project.entities.UTCTimezone;

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
    private static UTCTimezone utcTimezone;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        Scroll scrollMinus = new Scroll(sliderMinus, -11, -1, -3);
        Scroll scrollPlus = new Scroll(sliderPlus, 12, 1, 3);
        Scroll scrollUTC = new Scroll(sliderUTC, 0, 23, 12);

        PaneInfo minusPane = new PaneInfo(lblMinusDate, lblMinusHours, lblMinusTimezone, lblMinusZone, minusFlag, scrollMinus);
        PaneInfo plusPane = new PaneInfo(lblPlusZone, lblPlusHours, lblPlusTimezone, lblPlusDate, plusFlag, scrollPlus);
        PaneInfo utcPane = new PaneInfo(lblUTCHours, utcFlag, scrollUTC);
        
        minusTimezone = new LocalTimezone(minusPane);
        plusTimezone = new LocalTimezone(plusPane);


    }

    
    public static LocalTimezone getMinusTimezone(){
        return minusTimezone;

    }

    public static LocalTimezone getPlusTimezone(){
        return plusTimezone;
    }

    public static UTCTimezone getUTCTimezone(){
        return utcTimezone;
    }



}
