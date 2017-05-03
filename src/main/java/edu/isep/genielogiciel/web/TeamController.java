package edu.isep.genielogiciel.web;
import java.util.Map;

import javax.sql.DataSource;

import edu.isep.genielogiciel.models.Team;
import edu.isep.genielogiciel.models.User;
import edu.isep.genielogiciel.repositories.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.isep.ldap.*;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/team")
public class TeamController {

	private final Logger logger = LoggerFactory.getLogger(TeamController.class);

	@RequestMapping(value = "/myTeam")
    public String myTeam(Map<String, Object> model) {
		return "team/myTeam";
	}

    @RequestMapping(value = "/allTeam")
    public String allTeam(Map<String, Object> model) {
		return "team/allTeam";
	}

    @RequestMapping(value = "/addTeam", method = RequestMethod.GET)
    public String addTeam(Map<String, Object> model) {
        return "team/addTeam";
    }

    @RequestMapping(value = "/addTeam", method = RequestMethod.POST)
    public ModelAndView addTeam(@RequestParam("nbrEleve") Integer nbrEleve, @RequestParam("assignSubject") String assignSubject) {
        return new ModelAndView("team/allTeam");
    }
}
