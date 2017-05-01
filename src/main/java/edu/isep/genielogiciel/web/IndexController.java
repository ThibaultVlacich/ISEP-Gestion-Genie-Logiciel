package edu.isep.genielogiciel.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	private final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping(value = "/")
	public String index() {

		logger.debug("index() is executed!");

		return "index";
	}

}
