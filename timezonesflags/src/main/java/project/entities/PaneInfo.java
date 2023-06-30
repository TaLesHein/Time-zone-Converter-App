package project.entities;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class PaneInfo {

    @FXML
    private Label lblDate, lblHours, lblTimezone, lblZone;

    @FXML
    private ImageView countryFlag;

    private Scroll slider;

    public PaneInfo(Label lblDate, Label lblHours, Label lblTimezone, Label lblZone, ImageView countryFlag,
            Scroll slider) {

        this.lblDate = lblDate;
        this.lblHours = lblHours;
        this.lblTimezone = lblTimezone;
        this.lblZone = lblZone;
        this.countryFlag = countryFlag;
        this.slider = slider;

    }

    public PaneInfo(Label lblHours, ImageView countryFlag, Scroll slider){
        this.lblHours = lblHours;
        this.countryFlag = countryFlag;
        this.slider = slider;

    }
    public Label getLblDate() {
        return lblDate;
    }

    public Label getLblHours() {
        return lblHours;
    }

    public Label getLblTimezone() {
        return lblTimezone;
    }

    public Label getLblZone() {
        return lblZone;
    }

    public ImageView getCountryFlag() {
        return countryFlag;
    }

    public Scroll getSlider() {
        return slider;
    }

    

}
