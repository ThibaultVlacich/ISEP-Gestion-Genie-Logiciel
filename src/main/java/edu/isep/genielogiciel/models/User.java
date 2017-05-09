package edu.isep.genielogiciel.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    @Override
    public String toString() {
        return login + " (" + number + ")";
    }
}