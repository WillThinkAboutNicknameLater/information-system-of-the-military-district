package ru.nsu.fit.militarysystem.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "ranks", indexes = {
        @Index(name = "ranks_name_staff_category_id_rank_category_id_key", columnList = "name, staff_category_id, rank_category_id", unique = true)
})
public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Short id;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "staff_category_id", nullable = false)
    @ToString.Exclude
    private StaffCategory staffCategory;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rank_category_id", nullable = false)
    @ToString.Exclude
    private RankCategory rankCategory;
}