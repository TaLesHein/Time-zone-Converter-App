package project;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import project.entities.MinusTimezone;
import project.entities.PlusTimezone;
import project.entities.UTCTimezone;

public class conversorFrontController implements Initializable {

    @FXML
    private Label lblMinusDate, lblMinusHours, lblMinusTimezone, lblMinusZone;

    @FXML
    private Label lblPlusDate, lblPlusHours, lblPlusTimezone, lblPlusZone;

    @FXML
    private Label lblUTCDate;

    @FXML
    private ImageView minusFlag, plusFlag;

    @FXML
    private Pane paneMinus, panePlus, paneUTC;

    @FXML
    private Slider scrollMinus, scrollPlus, scrollUTC;

    private static MinusTimezone minusTimezone;
    private static PlusTimezone plusTimezone;
    private static UTCTimezone utcTimezone;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        minusTimezone = new MinusTimezone(lblMinusDate, lblMinusHours,
                lblMinusTimezone, lblMinusZone, scrollMinus, minusFlag);

        plusTimezone = new PlusTimezone(lblPlusDate, lblPlusHours,
                lblPlusTimezone, lblPlusZone, scrollPlus,plusFlag);

        utcTimezone = new UTCTimezone(lblUTCDate, scrollUTC);

    }

    public static MinusTimezone getMinusTimezone(){
        return minusTimezone;

    }

    public static PlusTimezone getPlusTimezone(){
        return plusTimezone;
    }

    public static UTCTimezone getUTCTimezone(){
        return utcTimezone;
    }



}
