package edu.isep.genielogiciel.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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
    @OrderBy("priority ASC")
    private List<Functionality> functionalities;

}

