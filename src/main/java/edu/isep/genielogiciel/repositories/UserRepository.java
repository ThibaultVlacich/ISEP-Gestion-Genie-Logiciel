package edu.isep.genielogiciel.repositories;

import edu.isep.genielogiciel.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findById(Integer id);
    User findByLogin(String login);
    User findByNumber(Integer number);
    Iterable<User> findByRole(String role);
}