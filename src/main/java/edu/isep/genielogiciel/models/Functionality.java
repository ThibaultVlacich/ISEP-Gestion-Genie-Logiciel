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
    private Integer priority;

    @ManyToOne
    private Subject subject;
}
