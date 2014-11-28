package com.archetypeone.rockpaperscissors.engine;

import java.util.Random;

import com.archetypeone.rockpaperscissors.weapons.Weapon;
import com.archetypeone.rockpaperscissors.weapons.WeaponCompareResult;
import com.archetypeone.rockpaperscissors.weapons.WeaponsFactory;

public class GameEngineImpl implements GameEngine {

	private static final Random RANDOM_GENERATOR = new Random();

	private WeaponsFactory weaponsFactory;

	@Override
	public void playGame(GameState state) {
		Weapon player1Weapon = computeWeapon();
		playGame(state, player1Weapon);
	}

	Weapon computeWeapon() {
		int index = RANDOM_GENERATOR.nextInt(getWeapons().length);
		return weaponsFactory.getWeapon(getWeapons()[index]);
	}

	@Override
	public void playGame(GameState state, String player1WeaponName) {
		Weapon player1Weapon = weaponsFactory.getWeapon(player1WeaponName);
		playGame(state, player1Weapon);
	}

	private void playGame(GameState state, Weapon player1Weapon) {
		Weapon player2Weapon = computeWeapon();
		WeaponCompareResult result = player1Weapon.beats(player2Weapon);
		RoundState roundState = new RoundState();
		roundState.setPlayer1Weapon(player1Weapon.getName());
		roundState.setPlayer2Weapon(player2Weapon.getName());
		roundState.setResult(result);
		state.updateRoundState(roundState);
	}

	@Override
	public String[] getWeapons() {
		return weaponsFactory.getWeaponNames();
	}

	public void setWeaponsFactory(WeaponsFactory weaponsFactory) {
		this.weaponsFactory = weaponsFactory;
	}
}
