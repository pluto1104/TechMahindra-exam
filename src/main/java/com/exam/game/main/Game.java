package com.exam.game.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.exam.game.entity.Player;
import com.exam.game.entity.RoundGame;
import com.exam.game.utility.Constants;
import com.exam.game.utility.Utilities;
@Component
public class Game implements ApplicationRunner {
	private List<Player> playerList;
	private List<RoundGame> gameList;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		startGame();
		// get winners
		getRoundWinner(playerList, gameList);
		// get player details
		getPlayerHistory(playerList, gameList);
		// get the winner
		getOverallWinner(playerList);
		// restart
		restartGame();
	}

	private void startGame() {
		// first step enter player name
		Scanner in = new Scanner(System.in);
		playerList = new ArrayList<>();
		for(int i = 0; i < Constants.PLAYERNUMBER; i++) {
			System.out.print(Constants.ENTERPLAYER);
			Player player = new Player();
			player.setName(in.next());
			playerList.add(player);
		}
		// second step set up the every round every player guess number
		gameList = new ArrayList<>();
		for(int j = 1; j < Constants.ROUNDNUMBER; j++) {
			System.out.println(Constants.POUNDSIGN);
			System.out.println(new StringBuilder().append(Constants.ROUND).append(" ").append(j).toString());
			RoundGame game = new RoundGame();
			game.setCorrectNumber(Utilities.getRandomNumber());
			Map<String, Integer> playerGuessNumber = new HashMap<>();
			for (int k = 0; k < playerList.size(); k++) {
				Player player = playerList.get(k);
				System.out.print(new StringBuilder().append(Constants.PLAYERGUESS).append(" ").append(player.getName()).append(Constants.COLON).toString());
				playerGuessNumber.put(player.getName(), in.nextInt());
			}
			game.setPlayerGuessNumber(playerGuessNumber);
			gameList.add(game);
		}
	}

	private void getRoundWinner(List<Player> playerList, List<RoundGame> gameList) {
		for(int m = 0; m < gameList.size(); m++) {
			System.out.println(Constants.POUNDSIGN);
			RoundGame game = gameList.get(m);
			System.out.println(new StringBuilder(Constants.GUESSNUMBERROUND).append(" ").append(m+1).append(":").append(game.getCorrectNumber()).toString());
			System.out.println(Constants.WINNER);
			Map<String, Integer> playerGuessNumber = game.getPlayerGuessNumber();
			int winnerNumber = 0;
			for (int n = 0; n < playerList.size(); n++) {
				Player player = playerList.get(n);
				if(playerGuessNumber.get(player.getName()).equals(game.getCorrectNumber())) {
					System.out.println(Constants.PLAYER + player.getName());
					player.setScore(player.getScore()+1);
					winnerNumber++;
				}
			}
			if(winnerNumber==0) {
				System.out.println(Constants.NOWINNER);
			}
		}
	}

	private void getPlayerHistory(List<Player> playerList, List<RoundGame> gameList) {
		for(int n = 0; n < playerList.size(); n++) {
			System.out.println(Constants.POUNDSIGN);
			Player player = playerList.get(n);
			System.out.println(Constants.PLAYERNAME + player.getName());
			for(int m = 0; m < gameList.size(); m++) {
				RoundGame game = gameList.get(m);
				Map<String, Integer> playerGuessNumber = game.getPlayerGuessNumber();
				System.out.println(new StringBuilder().append(Constants.ROUND).append(" ").append(m+1).append(Constants.GUESSVALUE)
						.append(playerGuessNumber.get(player.getName())).append(Constants.CORRECTVALUE).append(game.getCorrectNumber()).toString());
			}
			System.out.println(Constants.YOURSCORE + player.getScore());
		}
	}

	private void getOverallWinner(List<Player> playerList) {
		System.out.println(Constants.POUNDSIGN);
		System.out.println(Constants.OVERALLWINNER);
		Player winner = new Player();
		List<Player> winnerList = new ArrayList<>();
		for(int n = 0; n < playerList.size(); n++) {
			Player player = playerList.get(n);
			if(player.getScore()>winner.getScore()) {
				winner = player;
				winnerList.clear();
			}else if(player.getScore()==winner.getScore()) {
				winnerList.add(player);
			}
		}
		if(!winnerList.isEmpty()) {
			int winnerNumber = 0;
			for(Player win : winnerList) {
				if(win.getScore()>0) {
					System.out.println(Constants.PLAYER + win.getName());
					winnerNumber++;
				}
			}
			if(winnerNumber == 0) {
				System.out.println(Constants.NOWINNER);
			}
		}else {
			if(winner.getScore()>0) {
				System.out.println(Constants.PLAYER + winner.getName());
			}else {
				System.out.println(Constants.NOWINNER);
			}
		}
		System.out.println(Constants.POUNDSIGN);
	}
	
	private void restartGame() {
		Scanner in = new Scanner(System.in);
		System.out.print(Constants.RESTARTGAME);
		String tryAgain = in.next();
		if(Constants.YES.equals(tryAgain)) {
			playerList.clear();
			gameList.clear();
			startGame();
			// get winners
			getRoundWinner(playerList, gameList);
			// get player details
			getPlayerHistory(playerList, gameList);
			// get the winner
			getOverallWinner(playerList);
			// restart
			restartGame();
		}else {
			in.close();
		}
	}

}
