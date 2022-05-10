package ru.nsu.fit.militarysystem.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "military_formations", indexes = {
        @Index(name = "military_formations_name_key", columnList = "name", unique = true)
})
public class MilitaryFormation implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date_of_formation", nullable = false)
    private LocalDate dateOfFormation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "military_formation_type_id", nullable = false)
    private MilitaryFormationType militaryFormationType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "commander_id", nullable = false)
    private MilitaryMan commander;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dislocation_id", nullable = false)
    private Dislocation dislocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private MilitaryFormation parent;
}