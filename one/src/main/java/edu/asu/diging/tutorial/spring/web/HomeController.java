package edu.asu.diging.tutorial.spring.web;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.asu.diging.tutorial.spring.service.AnotherMoodService;
import edu.asu.diging.tutorial.spring.service.HappyMoodService;
import edu.asu.diging.tutorial.spring.service.MoodService;
import edu.asu.diging.tutorial.spring.service.Play;
import edu.asu.diging.tutorial.spring.service.Sender;

@Controller
public class HomeController {
	
	@Autowired
	private MoodService service;
	
	@Autowired
	@Qualifier("happy") 
	private HappyMoodService service1;
	
	@Autowired
	@Qualifier("Another")
	private AnotherMoodService service2;
	
	@Autowired
	private Sender send;
	
	@Autowired
	private Play tt;
	
	public String tossValue;
	
	/*@RequestMapping(value = "/")
	public String home() {
		System.out.println("In controller");
	    return "index";
	}*/
	
	@RequestMapping(value = "/")
	public String home(ModelMap map) {
		Random rand = new Random();
		int  n = rand.nextInt(4) + 0;
		//if(n%2==0)
		map.addAttribute("mood", service1.getCurrentMood(n));
		map.addAttribute("reason", n);
		//else
			//map.addAttribute("mood", service2.getCurrentMood());
		//send.send("question","Its awesome!");
		tt.toss();
	    return "home";
	}
	//@PathVariable for path variable
	@RequestMapping(value = "/reason", params = {"why"})
	public String reason(@RequestParam("why") String why, ModelMap map) {
		System.out.println("Was in reason"+why);
		map.addAttribute("reason", service2.getCurrentMood(Integer.parseInt(why)));
	    return "reason";
	}
	@RequestMapping(value = "/about")
	public String reason(ModelMap map) {
	    return "about";
	}
}
