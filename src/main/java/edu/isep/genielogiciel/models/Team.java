package edu.isep.genielogiciel.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * Created by HM on 02/05/17.
 */

@Entity
@Data
public class Team {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;
    private Integer nbrEleves;
    private Integer idSubject;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setNbrEleves(Integer nbrEleves) {
        this.nbrEleves = nbrEleves;
    }
    public Integer getNbrEleves() {
        return nbrEleves;
    }

    public void setIdSubject(Integer idSubject) {
        this.idSubject = idSubject;
    }
    public Integer getIdSubject() {
        return idSubject;
    }

}

