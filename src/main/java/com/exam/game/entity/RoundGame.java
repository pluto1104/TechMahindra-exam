package com.exam.game.entity;

import java.util.Map;

public class RoundGame {
	
	private Integer correctNumber;
	private Map<String, Integer> playerGuessNumber;
	
	public Integer getCorrectNumber() {
		return correctNumber;
	}
	public void setCorrectNumber(Integer correctNumber) {
		this.correctNumber = correctNumber;
	}
	public Map<String, Integer> getPlayerGuessNumber() {
		return playerGuessNumber;
	}
	public void setPlayerGuessNumber(Map<String, Integer> playerGuessNumber) {
		this.playerGuessNumber = playerGuessNumber;
	}
	
}
