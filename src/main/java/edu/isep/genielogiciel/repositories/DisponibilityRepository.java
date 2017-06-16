package edu.isep.genielogiciel.repositories;


import edu.isep.genielogiciel.models.Disponibility;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Boris on 02/06/2017.
 */
public interface DisponibilityRepository extends CrudRepository<Disponibility, Long> {
    public Disponibility findById(Integer id);
}
