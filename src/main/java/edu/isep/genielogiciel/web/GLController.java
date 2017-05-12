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

    private static User currentUser = null;

    @ModelAttribute("currentUser")
    public static User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (currentUser == null && principal instanceof User) {
            currentUser = (User) principal;
        }

        return currentUser;
    }

}
