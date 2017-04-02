package edu.isep.genielogiciel.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.isep.genielogiciel.service.IndexService;

@Controller
public class IndexController {

	private final Logger logger = LoggerFactory.getLogger(IndexController.class);
	private final IndexService service;

	@Autowired
	public IndexController(IndexService service) {
		this.service = service;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Map<String, Object> model) {

		logger.debug("index() is executed!");

		return "index";
	}

}
