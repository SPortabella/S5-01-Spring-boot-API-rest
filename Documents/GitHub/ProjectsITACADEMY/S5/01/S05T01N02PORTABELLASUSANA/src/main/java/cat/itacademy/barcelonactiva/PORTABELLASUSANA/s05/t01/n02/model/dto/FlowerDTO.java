package cat.itacademy.barcelonactiva.PORTABELLASUSANA.s05.t01.n02.model.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;

@Data
public class FlowerDTO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String country;
    private String typeCountry;

    public FlowerDTO(Integer id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.typeCountry = checkUECountry(country);
    }

    public FlowerDTO(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public FlowerDTO() {
    }

    private String checkUECountry(String country) {
        String[] countriesUE = {"SPAIN", "FRANCE", "GERMANY", "PORTUGAL", "POLAND"};
        if (Arrays.asList(countriesUE).contains(country.toUpperCase()))
            return "EU";
        else return "NonEU";
    }
}
