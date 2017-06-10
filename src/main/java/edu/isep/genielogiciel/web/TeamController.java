package edu.isep.genielogiciel.web;

import edu.isep.genielogiciel.models.Team;
import edu.isep.genielogiciel.models.User;
import edu.isep.genielogiciel.repositories.SubjectRepository;
import edu.isep.genielogiciel.repositories.TeamRepository;
import edu.isep.genielogiciel.repositories.UserRepository;
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

@Controller
@RequestMapping(value = "/team")
@PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_CLIENT', 'ROLE_TEACHER')") // Guests can't access
public class TeamController extends GLController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @RequestMapping("**")
    public ModelAndView all() {
        return new ModelAndView("team/all", "teams", teamRepository.findAll());
    }

    
    @RequestMapping(value = {"/add", "/add/"}, method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ModelAndView add(@RequestParam("id") Integer id) {

        Team team = teamRepository.findById(id);

        if (team == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        Map<String, Object> model = new HashMap<>();
        model.put("team", team);
        model.put("students", userRepository.findByRole("STUDENT"));

        return new ModelAndView("team/add", model);
    }

    @RequestMapping(value = {"/add", "/add/"}, method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ModelAndView add(@RequestParam("id") Integer id, @RequestParam("id_student") Integer id_student) {
        User user = userRepository.findById(id_student);
        Team team = teamRepository.findById(id);

        if (team == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        if (user == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        user.setTeam(team);
        userRepository.save(user);

        return new ModelAndView("redirect:/team/detail?added&id="+id);
    }


    @RequestMapping(value = {"/create", "/create/"}, method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ModelAndView create() {
        return new ModelAndView("team/create", "subjects", subjectRepository.findAll());
    }

    @RequestMapping(value = {"/create", "/create/"}, method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ModelAndView create(@RequestParam("name") String name, @RequestParam("size") Integer size, @RequestParam("numberMails") Integer numberMails, @RequestParam("meetingTime") Integer meetingTime, @RequestParam(value = "id_subject", required = false) Integer id_subject) {
        Team team = new Team();
        team.setName(name);
        team.setSize(size);

        if(id_subject != null){
            team.setSubject(subjectRepository.findById(id_subject));
        }

        team.setMailsLeft(numberMails);
        team.setTimeLeft(meetingTime);

        teamRepository.save(team);

        return new ModelAndView("redirect:/team?created");
    }

    @RequestMapping({"/register", "/register/"})
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ModelAndView register(@RequestParam("id") Integer id, @RequestParam(value = "confirm", required = false) Boolean confirm) {
        Team team = teamRepository.findById(id);

        if (team == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        if (team.getValid()) {
            return new ModelAndView("error/403", HttpStatus.FORBIDDEN);
        }

        if (confirm != null && confirm) {
            User currentUser = getCurrentUser();

            currentUser.setTeam(team);
            userRepository.save(currentUser);

            return new ModelAndView("redirect:/team?registered&team_id="+id);
        }

        return new ModelAndView("team/register", "team", team);
    }

    @RequestMapping({"/leave", "/leave/"})
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ModelAndView leave(@RequestParam(value = "confirm", required = false) Boolean confirm) {
        User currentUser = getCurrentUser();

        Team team = currentUser.getTeam();

        if (team == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        if (team.getValid()) {
            return new ModelAndView("error/403", HttpStatus.FORBIDDEN);
        }

        if (confirm != null && confirm) {
            currentUser.setTeam(null);
            userRepository.save(currentUser);

            return new ModelAndView("redirect:/team?leaved&team_id="+team.getId());
        }

        return new ModelAndView("team/leave", "team", team);
    }

    @RequestMapping(value = {"/detail", "/detail/"}, method = RequestMethod.GET)
    public ModelAndView detail(@RequestParam("id") Integer id) {
        Team team = teamRepository.findById(id);

        if (team == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        return new ModelAndView("team/detail", "team", team);

    }

    @RequestMapping(value = {"/edit", "/edit/"}, method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam("id") Integer id) {

        Team team = teamRepository.findById(id);

        if (team == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        Map<String, Object> model = new HashMap<>();
        model.put("team", team);
        model.put("subjects", subjectRepository.findAll());

        return new ModelAndView("team/edit", model);
    }

    @RequestMapping(value = {"/edit", "/edit/"}, method = RequestMethod.POST)
    public ModelAndView edit(@RequestParam("id") Integer id, @RequestParam("name") String name, @RequestParam(value = "numberMails", required = false) Integer numberMails, @RequestParam(value = "meetingTime", required = false) Integer meetingTime, @RequestParam(value = "id_subject", required = false) Integer id_subject) {
        Team team = teamRepository.findById(id);

        team.setName(name);

        if(numberMails != null){
            team.setMailsLeft(numberMails);
        }

        if(meetingTime != null){
            team.setTimeLeft(meetingTime);
        }

        if(id_subject != null){
            team.setSubject(subjectRepository.findById(id_subject));
        }

        teamRepository.save(team);

        return new ModelAndView("redirect:/team/detail?edited&id="+id);
    }

    @RequestMapping({"/valid", "/valid/"})
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ModelAndView valid(@RequestParam("id") Integer id, @RequestParam(value = "confirm", required = false) Boolean confirm) {
        Team team = teamRepository.findById(id);

        if (team == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        if (confirm != null && confirm) {
            team.setValid(true);

            teamRepository.save(team);

            return new ModelAndView("redirect:/team?validated");
        }

        return new ModelAndView("team/valid", "team", team);
    }

    @RequestMapping({"/remove", "/remove/"})
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ModelAndView remove(@RequestParam("id") Integer id, @RequestParam(value = "confirm", required = false) Boolean confirm) {
        User user = userRepository.findById(id);
        Team team = user.getTeam();

        if (team == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }


        if (confirm != null && confirm) {
            user.setTeam(null);
            userRepository.save(user);

            return new ModelAndView("redirect:/team/detail?id="+team.getId()+"&removed");
        }

        Map<String, Object> model = new HashMap<>();
        model.put("team", team);
        model.put("user", user);

        return new ModelAndView("team/remove", model);
    }

    @RequestMapping({"/delete", "/delete/"})
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ModelAndView delete(@RequestParam("id") Integer id, @RequestParam(value = "confirm", required = false) Boolean confirm) {
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
