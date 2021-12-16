package com.exam.game.utility;

import java.util.Random;

public class Utilities {
	
	public static Integer getRandomNumber() {
		Random random = new Random();
		return random.nextInt(10);
	}

}
