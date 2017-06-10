package edu.isep.genielogiciel.models;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Boris on 10/06/2017.
 */
@Data
@Entity
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String objet;
    private String enddate;

    private String state="Waiting";

    @OneToOne
    private User author;
}
