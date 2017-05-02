package edu.isep.genielogiciel.web;

import java.util.Map;

import edu.isep.genielogiciel.models.User;
import edu.isep.genielogiciel.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.isep.ldap.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {
        return "user/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView index(@RequestParam("login") String login, @RequestParam("password") String password) {
        LDAPAccess access = new LDAPAccess();

        try {
            LDAPObject object = access.LDAPget(login, password);

            if (object != null) {
                User user = userRepository.findByNumber(Integer.valueOf(object.getNumber()));

                if (user == null) {
                    user = new User();
                }

                user.setNumber(Integer.valueOf(object.getNumber()));
                user.setLastName(object.getLogin());
                user.setMail(object.getMail());
                user.setFirstName(object.getPrenom());
                user.setLastName(object.getNomFamille());

                userRepository.save(user);

                return new ModelAndView("user/success", "user", user);
            }
        } catch(Exception e) {
            logger.error(e.getMessage());
        }

        return new ModelAndView("user/error");
    }

}
