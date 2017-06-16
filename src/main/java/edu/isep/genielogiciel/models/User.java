package edu.isep.genielogiciel.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private Integer number;

    // Role can be: "GUEST", "STUDENT", "CLIENT", "TEACHER"
    private String role;

    private String login;
    private String password;
    private String mail;

    private String firstName;
    private String lastName;

    @ManyToOne
    private Team team;

    public Boolean hasTeam() {
        return (this.team != null);
    }

    @Override
    public String toString() {
        return login + " (" + number + ")";
    }
}