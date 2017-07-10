package edu.asu.diging.tutorial.spring.service;

import org.springframework.stereotype.Service;
import java.util.Random;

import javax.annotation.PostConstruct;

import edu.asu.diging.tutorial.spring.domain.Mood;

@Service
public class MoodService {
	public String[] moods;
	
	// can use initIt() instead of constructor for initializing
	/*public MoodService(){
		moods = new String[5];
		moods[0] = "Happy";
		moods[1] = "Sad";
		moods[2] = "Confused";
		moods[3] = "Anxious";
		moods[4] = "Angry";
	}*/
	
	@PostConstruct
	public void initIt() throws Exception {
		moods = new String[5];
		moods[0] = "Happy";
		moods[1] = "Sad";
		moods[2] = "Confused";
		moods[3] = "Anxious";
		moods[4] = "Angry";
	}
    
	public Mood getCurrentMood() {
		Random rand = new Random();
		int  n = rand.nextInt(5) + 1;
        return new Mood(moods[n-1]);
    }
}
