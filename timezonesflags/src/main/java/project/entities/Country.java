package project.entities;

import javafx.scene.image.Image;

public class Country {

    private String nameCountry;
    private Image imgFlag;

    public Country(String nameCountry, String imagePath) {

        this.nameCountry = nameCountry;
        this.imgFlag = new Image(getClass().getResourceAsStream(imagePath));

    }

    public String getNameCountry() {
        return nameCountry;
    }

    public Image getImgFlag() {
        return imgFlag;
    }

}
