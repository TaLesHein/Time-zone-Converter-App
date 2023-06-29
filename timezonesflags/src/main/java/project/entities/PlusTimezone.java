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
    private ImageView plusFlag;

    private int valueScroll;
    private ZoneId zoneId;
    private ZonedDateTime dateLocal;
    private Country countrys[] = new Country[12];

    public PlusTimezone(Label date, Label hours, Label timezone, Label zone,
            Slider scroll, ImageView flag) {

        this.lblPlusDate = date;
        this.lblPlusHours = hours;
        this.lblPlusTimezone = timezone;
        this.lblPlusZone = zone;
        this.scrollPlus = scroll;
        this.plusFlag = flag;

        zoneId = ZoneId.of("+03:00");
        dateLocal = ZonedDateTime.parse("2023-01-01T15:00:00" + zoneId);

        arrayConstructor();
        scrollConstructor();

    }

    private void arrayConstructor() {

        // +01:00
        countrys[0] = new Country("Berlim/Alemanha", "/project/images/Berlim, Alemanha.png");

        // +02:00
        countrys[1] = new Country("Cairo/Egito", "/project/images/Cairo, Egito.png");

        // +03:00
        countrys[2] = new Country("Moscou/Rússia", "/project/images/Moscou, Russia.png");

        // +04:00
        countrys[3] = new Country("Abu Dabi/Emirados Árabes Unidos",
                "/project/images/Abu Dabi, Emirados Arabes Unidos.png");

        // +05:00
        countrys[4] = new Country("Carachi/Paquistão", "/project/images/Carachi, Paquistao.png");

        // +06:00
        countrys[5] = new Country("Almaty/Cazaquistão", "/project/images/Almaty, Cazaquistao.png");

        // +07:00
        countrys[6] = new Country("Jacarta/Indonésia", "/project/images/Jacarta, Indonesia.png");

        // +08:00
        countrys[7] = new Country("Pequim/China", "/project/images/Pequim, China.png");

        // +09:00
        countrys[8] = new Country("Tóquio/Japão", "/project/images/Toquio, Japao.png");

        // +10:00
        countrys[9] = new Country("Sydney/Austrália", "/project/images/Sydney, Australia.png");

        // +11:00
        countrys[10] = new Country("Nova Caledônia/França", "/project/images/Nova Caledonia, Franca.png");

        // +12:00
        countrys[11] = new Country("Wellington/Nova Zelândia", "/project/images/Wellington, Nova Zelandia.png");

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
                alterFlag();

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

    private void alterFlag() {

        int valueZoneId = Integer.parseInt(zoneId.toString().substring(1, 3));

        plusFlag.setImage(countrys[valueZoneId - 1].getImgFlag());

        lblPlusZone.setText(countrys[valueZoneId - 1].getNameCountry());
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
