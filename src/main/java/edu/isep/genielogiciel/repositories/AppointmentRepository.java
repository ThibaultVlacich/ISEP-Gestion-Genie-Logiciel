package edu.isep.genielogiciel.repositories;

import edu.isep.genielogiciel.models.Appointment;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Boris on 16/05/2017.
 */
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
    public Appointment findById(Integer id);
}
