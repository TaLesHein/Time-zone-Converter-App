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
    private ImagesFlags imgFlag;

    public PaneInfo(Label lblDate, Label lblHours, Label lblTimezone, Label lblZone, ImageView countryFlag,
            Scroll slider, ImagesFlags flag) {

        this.lblDate = lblDate;
        this.lblHours = lblHours;
        this.lblTimezone = lblTimezone;
        this.lblZone = lblZone;
        this.countryFlag = countryFlag;
        this.slider = slider;
        this.imgFlag = flag;

    }

    public PaneInfo(Label lblHours, ImageView countryFlag, Scroll slider, ImagesFlags flag) {
        this.lblHours = lblHours;
        this.countryFlag = countryFlag;
        this.slider = slider;
        this.imgFlag = flag;

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

    public ImagesFlags getImgFlag() {
        return imgFlag;
    }

}
