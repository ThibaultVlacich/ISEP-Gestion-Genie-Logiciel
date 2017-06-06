package edu.isep.genielogiciel.web;

import edu.isep.genielogiciel.models.Disponibility;
import edu.isep.genielogiciel.models.Team;
import edu.isep.genielogiciel.repositories.DisponibilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @RequestMapping(value = {"/create", "/create/"}, method = RequestMethod.GET)
    public String create() {
        return "disponibility/create";
    }

    @RequestMapping(value = {"/create", "/create/"}, method = RequestMethod.POST)
    public ModelAndView create(@RequestParam("lundi") String lundi, @RequestParam("mardi") String mardi, @RequestParam("mercredi") String mercredi, @RequestParam("jeudi") String jeudi, @RequestParam("vendredi") String vendredi) {
        Disponibility disponibility = new Disponibility();

        disponibility.setLundi(lundi);
        disponibility.setMardi(mardi);
        disponibility.setMercredi(mercredi);
        disponibility.setJeudi(jeudi);
        disponibility.setVendredi(vendredi);

        disponibilityRepository.save(disponibility);

        return new ModelAndView("redirect:/disponibility/all");
    }

    

}
