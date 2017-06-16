package edu.isep.genielogiciel.forms;

import edu.isep.genielogiciel.models.Functionality;
import lombok.Data;

import java.util.List;

/**
 * Created by thibault on 23/05/2017.
 */
@Data
public class SubjectForm {
    private Integer id;
    private String name;
    private Integer client;
    private String description;
    private List<Functionality> functionalities;
}
