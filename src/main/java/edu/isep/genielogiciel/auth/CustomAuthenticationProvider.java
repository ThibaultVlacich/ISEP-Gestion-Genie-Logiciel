package edu.isep.genielogiciel.auth;

import edu.isep.genielogiciel.models.User;
import edu.isep.genielogiciel.repositories.UserRepository;
import edu.isep.ldap.LDAPAccess;
import edu.isep.ldap.LDAPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserRepository userRepository;

    private static Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName().trim();
        String password = authentication.getCredentials().toString().trim();

        LDAPAccess access = new LDAPAccess();

        try {
            // Verify credentials on the ISEP LDAP server
            LDAPObject object = access.LDAPget(userName, password);

            // Bypass to test outside ISEP
            // LDAPObject object = new LDAPObject(userName, password, "Name", "Last Name", "First Name", "type", "0000", "xxx@isep.fr");

            if (object != null) {
                // User matching those credentials has been found on the server
                // Check if it exists on out local database
                User user = userRepository.findByLogin(object.getLogin());

                if (user == null) {
                    // User doesn't already exist
                    // Create new user and assign it basic access (GUEST)
                    user = new User();
                    user.setRole("GUEST");
                    user.setLogin(object.getLogin());
                }

                // Update information on user from the ones returned by the LDAP server
                user.setNumber(Integer.valueOf(object.getNumber()));
                user.setMail(object.getMail());
                user.setFirstName(object.getPrenom());
                user.setLastName(object.getNomFamille());

                // Save / Update user in local database
                userRepository.save(user);

                // Authenticate user
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole()));

                Authentication auth = new UsernamePasswordAuthenticationToken(user, password, authorities);

                return auth;
            }
        } catch(Exception e) {
            logger.error(e.getMessage());
        }

        // No user matching given credentials has been found
        // or an error happened
        return null;
    }

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}