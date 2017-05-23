package edu.isep.genielogiciel.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/*
 * Created by HM on 02/05/17.
 */

@Entity
@Data
public class Team {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private Integer size;
    private Integer mailsLeft;
    private Integer timeLeft;

    private Boolean valid = false;

    @ManyToOne
    private Subject subject;

    @OneToMany(mappedBy = "team")
    private List<User> members;

    @OneToMany(mappedBy = "team")
    private List<Appointment> appointments;
    
}