package edu.isep.genielogiciel.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Boris on 02/06/2017.
 */
@Data
@Entity
public class Disponibility {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String week;
    private String lundi;
    private String mardi;
    private String mercredi;
    private String jeudi;
    private String vendredi;
}
