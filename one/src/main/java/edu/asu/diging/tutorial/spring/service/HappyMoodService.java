package edu.asu.diging.tutorial.spring.service;

import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import edu.asu.diging.tutorial.spring.domain.Mood;

@Component(value="happy")
public class HappyMoodService implements MoodInterfaceService{
	public String[] moods = new String[5];
	
	@Override
	public Mood getCurrentMood() {
		Random rand = new Random();
		int  n = rand.nextInt(5) + 1;
        return new Mood(moods[n-1]);
	}

	@PostConstruct
	public void initIt() {
		moods[0] = "Happy";
		moods[1] = "Sad";
		moods[2] = "Confused";
		moods[3] = "Anxious";
		moods[4] = "Angry";
	}	
	@Override
	public Mood getCurrentMood(int pos) {
		// TODO Auto-generated method stub
		return new Mood(moods[pos]);
	}
}
