package com.archetypeone.rockpaperscissors.weapons;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * A weapon is a generic representation of the selected "weapon" i.e. rock,
 * paper or scissors (or possibly something else in an extended form of the
 * game)
 * 
 * @author andrew
 *
 */
public final class Weapon {

	public static final String ERROR_MSG_NAME_BLANK = "name cannot be blank!";

	private final String name;
	private final Set<Weapon> beats;

	Weapon(String name) {
		validateName(name);
		this.name = name;
		beats = new HashSet<Weapon>();
	}

	void validateName(String name) {
		if (StringUtils.isBlank(name)) {
			throw new IllegalArgumentException(ERROR_MSG_NAME_BLANK);
		}
	}

	public void registerBeatsWeapons(Weapon... weapons) {
		beats.addAll(Arrays.asList(weapons));
	}

	public WeaponCompareResult beats(Weapon weapon) {
		WeaponCompareResult roundResult = WeaponCompareResult.DRAW;
		if (beats.contains(weapon)) {
			roundResult = WeaponCompareResult.LHS_WINS;
		} else if (weapon.beats.contains(this)) {
			roundResult = WeaponCompareResult.RHS_WINS;
		}
		return roundResult;
	}

	@Override
	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}
}
