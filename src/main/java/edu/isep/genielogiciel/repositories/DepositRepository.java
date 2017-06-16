package edu.isep.genielogiciel.repositories;

import edu.isep.genielogiciel.models.Deposit;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Boris on 10/06/2017.
 */
public interface DepositRepository extends CrudRepository<Deposit, Long> {
    public Deposit findById(Integer id);
}
