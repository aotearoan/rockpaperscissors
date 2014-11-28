package com.archetypeone.rockpaperscissors.game;

import com.archetypeone.rockpaperscissors.client.CLIClient;
import com.archetypeone.rockpaperscissors.client.Client;
import com.archetypeone.rockpaperscissors.engine.GameEngine;
import com.archetypeone.rockpaperscissors.engine.GameEngineImpl;
import com.archetypeone.rockpaperscissors.weapons.RockPaperScissorsWeaponsFactory;
import com.archetypeone.rockpaperscissors.weapons.WeaponsFactory;

/**
 * Entry point to Rock Paper Scissors game
 * @author andrew
 *
 */
public class RockPaperScissorsGame {
	
	public static void main(String[] args) {
		Client client = new CLIClient();
		GameEngine gameEngine = new GameEngineImpl();
		WeaponsFactory weaponsFactory = new RockPaperScissorsWeaponsFactory();
		weaponsFactory.initialize();
		gameEngine.setWeaponsFactory(weaponsFactory);
		client.setGameEngine(gameEngine);
		client.start();
	}
}
