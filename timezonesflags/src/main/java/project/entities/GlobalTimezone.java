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

public class GlobalTimezone {

    @FXML
    private Label lblHour;

    @FXML
    private Scroll scroll;

    @FXML
    private ImageView countryFlag;

    private OffsetDateTime dateGlobal = OffsetDateTime.parse("2023-01-01T12:00:00Z");

    public GlobalTimezone(PaneInfo paneInfo) {

        this.lblHour = paneInfo.getLblHours();
        this.scroll = paneInfo.getSlider();
        this.countryFlag = paneInfo.getCountryFlag();

         SliderInteractionHandler.sliderEvent(scroll.getSlider(), lblHour);
        scrollEventObservable();

    }

    private void scrollEventObservable() {
        DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("HH:mm");
        scroll.getSlider().valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {

                String valueFormatString = String.format("%02d", scroll.getValueScroll());

                dateGlobal = OffsetDateTime.parse("2023-01-01T" + valueFormatString + ":00:00Z");

                lblHour.setText(dFormatter.format(dateGlobal));
                convertTimezoneHours();

            }

        });

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

    public OffsetDateTime getOffsetDateTime() {
        return dateGlobal;
    }

}
