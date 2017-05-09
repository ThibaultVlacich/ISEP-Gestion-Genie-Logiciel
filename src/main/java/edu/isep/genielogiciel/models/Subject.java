package edu.isep.genielogiciel.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/*
 * Created by HM on 02/05/17.
 */

@Entity
@Data
public class Subject {

    public static class FunctionalityNotInSubject extends RuntimeException {
        public FunctionalityNotInSubject() {
            super("Functionality not in subject");
        }
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;

    @OneToOne
    private User client;

    @OneToMany
    private Set<Functionality> functionalities = new HashSet<>();

    public void addFunctionality(Functionality functionality) {
        this.functionalities.add(functionality);
    }

    public void removeFunctionality(Functionality functionality) throws FunctionalityNotInSubject {
        if (this.functionalities.contains(functionality)) {
            this.functionalities.remove(functionality);

            return;
        }

        throw new FunctionalityNotInSubject();
    }
}

