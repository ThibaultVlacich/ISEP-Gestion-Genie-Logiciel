package edu.isep.genielogiciel.models;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Boris on 16/05/2017.
 */
@Data
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Team team;


    private String object;
    private String date;
    private String hours;
    private String time;
    private String state="Waiting";
    private String comment;


}
