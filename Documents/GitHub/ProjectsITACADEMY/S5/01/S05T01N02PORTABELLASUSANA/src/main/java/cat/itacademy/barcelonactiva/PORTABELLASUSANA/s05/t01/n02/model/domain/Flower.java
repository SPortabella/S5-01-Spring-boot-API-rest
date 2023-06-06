package cat.itacademy.barcelonactiva.PORTABELLASUSANA.s05.t01.n02.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Flower {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Integer id;
    private String name;
    private String country;

    public Flower(String name, String country) {
        this.name = name;
        this.country = country;
    }
}
