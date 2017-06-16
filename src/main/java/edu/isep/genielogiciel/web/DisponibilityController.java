package edu.isep.genielogiciel.web;

import edu.isep.genielogiciel.models.Disponibility;
import edu.isep.genielogiciel.repositories.DisponibilityRepository;
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
 * Created by Boris on 02/06/2017.
 */
@Controller
@RequestMapping(value = "/disponibility")
public class DisponibilityController extends GLController {

    @Autowired
    private DisponibilityRepository disponibilityRepository;

    @RequestMapping("**")
    public ModelAndView all() {
        return new ModelAndView("disponibility/all", "disponibilities", disponibilityRepository.findAll());
    }

    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_CLIENT')")
    @RequestMapping(value = {"/create", "/create/"}, method = RequestMethod.GET)
    public String create() {
        return "disponibility/create";
    }

    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_CLIENT')")
    @RequestMapping(value = {"/create", "/create/"}, method = RequestMethod.POST)
    public ModelAndView create(@RequestParam("week") String week, @RequestParam("monday") String monday, @RequestParam("tuesday") String tuesday, @RequestParam("wednesday") String wednesday, @RequestParam("thursday") String thursday, @RequestParam("friday") String friday) {
        Disponibility disponibility = new Disponibility();

        disponibility.setWeek(week);
        disponibility.setMonday(monday);
        disponibility.setTuesday(tuesday);
        disponibility.setWednesday(wednesday);
        disponibility.setThursday(thursday);
        disponibility.setFriday(friday);

        disponibilityRepository.save(disponibility);

        return new ModelAndView("redirect:/disponibility/all?created");
    }

    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_CLIENT')")
    @RequestMapping(value = {"/edit", "/edit/"}, method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam("id") Integer id) {

        Disponibility disponibility = disponibilityRepository.findById(id);

        if (disponibility == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        Map<String, Object> model = new HashMap<>();
        model.put("disponibility", disponibility);

        return new ModelAndView("disponibility/edit", model);
    }

    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_CLIENT')")
    @RequestMapping(value = {"/edit", "/edit/"}, method = RequestMethod.POST)
    public ModelAndView edit(@RequestParam("id") Integer id, @RequestParam("week") String week, @RequestParam("monday") String monday, @RequestParam("tuesday") String tuesday, @RequestParam("wenesday") String wenesday, @RequestParam("thursday") String thursday, @RequestParam("friday") String friday) {
        Disponibility disponibility = disponibilityRepository.findById(id);

        disponibility.setWeek(week);
        disponibility.setMonday(monday);
        disponibility.setTuesday(tuesday);
        disponibility.setWednesday(wenesday);
        disponibility.setThursday(thursday);
        disponibility.setFriday(friday);

        disponibilityRepository.save(disponibility);

        return new ModelAndView("redirect:/disponibility/all?edited");
    }

    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_CLIENT')")
    @RequestMapping({"/delete", "/delete/"})
    private ModelAndView delete(@RequestParam("id") Integer id, @RequestParam(value = "confirm", required = false) Boolean confirm) {
        Disponibility disponibility = disponibilityRepository.findById(id);

        if (disponibility == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        if (confirm != null && confirm) {
            disponibilityRepository.delete(disponibility);

            return new ModelAndView("redirect:/disponibility?deleted");
        }

        return new ModelAndView("disponibility/delete", "disponibility",disponibility);
    }
}
