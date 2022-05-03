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
@Table(name = "subjects", indexes = {
        @Index(name = "subjects_name_key", columnList = "name", unique = true)
})
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Short id;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "name", nullable = false)
    private String name;
}