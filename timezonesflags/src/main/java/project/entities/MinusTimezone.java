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

public class MinusTimezone {

    @FXML
    private Label lblMinusDate, lblMinusHours, lblMinusTimezone, lblMinusZone;

    @FXML
    private Slider scrollMinus;

    @FXML
    private ImageView minusFlag;

    private int valueScroll;
    private ZoneId zoneId;
    private ZonedDateTime dateLocal;

    public MinusTimezone(Label date, Label hours, Label timezone, Label zone,
            Slider scroll, ImageView flag) {

        this.lblMinusDate = date;
        this.lblMinusHours = hours;
        this.lblMinusTimezone = timezone;
        this.lblMinusZone = zone;
        this.scrollMinus = scroll;

        zoneId = ZoneId.of("-03:00");
        dateLocal = ZonedDateTime.parse("2023-01-01T09:00:00" + zoneId);

        scrollConstructor();

    }

    private void scrollConstructor() {

        scrollMinus.setMin(-11);
        scrollMinus.setMax(-01);
        scrollMinus.setValue(-3);

        scrollMinus.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {

                valueScroll = (int) scrollMinus.getValue();
                zoneId = ZoneId.of(String.format("%03d:00", valueScroll));

                lblMinusTimezone.setText(zoneId.toString());
                convertTimezoneHours();
            }

        });

        SliderInteractionHandler.sliderEvent(scrollMinus, lblMinusTimezone);
    }

    private void convertTimezoneHours() {

        UTCTimezone utcTimezone = conversorFrontController.getUTCTimezone();

        OffsetDateTime dateGlobal = utcTimezone.getOffsetDateTime();

        ZonedDateTime convertZonedDateTime = dateGlobal.atZoneSameInstant(zoneId);

        setZonedDateTime(convertZonedDateTime);

    }

    public void setHourText(String newHour) {
        lblMinusHours.setText(newHour + ":00");
    }

    public void setDateText(String newDate) {
        lblMinusDate.setText(newDate);
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
