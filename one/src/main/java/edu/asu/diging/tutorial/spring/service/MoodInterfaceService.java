package edu.asu.diging.tutorial.spring.service;

import javax.annotation.PostConstruct;

import org.springframework.ui.ModelMap;

import edu.asu.diging.tutorial.spring.domain.Mood;

public interface MoodInterfaceService {
	public Mood getCurrentMood();
	public Mood getCurrentMood(int pos);
	public void initIt();	
}
