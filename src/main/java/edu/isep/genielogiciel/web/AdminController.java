package edu.isep.genielogiciel.web;

import edu.isep.genielogiciel.models.User;
import edu.isep.genielogiciel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_TEACHER')")
public class AdminController extends GLController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("")
    public String index() {
        return "admin/index";
    }

    @RequestMapping("/users")
    public ModelAndView users() {
        return new ModelAndView("admin/users", "users", userRepository.findAll());
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView user(@RequestParam("id") Integer id) {
        User user = userRepository.findById(id);

        if (user == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        return new ModelAndView("admin/user", "user", user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ModelAndView user(@RequestParam("id") Integer id, @RequestParam("role") String role) {
        User user = userRepository.findById(id);

        if (user == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        user.setRole(role);
        userRepository.save(user);

        return new ModelAndView("redirect:/admin/users?updated");
    }

}
