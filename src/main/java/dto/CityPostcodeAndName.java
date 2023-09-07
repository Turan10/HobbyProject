package dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
    public class CityPostcodeAndName {
    private int zip;
    private String cityName;

    public CityPostcodeAndName(int zip, String cityName) {
        this.zip = zip;
        this.cityName = cityName;
    }


}
