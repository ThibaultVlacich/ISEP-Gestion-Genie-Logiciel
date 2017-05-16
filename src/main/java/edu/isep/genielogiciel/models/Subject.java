package edu.isep.genielogiciel.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Created by HM on 02/05/17.
 */

@Entity
@Data
public class Subject {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    private String description;

    @OneToOne
    private User client;

    @OneToMany(mappedBy = "subject")
    private List<Team> teams;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<Functionality> functionalities;

}

