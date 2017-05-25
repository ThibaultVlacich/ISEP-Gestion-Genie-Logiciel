package edu.isep.genielogiciel.web;

import edu.isep.genielogiciel.forms.SubjectForm;
import edu.isep.genielogiciel.models.Functionality;
import edu.isep.genielogiciel.models.Subject;
import edu.isep.genielogiciel.repositories.FunctionalityRepository;
import edu.isep.genielogiciel.repositories.SubjectRepository;
import edu.isep.genielogiciel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/subject")
public class SubjectController extends GLController {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private FunctionalityRepository functionalityRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("**")
    private ModelAndView all() {
        return new ModelAndView("subject/all", "subjects", subjectRepository.findAll());
    }

    @RequestMapping(value = {"/create", "/create/"}, method = RequestMethod.GET)
    private ModelAndView create() {
        Map<String, Object> model = new HashMap<>();
        model.put("clients", userRepository.findByRole("CLIENT"));
        model.put("subjectForm", new SubjectForm());

        return new ModelAndView("subject/create", model);
    }

    @RequestMapping(value = {"/create", "/create/"}, method = RequestMethod.POST)
    private ModelAndView create(@ModelAttribute("subjectForm") SubjectForm subjectForm) {
        Subject subject = new Subject();
        subject.setName(subjectForm.getName());
        subject.setDescription(subjectForm.getDescription());
        subject.setClient(subjectForm.getClient() == 0 ? null : userRepository.findById(subjectForm.getClient()));

        subjectRepository.save(subject);

        if (subjectForm.getFunctionalities() != null) {
            Integer currentPriority = 1;

            for (Functionality functionality : subjectForm.getFunctionalities()) {
                functionality.setId(null);
                functionality.setPriority(currentPriority);
                functionality.setSubject(subject);

                functionalityRepository.save(functionality);

                currentPriority++;
            }
        }

        return new ModelAndView("redirect:/subject?created");
    }

    @RequestMapping(value = {"/edit", "/edit/"}, method = RequestMethod.GET)
    private ModelAndView edit(@RequestParam("id") Integer id) {
        Subject subject = subjectRepository.findById(id);

        if (subject == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        Map<String, Object> model = new HashMap<>();
        model.put("subject", subject);
        model.put("clients", userRepository.findByRole("CLIENT"));
        model.put("subjectForm", new SubjectForm());

        return new ModelAndView("subject/edit", model);
    }

    @RequestMapping(value = {"/edit", "/edit/"}, method = RequestMethod.POST)
    private ModelAndView edit(@RequestParam("id") Integer id, @RequestParam(value = "functionalities_to_delete", required = false) String functionalities_to_delete, @ModelAttribute("subjectForm") SubjectForm subjectForm) {
        Subject subject = subjectRepository.findById(id);

        if (subject == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        subject.setName(subjectForm.getName());
        subject.setDescription(subjectForm.getDescription());
        subject.setClient(subjectForm.getClient() == 0 ? null : userRepository.findById(subjectForm.getClient()));

        subjectRepository.save(subject);

        if (functionalities_to_delete != null) {
            String[] funcIds = functionalities_to_delete.split(",");

            for (String funcId : funcIds) {
                if (funcId.isEmpty())
                    continue;

                Functionality functionality = functionalityRepository.findById(Integer.parseInt(funcId));

                if (functionality != null)
                    functionalityRepository.delete(functionality);
            }
        }

        if (subjectForm.getFunctionalities() != null) {
            Integer currentPriority = 1;

            for (Functionality functionality : subjectForm.getFunctionalities()) {
                if (functionality.getId() == 0) {
                    // New functionality
                    functionality.setId(null);
                }

                functionality.setPriority(currentPriority);
                functionality.setSubject(subject);

                functionalityRepository.save(functionality);

                currentPriority++;
            }
        }

        return new ModelAndView("redirect:/subject?updated");
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
    @RequestMapping(value = {"/detail", "/detail/"}, method = RequestMethod.GET)
    public ModelAndView detail(@RequestParam("id") Integer id) {
        Subject subject = subjectRepository.findById(id);

        if (subject == null) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }

        return new ModelAndView("subject/detail", "subject", subject);

    }
}
