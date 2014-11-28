package com.archetypeone.rockpaperscissors.weapons;

import java.util.HashMap;
import java.util.Map;

/**
 * Standard Rock Paper Scissors weapons factory
 * 
 * @author andrew
 *
 */
public class RockPaperScissorsWeaponsFactory implements WeaponsFactory {

	public static final String ROCK = "rock";
	public static final String PAPER = "paper";
	public static final String SCISSORS = "scissors";

	private Map<String, Weapon> weapons = new HashMap<String, Weapon>();

	@Override
	public void initialize() {
		Weapon rock = new Weapon(ROCK);
		Weapon paper = new Weapon(PAPER);
		Weapon scissors = new Weapon(SCISSORS);
		rock.registerBeatsWeapons(scissors);
		scissors.registerBeatsWeapons(paper);
		paper.registerBeatsWeapons(rock);
		weapons.put(rock.getName(), rock);
		weapons.put(paper.getName(), paper);
		weapons.put(scissors.getName(), scissors);
	}

	@Override
	public Weapon getWeapon(String name) {
		return weapons.get(name);
	}

	@Override
	public String[] getWeaponNames() {
		return weapons.keySet().toArray(new String[weapons.size()]);
	}
}
