package edu.isep.genielogiciel.repositories;

import edu.isep.genielogiciel.models.Functionality;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionalityRepository extends CrudRepository<Functionality, Long> {

    public Functionality findById(Integer id);
}