package com.archetypeone.rockpaperscissors.engine;

import com.archetypeone.rockpaperscissors.weapons.WeaponsFactory;

public interface GameEngine {

	void playGame(GameState state);

	void playGame(GameState state, String player1Weapon);

	String[] getWeapons();

	void setWeaponsFactory(WeaponsFactory weaponsFactory);
}
