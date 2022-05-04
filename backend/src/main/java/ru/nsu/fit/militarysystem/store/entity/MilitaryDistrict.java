package ru.nsu.fit.militarysystem.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "military_districts", indexes = {
        @Index(name = "military_districts_name_key", columnList = "name", unique = true)
})
public class MilitaryDistrict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Short id;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date_of_formation", nullable = false)
    private LocalDate dateOfFormation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "headquarters_dislocation_id", nullable = false)
    @ToString.Exclude
    private Dislocation headquartersDislocation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "commander_id", nullable = false)
    @ToString.Exclude
    private MilitaryMan commander;
}