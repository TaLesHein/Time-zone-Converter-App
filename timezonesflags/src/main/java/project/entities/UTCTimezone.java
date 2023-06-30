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
import project.SceneControl;
import project.utils.SliderInteractionHandler;

public class UTCTimezone {

    @FXML
    private Label lblUTCHour;

    @FXML
    private Slider scrollUTC;

    @FXML
    private ImageView utcFlag;

    private int valueScroll;
    private OffsetDateTime dateGlobal = OffsetDateTime.parse("2023-01-01T12:00:00Z");

    public UTCTimezone(Label lblDate, Slider scroll, ImageView flag) {

        this.lblUTCHour = lblDate;
        this.scrollUTC = scroll;
        this.utcFlag = flag;

        DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("HH:mm");
        scrollUTC.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {

                valueScroll = (int) scrollUTC.getValue();
                String valueFormatString = String.format("%02d", valueScroll);

                dateGlobal = OffsetDateTime.parse("2023-01-01T" + valueFormatString + ":00:00Z");

                lblUTCHour.setText(dFormatter.format(dateGlobal));
                convertTimezoneHours();

            }

        });

        SliderInteractionHandler.sliderEvent(scrollUTC, lblUTCHour);

    }

    private void convertTimezoneHours() {

        LocalTimezone minusTimezone = SceneControl.getMinusTimezone();
        LocalTimezone plusTimezone = SceneControl.getPlusTimezone();

        ZoneId minusZoneId = minusTimezone.getZoneId();
        ZoneId plusZoneId = plusTimezone.getZoneId();

        ZonedDateTime convertMinusDateTime = dateGlobal.atZoneSameInstant(minusZoneId);
        ZonedDateTime convertPlusDateTime = dateGlobal.atZoneSameInstant(plusZoneId);
        
        minusTimezone.setZonedDateTime(convertMinusDateTime);
        plusTimezone.setZonedDateTime(convertPlusDateTime);

    }

    public OffsetDateTime getOffsetDateTime(){
        return dateGlobal;
    }

}
