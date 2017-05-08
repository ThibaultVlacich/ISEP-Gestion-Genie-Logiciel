package edu.isep.genielogiciel.web;

import edu.isep.genielogiciel.models.Team;
import edu.isep.genielogiciel.repositories.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller
@RequestMapping(value = "/team")
public class TeamController {

	private final Logger logger = LoggerFactory.getLogger(TeamController.class);
    @Autowired
    private TeamRepository teamRepository;

	@RequestMapping("**")
    private ModelAndView all() {
	    return new ModelAndView("team/all", "teams", teamRepository.findAll());
    }

    @RequestMapping(value = {"/create", "/create"}, method = RequestMethod.GET)
    private String create() {
        return "team/create";
    }

    @RequestMapping({"/delete", "/delete/"})
    private ModelAndView delete(@RequestParam("id") Integer id, @RequestParam(value = "confirm", required = false) Boolean confirm) {
        Team team = teamRepository.findById(id);

        if (team == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        if (confirm != null && confirm) {
            teamRepository.delete(team);

            return new ModelAndView("redirect:/team?deleted");
        }

        return new ModelAndView("team/delete", "team", team);
    }
}
