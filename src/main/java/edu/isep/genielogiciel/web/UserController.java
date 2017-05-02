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
    public String index(@RequestParam("login") String login, @RequestParam("password") String password) {
        LDAPAccess access = new LDAPAccess();

        try {
            LDAPObject object = access.LDAPget(login, password);

            if (object == null) {
                // Invalid login
                logger.info("Invalid login");
            } else {
                User n = new User();
                n.setId(Integer.valueOf(object.getNumber()));
                n.setLastName(object.getLogin());
                n.setMail(object.getMail());
                n.setFirstName(object.getPrenom());
                n.setLastName(object.getNomFamille());

                userRepository.save(n);
            }
        } catch(Exception e) {
            logger.error(e.getMessage());
        }

        return "user/login";
    }

}
