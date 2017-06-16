package edu.isep.genielogiciel.web;

import edu.isep.genielogiciel.models.Appointment;
import edu.isep.genielogiciel.models.Team;
import edu.isep.genielogiciel.repositories.AppointmentRepository;
import edu.isep.genielogiciel.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Boris on 16/05/2017.
 */
@Controller
@RequestMapping(value = "/appointment")
public class AppointmentController extends GLController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @RequestMapping("**")
    public ModelAndView all() {
        return new ModelAndView("appointment/all", "appointments", appointmentRepository.findAll());
    }

    @RequestMapping(value = {"/create", "/create/"}, method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public String create() {
        return "appointment/create";
    }

    @RequestMapping(value = {"/create", "/create/"}, method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ModelAndView create(@RequestParam("object") String object, @RequestParam("date") String date, @RequestParam("hours") String hours,@RequestParam("time") String time) {
        Appointment appointment = new Appointment();
        appointment.setObject(object);
        appointment.setDate(date);
        appointment.setHours(hours);
        appointment.setTime(time);
        appointment.setTeam(getCurrentUser().getTeam());

        appointmentRepository.save(appointment);

        return new ModelAndView("redirect:/appointment/all");
    }

    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_CLIENT')")
    @RequestMapping(value = {"/meeting", "/meeting/"}, method = RequestMethod.GET)
    public ModelAndView meeting(@RequestParam("id") Integer id) {

        Appointment appointment = appointmentRepository.findById(id);

        if (appointment == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        Map<String, Object> model = new HashMap<>();
        model.put("appointment", appointment);

        return new ModelAndView("appointment/meeting", model);
    }

    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_CLIENT')")
    @RequestMapping(value = {"/meeting", "/meeting/"}, method = RequestMethod.POST)
    public ModelAndView meeting(@RequestParam("id") Integer id, @RequestParam("timer") Integer timer, @RequestParam("comment") String comment) {
        Appointment appointment = appointmentRepository.findById(id);
        Team team = appointment.getTeam();

        if (appointment == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        if (team == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        appointment.setComment(comment);

        Integer meetingTime = team.getTimeLeft();
        team.setTimeLeft(meetingTime-timer);

        appointment.setState("Done");
        appointmentRepository.save(appointment);

        teamRepository.save(team);


        return new ModelAndView("team/detail", "team", team);
    }

    @RequestMapping({"/refuse", "/refuse/"})
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ModelAndView refuse(@RequestParam("id") Integer id, @RequestParam(value = "confirm", required = false) Boolean confirm) {
        Appointment appointment = appointmentRepository.findById(id);

        if (appointment == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        if (confirm != null && confirm) {
            appointment.setState("Refused");
            appointmentRepository.save(appointment);
            return new ModelAndView("redirect:/appointment?refused");
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
            appointment.setState("Validated");
            appointmentRepository.save(appointment);
            return new ModelAndView("redirect:/appointment?validated");
        }

        return new ModelAndView("appointment/valid", "appointment", appointment);
    }

}
