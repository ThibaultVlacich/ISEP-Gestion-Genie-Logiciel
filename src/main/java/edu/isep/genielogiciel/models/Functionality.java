package edu.isep.genielogiciel.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Functionality {

    @Id
    private Integer id;
    private String name;

    public Functionality(String name) {
        this.name = name;
    }
}
