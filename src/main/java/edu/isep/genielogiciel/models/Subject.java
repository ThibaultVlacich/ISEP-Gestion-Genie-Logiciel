package edu.isep.genielogiciel.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/*
 * Created by HM on 02/05/17.
 */

@Entity
@Data
public class Subject {

    public static class UserNotInSubject extends RuntimeException {
        public UserNotInSubject() {
            super("User not in Subject");
        }
    }

    public static class SubjectFull extends RuntimeException {
        public SubjectFull() {
            super("Subject is full");
        }
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;
    private Integer size;

    //private Subject subject;

    @OneToMany
    private Set<User> members = new HashSet<>(0);

    public void addMember(User user) throws SubjectFull {
        if (this.members.size() < this.size) {
            this.members.add(user);

            return;
        }

        throw new SubjectFull();
    }

    public void removeMember(User user) throws UserNotInSubject {
        if (this.members.contains(user)) {
            this.members.remove(user);

            return;
        }

        throw new UserNotInSubject();
    }

}

