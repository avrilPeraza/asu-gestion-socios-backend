package proyecto.spring.asugestionsocios.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@Entity
@Table(name = "app_user")
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "document_type", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @Column(name = "document", nullable = false, length = 20, unique = true)
    private String document;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "member_number", nullable = false, length = 15, unique = true)
    private String memberNumber;

    @Column(name = "status", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "uses_sign_language", nullable = false)
    private Boolean usesSignLanguage = false;

    @Column(name = "has_hearing_impairment", nullable = false)
    private Boolean hasHearingImpairment = false;

    @Column(name = "street", nullable = false, length = 50)
    private String street;

    @Column(name = "house_number", nullable = false, length = 20)
    private String houseNumber;

    @Column(name = "apartment", nullable = false, length = 20)
    private String apartment;

    @Column(name = "belongs_to_committee", nullable = false)
    private Boolean belongsToCommittee;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "profile_id", nullable = false)
    @ToString.Exclude
    private Profile profile;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subcommittee_id")
    @ToString.Exclude
    private Subcommittee subcommittee;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Enrollment> enrollments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Audit> audits = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<DeactivateActivityType> DeactivateActivityTypes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Payment> payments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Phone> phones = new ArrayList<>();

}