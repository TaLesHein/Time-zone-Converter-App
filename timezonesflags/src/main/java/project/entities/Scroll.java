package project.entities;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;

public class Scroll {

    @FXML
    private Slider slider;

    private int valueScroll;

    public Scroll(Slider scroll, int minValue, int maxValue, int initialValue) {

        setSlider(scroll);
        setScrollValues(minValue, maxValue, initialValue);
        scrollControl();

    }

    public void scrollControl() {
        this.slider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {

                setValueScroll((int) slider.getValue());
            }

        });
    }

    private void setScrollValues(int min, int max, int initial) {

        slider.setMin(min);
        slider.setMax(max);
        slider.setValue(initial);

    }

    public Slider getSlider() {
        return slider;
    }

    public void setSlider(Slider slider) {
        this.slider = slider;
    }

    public int getValueScroll() {
        return valueScroll;
    }

    public void setValueScroll(int valueScroll) {
        this.valueScroll = valueScroll;
    }

}
