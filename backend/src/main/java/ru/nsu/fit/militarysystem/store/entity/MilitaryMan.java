package ru.nsu.fit.militarysystem.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "military_men")
public class MilitaryMan {
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rank_id", nullable = false)
    @ToString.Exclude
    private Rank rank;

    @ManyToMany
    @JoinTable(name = "military_men__military_specialties",
            joinColumns = @JoinColumn(name = "military_man_id"),
            inverseJoinColumns = @JoinColumn(name = "military_specialty_id"))
    @ToString.Exclude
    private Set<MilitarySpecialty> militarySpecialties = new LinkedHashSet<>();
}