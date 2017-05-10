package edu.isep.genielogiciel.web;

import edu.isep.genielogiciel.models.Team;
import edu.isep.genielogiciel.models.User;
import edu.isep.genielogiciel.repositories.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = {"/create", "/create/"}, method = RequestMethod.GET)
    private String create() {
        return "team/create";
    }

    @RequestMapping(value = {"/create", "/create/"}, method = RequestMethod.POST)
    private ModelAndView create(@RequestParam("name") String name, @RequestParam("size") Integer size) {
        Team team = new Team();
        team.setName(name);
        team.setSize(size);

				team.setMailsLeft(5);
				team.setTimeLeft(120);

        teamRepository.save(team);

        return new ModelAndView("redirect:/team?created");
    }

    @RequestMapping({"/register", "/register/"})
    private ModelAndView register(@RequestParam("id") Integer id, @RequestParam(value = "confirm", required = false) Boolean confirm) {
        Team team = teamRepository.findById(id);

        if (team == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        if (confirm != null && confirm) {
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            team.addMember(currentUser);
            teamRepository.save(team);

            return new ModelAndView("redirect:/team?registered&team_id="+id);
        }

        return new ModelAndView("team/register", "team", team);
    }

		@RequestMapping(value = {"/detail", "/detail/"}, method = RequestMethod.GET)
		private ModelAndView detail(@RequestParam("id") Integer id) {
				Team team = teamRepository.findById(id);

				if (team == null) {
						return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
				}

				return new ModelAndView("team/detail", "team", team);

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
