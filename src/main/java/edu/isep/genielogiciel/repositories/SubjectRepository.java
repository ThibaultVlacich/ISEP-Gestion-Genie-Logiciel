package edu.isep.genielogiciel.repositories;

import edu.isep.genielogiciel.models.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {

    public Subject findById(Integer id);
}