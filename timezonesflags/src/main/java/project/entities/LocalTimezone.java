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

public class LocalTimezone {

    @FXML
    private Label lblDate, lblHours, lblTimezone, lblZone;

    @FXML
    private ImageView countryFlag;

    private Scroll scroll;
    private Country countrys[] = new Country[11];

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

        if (scroll.getSlider().getValue() > 0) {

            stringTimezoneFormat = "+%02d";
        }
        else{
            stringTimezoneFormat = "%03d";
        }

        String zoneIdDefine = String.format(stringTimezoneFormat, (int) scroll.getSlider().getValue());
        zoneId = ZoneId.of(zoneIdDefine);
        dateLocal = ZonedDateTime.parse("2023-01-01T09:00:00" + zoneId);

        arrayConstructor();
        scrollEventObservable();

    }

    private void arrayConstructor() {

        // -01:00
        countrys[0] = new Country("Apia/Samoa", "/project/images/Apia, Samoa.png");

        // -02:00
        countrys[1] = new Country("Havai/Estados Unidos", "/project/images/Havai, Estados Unidos.png");

        // -03:00
        countrys[2] = new Country("Alasca/Estados Unidos", "/project/images/Alasca, Estados Unidos.png");

        // -04:00
        countrys[3] = new Country("California/Estados Unidos", "/project/images/California, Estados Unidos.png");

        // -05:00
        countrys[4] = new Country("Arizona/Estados Unidos", "/project/images/Arizona, Estados Unidos.png");

        // -06:00
        countrys[5] = new Country("Cidade do México/México", "/project/images/Cidade do Mexico, Mexico.png");

        // -07:00
        countrys[6] = new Country("Nova York/Estados Unidos", "/project/images/Nova York, Estados Unidos.png");

        // -08:00
        countrys[7] = new Country("Santiago/Chile", "/project/images/Santiago, Chile.png");

        // -09:00
        countrys[8] = new Country("São Paulo/Brasil", "/project/images/Sao Paulo, Brasil.png");

        // -10:00
        countrys[9] = new Country("Pernambuco/Brasil", "/project/images/Pernambuco, Brasil.png");

        // -11:00
        countrys[10] = new Country("Açores/Portugal", "/project/images/Acores, Portugal.png");
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

        SliderInteractionHandler.sliderEvent(scroll.getSlider(), lblTimezone);
    }

    private void convertTimezoneHours() {

        UTCTimezone utcTimezone = SceneControl.getUTCTimezone();

        OffsetDateTime dateGlobal = utcTimezone.getOffsetDateTime();

        ZonedDateTime convertZonedDateTime = dateGlobal.atZoneSameInstant(zoneId);

        setZonedDateTime(convertZonedDateTime);

    }

    private void alterFlag() {

        int valueZoneId = Integer.parseInt(zoneId.toString().substring(1, 3));

        countryFlag.setImage(countrys[valueZoneId - 1].getImgFlag());

        lblZone.setText(countrys[valueZoneId - 1].getNameCountry());
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
