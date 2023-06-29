package project.entities;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import project.conversorFrontController;
import project.utils.SliderInteractionHandler;

public class PlusTimezone {

    @FXML
    private Label lblPlusDate, lblPlusHours, lblPlusTimezone, lblPlusZone;

    @FXML
    private Slider scrollPlus;

    @FXML
    private ImageView PlusFlag;

    private int valueScroll;
    private ZoneId zoneId;
    private ZonedDateTime dateLocal;

    public PlusTimezone(Label date, Label hours, Label timezone, Label zone,
            Slider scroll, ImageView flag) {

        this.lblPlusDate = date;
        this.lblPlusHours = hours;
        this.lblPlusTimezone = timezone;
        this.lblPlusZone = zone;
        this.scrollPlus = scroll;

        zoneId = ZoneId.of("+03:00");
        dateLocal = ZonedDateTime.parse("2023-01-01T15:00:00" + zoneId);
        scrollConstructor();

    }

    private void scrollConstructor() {

        scrollPlus.setMin(1);
        scrollPlus.setMax(12);
        scrollPlus.setValue(3);

        scrollPlus.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {

                valueScroll = (int) scrollPlus.getValue();
                zoneId = ZoneId.of(String.format("+%02d:00", valueScroll));

                lblPlusTimezone.setText(zoneId.toString());
                convertTimezoneHours();

            }
        });

        SliderInteractionHandler.sliderEvent(scrollPlus, lblPlusTimezone);
    }

    private void convertTimezoneHours() {

        UTCTimezone utcTimezone = conversorFrontController.getUTCTimezone();

        OffsetDateTime dateGlobal = utcTimezone.getOffsetDateTime();

        ZonedDateTime convertZonedDateTime = dateGlobal.atZoneSameInstant(zoneId);

        setZonedDateTime(convertZonedDateTime);

    }

    public void setHourText(String newHour) {
        lblPlusHours.setText(newHour + ":00");
    }

    public void setDateText(String newDate) {
        lblPlusDate.setText(newDate);
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
