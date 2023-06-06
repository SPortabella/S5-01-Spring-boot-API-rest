package cat.itacademy.barcelonactiva.PORTABELLASUSANA.s05.t01.n01.model.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;

@Data
public class BranchDTO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String country;
    private String typeCountry;

    public BranchDTO(Integer id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.typeCountry = checkUECountry(country);
    }

    public BranchDTO(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public BranchDTO() {
    }

    private String checkUECountry(String country) {
        String[] countriesUE = {"SPAIN","FRANCE","GERMANY","PORTUGAL","POLAND"};
        if (Arrays.asList(countriesUE).contains(country.toUpperCase()))
            return "EU";
        else return "NonEU";
    }

}
