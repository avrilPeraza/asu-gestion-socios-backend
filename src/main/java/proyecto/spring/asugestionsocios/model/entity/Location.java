package proyecto.spring.asugestionsocios.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "localidad")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100, unique = true)
    private String name;

    @Column(name = "codigo_area", nullable = false, length = 50, unique = true)
    private String areaCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pais_id", nullable = false)
    @ToString.Exclude
    private Country country;

    @OneToMany(mappedBy = "location")
    @ToString.Exclude
    private List<Phone> phones = new ArrayList<>();

}