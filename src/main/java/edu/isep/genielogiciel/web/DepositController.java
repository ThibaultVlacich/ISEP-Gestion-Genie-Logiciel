package edu.isep.genielogiciel.web;

import edu.isep.genielogiciel.models.Deposit;
import edu.isep.genielogiciel.models.User;
import edu.isep.genielogiciel.repositories.DepositRepository;
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

/**
 * Created by Boris on 10/06/2017.
 */
@Controller
@RequestMapping(value = "/deposit")
public class DepositController extends GLController {

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("**")
    public ModelAndView all() {

        return new ModelAndView("deposit/all","deposits", depositRepository.findAll());
    }

    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_CLIENT')")
    @RequestMapping(value = {"/create", "/create/"}, method = RequestMethod.GET)
    public String create() {
        return "deposit/create";
    }

    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_CLIENT')")
    @RequestMapping(value = {"/create", "/create/"}, method = RequestMethod.POST)
    public ModelAndView create(@RequestParam("name") String name, @RequestParam("enddate") String enddate, @RequestParam("objet") String objet) {
        Deposit deposit = new Deposit();

        deposit.setName(name);
        deposit.setEnddate(enddate);
        deposit.setObjet(objet);

        User user = getCurrentUser();

        deposit.setAuthor(user);

        depositRepository.save(deposit);

        return new ModelAndView("redirect:/deposit/all?created");
    }

    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_CLIENT')")
    @RequestMapping(value = {"/edit", "/edit/"}, method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam("id") Integer id) {

        Deposit deposit = depositRepository.findById(id);

        if (deposit == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        Map<String, Object> model = new HashMap<>();
        model.put("deposit", deposit);

        return new ModelAndView("deposit/edit", model);
    }

    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_CLIENT')")
    @RequestMapping(value = {"/edit", "/edit/"}, method = RequestMethod.POST)
    public ModelAndView edit(@RequestParam("id") Integer id, @RequestParam("name") String name, @RequestParam("enddate") String enddate, @RequestParam("objet") String objet) {
        Deposit deposit = depositRepository.findById(id);

        deposit.setName(name);
        deposit.setEnddate(enddate);
        deposit.setObjet(objet);

        depositRepository.save(deposit);

        return new ModelAndView("redirect:/deposit/all?edited");
    }

    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_CLIENT')")
    @RequestMapping({"/delete", "/delete/"})
    private ModelAndView delete(@RequestParam("id") Integer id, @RequestParam(value = "confirm", required = false) Boolean confirm) {
        Deposit deposit = depositRepository.findById(id);

        if (deposit == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        if (confirm != null && confirm) {
            depositRepository.delete(deposit);

            return new ModelAndView("redirect:/deposit?deleted");
        }

        return new ModelAndView("deposit/delete", "deposit", deposit);
    }

    @RequestMapping(value = {"/detail", "/detail/"}, method = RequestMethod.GET)
    public ModelAndView detail(@RequestParam("id") Integer id) {
        Deposit deposit = depositRepository.findById(id);

        if (deposit == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        return new ModelAndView("deposit/detail", "deposit", deposit);

    }

    @RequestMapping(value = {"/deposit", "/deposit/"}, method = RequestMethod.GET)
    public ModelAndView deposit(@RequestParam("id") Integer id) {
        Deposit deposit = depositRepository.findById(id);

        if (deposit == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        return new ModelAndView("deposit/deposit", "deposit", deposit);

    }

    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_CLIENT')")
    @RequestMapping(value = {"/close", "/close/"})
    public ModelAndView close(@RequestParam("id") Integer id, @RequestParam(value = "confirm", required = false) Boolean confirm) {
        Deposit deposit = depositRepository.findById(id);

        if (deposit == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        if (confirm != null && confirm) {
            deposit.setState("Closed");
            depositRepository.save(deposit);
            return new ModelAndView("redirect:/deposit?closed");
        }
        return new ModelAndView("deposit/all", "deposit", deposit);

    }
}
