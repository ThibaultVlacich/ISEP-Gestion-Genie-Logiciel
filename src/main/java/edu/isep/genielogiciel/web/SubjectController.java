package edu.isep.genielogiciel.web;

import edu.isep.genielogiciel.models.Functionality;
import edu.isep.genielogiciel.models.Subject;
import edu.isep.genielogiciel.repositories.FunctionalityRepository;
import edu.isep.genielogiciel.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/subject")
public class SubjectController extends GLController {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private FunctionalityRepository functionalityRepository;

    @RequestMapping("**")
    private ModelAndView all() {
        return new ModelAndView("subject/all", "subjects", subjectRepository.findAll());
    }

    @RequestMapping(value = {"/create", "/create/"}, method = RequestMethod.GET)
    private String create() {
        return "subject/create";
    }

    @RequestMapping(value = {"/create", "/create/"}, method = RequestMethod.POST)
    private ModelAndView create(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("functionality") String[] functionalities) {
        Subject subject = new Subject();
        subject.setName(name);
        subject.setDescription(description);

        for (String functionalityName: functionalities) {
            Functionality functionality = new Functionality(functionalityName);

            functionalityRepository.save(functionality);

            subject.addFunctionality(functionality);
        }

        subjectRepository.save(subject);

        return new ModelAndView("redirect:/subject?created");
    }

    @RequestMapping({"/delete", "/delete/"})
    private ModelAndView delete(@RequestParam("id") Integer id, @RequestParam(value = "confirm", required = false) Boolean confirm) {
        Subject subject = subjectRepository.findById(id);

        if (subject == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        if (confirm != null && confirm) {
            subjectRepository.delete(subject);

            return new ModelAndView("redirect:/subject?deleted");
        }

        return new ModelAndView("subject/delete", "subject", subject);
    }
}
