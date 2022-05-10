package ru.nsu.fit.militarysystem.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "military_men", indexes = {
        @Index(name = "military_men_identification_number_key", columnList = "identification_number", unique = true)
})
public class MilitaryMan implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "second_name", nullable = false)
    private String secondName;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "date_of_award", nullable = false)
    private LocalDate dateOfAward;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "identification_number")
    private String identificationNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rank_id", nullable = false)
    private Rank rank;

    @ManyToMany
    @JoinTable(name = "military_men__military_specialties",
               joinColumns = @JoinColumn(name = "military_man_id"),
               inverseJoinColumns = @JoinColumn(name = "military_specialty_id"))
    private Set<MilitarySpecialty> militarySpecialties = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "military_men__military_formations",
               joinColumns = @JoinColumn(name = "military_man_id"),
               inverseJoinColumns = @JoinColumn(name = "military_formation_id"))
    private Set<MilitaryFormation> militaryFormations = new LinkedHashSet<>();
}