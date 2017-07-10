package edu.asu.diging.tutorial.spring.service;

import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import edu.asu.diging.tutorial.spring.domain.Mood;

@Component(value="Another")
public class AnotherMoodService implements MoodInterfaceService{
	public String[] moods = new String[5];
	
	@Override
	public Mood getCurrentMood() {
		Random rand = new Random();
		int  n = rand.nextInt(5) + 1;
        return new Mood(moods[n-1]);
	}

	@PostConstruct
	public void initIt() {
		// TODO Auto-generated method stub
		moods[0] = "It's my birthday";
		moods[1] = "I'm hurt";
		moods[2] = "I thought it's wednesday today";
		moods[3] = "It's the finals today";
		moods[4] = "The climate is annoying";
	}

	@Override
	public Mood getCurrentMood(int pos) {
		// TODO Auto-generated method stub
		return new Mood(moods[pos]);
	}

}
