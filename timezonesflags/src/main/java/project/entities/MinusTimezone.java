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
    private Country countrys[] = new Country[11];

    public MinusTimezone(Label date, Label hours, Label timezone, Label zone,
            Slider scroll, ImageView flag) {

        this.lblMinusDate = date;
        this.lblMinusHours = hours;
        this.lblMinusTimezone = timezone;
        this.lblMinusZone = zone;
        this.scrollMinus = scroll;
        this.minusFlag = flag;

        zoneId = ZoneId.of("-03:00");
        dateLocal = ZonedDateTime.parse("2023-01-01T09:00:00" + zoneId);

        arrayConstructor();
        scrollConstructor();

    }

    private void arrayConstructor(){

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
                alterFlag();
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

    private void alterFlag(){

        int valueZoneId = Integer.parseInt(zoneId.toString().substring(1,3));
        
        minusFlag.setImage(countrys[valueZoneId-1].getImgFlag());

        lblMinusZone.setText(countrys[valueZoneId-1].getNameCountry());
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
