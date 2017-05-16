package edu.isep.genielogiciel.web;

import edu.isep.genielogiciel.models.Appointment;
import edu.isep.genielogiciel.models.Subject;
import edu.isep.genielogiciel.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Boris on 16/05/2017.
 */
@Controller
@RequestMapping(value = "/appointment")
public class AppointmentController extends GLController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @RequestMapping("**")
    public ModelAndView all() {
        return new ModelAndView("appointment/all", "appointments", appointmentRepository.findAll());
    }

    @RequestMapping(value = {"/create", "/create/"}, method = RequestMethod.GET)
    public String create() {
        return "appointment/create";
    }

    @RequestMapping(value = {"/create", "/create/"}, method = RequestMethod.POST)
    public ModelAndView create(@RequestParam("object") String object, @RequestParam("date") String date, @RequestParam("hours") String hours,@RequestParam("time") String time) {
        Appointment appointment = new Appointment();
        appointment.setObject(object);
        appointment.setDate(date);
        appointment.setHours(hours);
        appointment.setTime(time);
        appointment.setTeam(getCurrentUser().getTeam());

        appointmentRepository.save(appointment);

        return new ModelAndView("redirect:/team/details");
    }
    @RequestMapping({"/refuse", "/refuse/"})
    private ModelAndView refuse(@RequestParam("id") Integer id, @RequestParam(value = "confirm", required = false) Boolean confirm) {
        Appointment appointment = appointmentRepository.findById(id);

        if (appointment == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        if (confirm != null && confirm) {
            appointmentRepository.delete(appointment);

            return new ModelAndView("redirect:/appointment?deleted");
        }

        return new ModelAndView("appointment/refuse", "appointment", appointment);
    }
    @RequestMapping({"/valid", "/valid/"})
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ModelAndView valid(@RequestParam("id") Integer id, @RequestParam(value = "confirm", required = false) Boolean confirm) {
        Appointment appointment = appointmentRepository.findById(id);

        if (appointment == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        if (confirm != null && confirm) {
            appointment.setValid(true);

            return new ModelAndView("redirect:/appointment?validated");
        }

        return new ModelAndView("appointment/valid", "appointment", appointment);
    }

}
