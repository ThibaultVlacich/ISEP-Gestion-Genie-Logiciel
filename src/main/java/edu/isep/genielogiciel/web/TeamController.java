package edu.isep.genielogiciel.web;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/team")
public class TeamController {

	private final Logger logger = LoggerFactory.getLogger(TeamController.class);

	@RequestMapping(value = "/myTeam")
  public String myTeam(Map<String, Object> model) {
		return "team/myTeam";
	}

  @RequestMapping(value = "/allTeam")
  public String allTeam(Map<String, Object> model) {
		return "team/allTeam";
	}
}
