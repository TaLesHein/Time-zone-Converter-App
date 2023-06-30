package project.entities;

import java.time.ZoneId;

import javafx.scene.image.Image;

public class ImagesFlags {

    private Country countryImg[];

    public ImagesFlags(int amountImages) {

        this.countryImg = new Country[amountImages];

        if (countryImg.length == 12) {

            setPlusImages();
        } else if(countryImg.length == 11) {

            setMinusImages();
        } else{
            setUTCImages();
        }

    }

    private void setPlusImages() {

        // +01:00
        countryImg[0] = new Country("Berlim/Alemanha", "/project/images/Berlim, Alemanha.png");

        // +02:00
        countryImg[1] = new Country("Cairo/Egito", "/project/images/Cairo, Egito.png");

        // +03:00
        countryImg[2] = new Country("Moscou/Rússia", "/project/images/Moscou, Russia.png");

        // +04:00
        countryImg[3] = new Country("Abu Dabi/Emirados Árabes Unidos",
                "/project/images/Abu Dabi, Emirados Arabes Unidos.png");

        // +05:00
        countryImg[4] = new Country("Carachi/Paquistão", "/project/images/Carachi, Paquistao.png");

        // +06:00
        countryImg[5] = new Country("Almaty/Cazaquistão", "/project/images/Almaty, Cazaquistao.png");

        // +07:00
        countryImg[6] = new Country("Jacarta/Indonésia", "/project/images/Jacarta, Indonesia.png");

        // +08:00
        countryImg[7] = new Country("Pequim/China", "/project/images/Pequim, China.png");

        // +09:00
        countryImg[8] = new Country("Tóquio/Japão", "/project/images/Toquio, Japao.png");

        // +10:00
        countryImg[9] = new Country("Sydney/Austrália", "/project/images/Sydney, Australia.png");

        // +11:00
        countryImg[10] = new Country("Nova Caledônia/França", "/project/images/Nova Caledonia, Franca.png");

        // +12:00
        countryImg[11] = new Country("Wellington/Nova Zelândia", "/project/images/Wellington, Nova Zelandia.png");
    }

    private void setMinusImages() {

        // -01:00
        countryImg[0] = new Country("Apia/Samoa", "/project/images/Apia, Samoa.png");

        // -02:00
        countryImg[1] = new Country("Havai/Estados Unidos", "/project/images/Havai, Estados Unidos.png");

        // -03:00
        countryImg[2] = new Country("Alasca/Estados Unidos", "/project/images/Alasca, Estados Unidos.png");

        // -04:00
        countryImg[3] = new Country("California/Estados Unidos", "/project/images/California, Estados Unidos.png");

        // -05:00
        countryImg[4] = new Country("Arizona/Estados Unidos", "/project/images/Arizona, Estados Unidos.png");

        // -06:00
        countryImg[5] = new Country("Cidade do México/México", "/project/images/Cidade do Mexico, Mexico.png");

        // -07:00
        countryImg[6] = new Country("Nova York/Estados Unidos", "/project/images/Nova York, Estados Unidos.png");

        // -08:00
        countryImg[7] = new Country("Santiago/Chile", "/project/images/Santiago, Chile.png");

        // -09:00
        countryImg[8] = new Country("São Paulo/Brasil", "/project/images/Sao Paulo, Brasil.png");

        // -10:00
        countryImg[9] = new Country("Pernambuco/Brasil", "/project/images/Pernambuco, Brasil.png");

        // -11:00
        countryImg[10] = new Country("Açores/Portugal", "/project/images/Acores, Portugal.png");
    }

    private void setUTCImages(){

    }
    
    
    public Image getFlag(ZoneId zoneId) {
        int valueZoneId = Integer.parseInt(zoneId.toString().substring(1, 3));
        return countryImg[valueZoneId - 1].getImgFlag();
    }

    public String getName(ZoneId zoneId){
        int valueZoneId = Integer.parseInt(zoneId.toString().substring(1, 3));
        return countryImg[valueZoneId - 1].getNameCountry();
    }
}
