package project.entities;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import project.SceneControl;
import project.utils.SliderInteractionHandler;

public class LocalTimezone {

    @FXML
    private Label lblDate, lblHours, lblTimezone, lblZone;

    @FXML
    private ImageView countryFlag;

    private Scroll scroll;
    private ImagesFlags imgFlag;

    private ZoneId zoneId;
    private ZonedDateTime dateLocal;

    private String stringTimezoneFormat;

    public LocalTimezone(PaneInfo paneInfo) {

        this.lblDate = paneInfo.getLblDate();
        this.lblHours = paneInfo.getLblHours();
        this.lblTimezone = paneInfo.getLblTimezone();
        this.lblZone = paneInfo.getLblZone();

        this.countryFlag = paneInfo.getCountryFlag();

        this.scroll = paneInfo.getSlider();

        this.imgFlag = paneInfo.getImgFlag();

        if (scroll.getSlider().getValue() > 0) {

            stringTimezoneFormat = "+%02d";
        } else {
            stringTimezoneFormat = "%03d";
        }

        String zoneIdDefine = String.format(stringTimezoneFormat, (int) scroll.getSlider().getValue());
        zoneId = ZoneId.of(zoneIdDefine);
        dateLocal = ZonedDateTime.parse("2023-01-01T09:00:00" + zoneId);

        SliderInteractionHandler.sliderEvent(scroll.getSlider(), lblTimezone);
        scrollEventObservable();

    }

    private void scrollEventObservable() {

        scroll.getSlider().valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {

                zoneId = ZoneId.of(String.format(stringTimezoneFormat, scroll.getValueScroll()));

                lblTimezone.setText(zoneId.toString());
                convertTimezoneHours();
                alterFlag();
            }

        });
    }

    private void convertTimezoneHours() {

        GlobalTimezone utcTimezone = SceneControl.getUTCTimezone();

        OffsetDateTime dateGlobal = utcTimezone.getOffsetDateTime();

        ZonedDateTime convertZonedDateTime = dateGlobal.atZoneSameInstant(zoneId);

        setZonedDateTime(convertZonedDateTime);

    }

    private void alterFlag() {

        countryFlag.setImage(imgFlag.getFlag(zoneId));
        lblZone.setText(imgFlag.getName(zoneId));
    }

    public void setHourText(String newHour) {
        lblHours.setText(newHour + ":00");
    }

    public void setDateText(String newDate) {
        lblDate.setText(newDate);
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    public void setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.dateLocal = zonedDateTime;
        setHourText(Integer.toString(dateLocal.getHour()));

        DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        setDateText(dFormatter.format(dateLocal));
    }

}
