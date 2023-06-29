package project.utils;

import javafx.event.Event;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;

public class SliderInteractionHandler {

    public static void sliderEvent(Slider scroll, Label label) {

       scroll.addEventHandler(Event.ANY, event -> {

            if (event instanceof MouseEvent) {
                MouseEvent mouseEvent = (MouseEvent) event;
                handleMouseEvent(mouseEvent, label);

            } else if (event instanceof KeyEvent) {
                KeyEvent keyEvent = (KeyEvent) event;
                handleKeyEvent(keyEvent, label);

            } else if (event instanceof TouchEvent) {
                TouchEvent touchEvent = (TouchEvent) event;
                handleTouchEvent(touchEvent, label);
            }
        });
    }

    private static void handleMouseEvent(MouseEvent event, Label label) {
        if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            label.setStyle("-fx-font-weight: bold;");

        } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            label.setStyle("-fx-font-weight: bold;");

        } else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
            label.setStyle("-fx-font-weight: normal;");
        }
    }

    private static void handleKeyEvent(KeyEvent event, Label label) {

        if(event.getCode() != KeyCode.LEFT && event.getCode() != KeyCode.RIGHT){
            return;
        }
        
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
           label.setStyle("-fx-font-weight: bold;");

        } else if (event.getEventType() == KeyEvent.KEY_RELEASED) {
           label.setStyle("-fx-font-weight: normal;");
        }
    }

    private static void handleTouchEvent(TouchEvent event, Label label) {
        if (event.getEventType() == TouchEvent.TOUCH_PRESSED) {
           label.setStyle("-fx-font-weight: bold;");

        } else if (event.getEventType() == TouchEvent.TOUCH_MOVED) {
           label.setStyle("-fx-font-weight: bold;");

        } else if (event.getEventType() == TouchEvent.TOUCH_RELEASED) {
           label.setStyle("-fx-font-weight: normal;");
        }
    }
}