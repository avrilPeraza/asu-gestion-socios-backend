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
@Table(name = "perfil")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50, unique = true)
    private String name;

    @Column(name = "descripcion", nullable = false, length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "estado", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "profile")
    @ToString.Exclude
    private List<FeatureProfile> features = new ArrayList<>();

    @OneToMany(mappedBy = "profile")
    @ToString.Exclude
    private List<User> users = new ArrayList<>();

}