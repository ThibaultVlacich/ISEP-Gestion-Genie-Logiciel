package edu.isep.genielogiciel.web;


import edu.isep.genielogiciel.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Abstract class GLController
 *
 * Implements some default attributes / methods
 * that should be available in all controllers
 *
 * @author Thibault Vlacich <thibault.vlacich@isep.fr>
 */
public abstract class GLController {

    @ModelAttribute("currentUser")
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal != null && principal instanceof User) {
            return (User) principal;
        }

        return null;
    }

}
