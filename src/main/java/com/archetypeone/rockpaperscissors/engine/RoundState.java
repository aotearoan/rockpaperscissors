package com.archetypeone.rockpaperscissors.engine;

import java.io.Serializable;

import com.archetypeone.rockpaperscissors.weapons.WeaponCompareResult;

public class RoundState implements Serializable {

	private static final long serialVersionUID = 4481212783318981588L;

	private String player1Weapon;
	private String player2Weapon;
	private WeaponCompareResult result;

	public String getPlayer1Weapon() {
		return player1Weapon;
	}

	public void setPlayer1Weapon(String player1Weapon) {
		this.player1Weapon = player1Weapon;
	}

	public String getPlayer2Weapon() {
		return player2Weapon;
	}

	public void setPlayer2Weapon(String player2Weapon) {
		this.player2Weapon = player2Weapon;
	}

	public WeaponCompareResult getResult() {
		return result;
	}

	public void setResult(WeaponCompareResult result) {
		this.result = result;
	}
}
