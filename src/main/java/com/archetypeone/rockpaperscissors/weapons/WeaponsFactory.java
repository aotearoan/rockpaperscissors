package com.archetypeone.rockpaperscissors.weapons;


public interface WeaponsFactory {

	/**
	 * Initialize weapons factory
	 */
	void initialize();

	Weapon getWeapon(String name);

	String[] getWeaponNames();
}
