package edu.isep.genielogiciel.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Functionality {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    @ManyToOne
    private Subject subject;
}
