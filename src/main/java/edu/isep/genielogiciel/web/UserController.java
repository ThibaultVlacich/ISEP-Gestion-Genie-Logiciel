package edu.isep.genielogiciel.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController extends GLController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {
        return "user/login";
    }

}
