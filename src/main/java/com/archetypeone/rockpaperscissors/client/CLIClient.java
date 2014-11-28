package com.archetypeone.rockpaperscissors.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.IntegerValidator;

import com.archetypeone.rockpaperscissors.engine.GameEngine;
import com.archetypeone.rockpaperscissors.engine.GameState;
import com.archetypeone.rockpaperscissors.engine.GameType;
import com.archetypeone.rockpaperscissors.engine.RoundState;
import com.archetypeone.rockpaperscissors.weapons.WeaponCompareResult;

public class CLIClient implements Client {

	private IntegerValidator integerValidator = new IntegerValidator();
	private String[] weapons;
	private String weaponsPrompt;
	private GameEngine gameEngine;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.archetypeone.rockpaperscissors.client.Client#start()
	 */
	@Override
	public void start() {
		initClient();
		System.out.println("Welcome to Rock Paper Scissors!");
		boolean done = false;
		while (!done) {
			try {
				done = newGame();
			} catch (Exception ex) {
				ex.printStackTrace();
				System.err
						.println("Oops, something went wrong! Please try again later...");
				done = true;
			}
		}
		System.out.println("Thanks for playing!");
	}

	private void initClient() {
		weapons = gameEngine.getWeapons();
		String[] weaponSelection = new String[weapons.length];
		for (int i = 0; i < weapons.length; i++) {
			weaponSelection[i] = "(" + (i + 1) + ") " + weapons[i];
		}
		weaponsPrompt = StringUtils.join(weaponSelection, ", ") + ": ";
	}

	private boolean newGame() throws IOException {
		boolean done = false;
		GameType gameType = promptGameType();
		if (gameType == null) {
			done = true;
		} else {
			int winsToPlayFor = promptNumWins();
			playGame(gameType, winsToPlayFor);
		}
		return done;
	}

	private void playGame(GameType gameType, int winsToPlayFor)
			throws IOException {
		boolean done = false;
		GameState state = new GameState(gameType, winsToPlayFor);
		while (!done) {
			switch (gameType) {
			case HUMAN_VS_ROBOT:
				String weapon = promptForWeapon();
				gameEngine.playGame(state, weapon);
				break;
			case ROBOT_VS_ROBOT:
				gameEngine.playGame(state);
				break;
			}
			displayResults(state);
			if (state.isCompleted()) {
				System.out.println("Congratulations " + state.getWinner()
						+ " wins " + state.getPlayer1Score() + "-"
						+ state.getPlayer2Score() + "!\n");
				done = true;
			}
		}
	}

	private void displayResults(GameState state) {
		RoundState roundState = state.getRoundState();
		switch (roundState.getResult()) {
		case DRAW:
			System.out.println(roundState.getPlayer1Weapon() + " draws with "
					+ roundState.getPlayer2Weapon());
			break;
		default:
			String verb = roundState.getResult() == WeaponCompareResult.LHS_WINS ? " beats "
					: " is beaten by ";
			System.out.println(roundState.getPlayer1Weapon() + verb
					+ roundState.getPlayer2Weapon());
			break;
		}
		System.out.println("Scores: " + state.getPlayer1Score() + "-"
				+ state.getPlayer2Score());
	}

	private String promptForWeapon() throws IOException {
		Integer option = prompt(weaponsPrompt, 1, weapons.length);
		return weapons[option - 1];
	}

	private GameType promptGameType() throws IOException {
		Integer option = prompt(
				"Select game type - (1) Human v Robot, (2) Robot v Robot, (3) Quit: ",
				1, 3);
		return lookupGameType(option);
	}

	private Integer promptNumWins() throws IOException {
		return prompt("Wins to play for (<=20): ", 1, 20);
	}

	private Integer prompt(String promptText, int min, int max)
			throws IOException {
		Integer option = null;
		boolean invalidInput = true;
		while (invalidInput) {
			System.out.print(promptText);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					System.in));
			String input = reader.readLine();
			option = validateOption(input, min, max);
			if (option == null) {
				System.out.print("Invalid value, please try again.");
			} else {
				invalidInput = false;
			}
		}
		return option;
	}

	private GameType lookupGameType(Integer option) {
		GameType gameType = null;
		switch (option) {
		case 1:
			gameType = GameType.HUMAN_VS_ROBOT;
			break;
		case 2:
			gameType = GameType.ROBOT_VS_ROBOT;
			break;
		}
		return gameType;
	}

	private Integer validateOption(String input, int min, int max) {
		Integer option = integerValidator.validate(input);
		if (option != null && (option < min && option > max)) {
			option = null;
		}
		return option;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.archetypeone.rockpaperscissors.client.Client#setGameEngine(com.
	 * archetypeone.rockpaperscissors.engine.GameEngine)
	 */
	@Override
	public void setGameEngine(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
	}
}
