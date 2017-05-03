package edu.isep.genielogiciel.repositories;

import edu.isep.genielogiciel.models.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

    public Team findById(Integer id);
}