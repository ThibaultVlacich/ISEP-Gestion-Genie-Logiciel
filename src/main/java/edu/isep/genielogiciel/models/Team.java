package edu.isep.genielogiciel.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Created by HM on 02/05/17.
 */

@Entity
@Data
public class Team {

    public static class UserNotInTeam extends RuntimeException {
        public UserNotInTeam() {
            super("User not in team");
        }
    }

    public static class TeamFull extends RuntimeException {
        public TeamFull() {
            super("Team is full");
        }
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;
    private Integer size;
    private Integer mailsLeft;
    private Integer timeLeft;

    @OneToOne
    private Subject subject;

    @OneToMany(mappedBy = "team")
    private List<User> members;

    public void addMember(User user) throws TeamFull {
        if (this.members.size() < this.size) {
            this.members.add(user);

            return;
        }

        throw new TeamFull();
    }

    public void removeMember(User user) throws UserNotInTeam {
        if (this.members.contains(user)) {
            this.members.remove(user);

            return;
        }

        throw new UserNotInTeam();
    }

}
