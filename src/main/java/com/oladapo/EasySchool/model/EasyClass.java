package com.oladapo.EasySchool.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "class")
public class EasyClass extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private int classId;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @OneToMany(mappedBy = "easyClass", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST, targetEntity = Person.class)
    private Set<Person> persons;
}
