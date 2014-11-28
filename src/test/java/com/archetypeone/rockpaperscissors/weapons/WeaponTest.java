package com.archetypeone.rockpaperscissors.weapons;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WeaponTest {

	private Weapon rock = new Weapon("rock");
	private Weapon scissors = new Weapon("scissors");
	private Weapon paper = new Weapon("paper");

	@Before
	public void setUp() {
		rock.registerBeatsWeapons(scissors);
		scissors.registerBeatsWeapons(paper);
		paper.registerBeatsWeapons(rock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void validateNameNull() {
		// given
		String name = null;
		// when
		rock.validateName(name);
		// then
	}

	@Test(expected = IllegalArgumentException.class)
	public void validateNameBlank() {
		// given
		String name = "";
		// when
		rock.validateName(name);
		// then
	}

	@Test
	public void validateNameValid() {
		// given
		String name = "rock";
		// when
		rock.validateName(name);
		// then
		Assert.assertTrue(true);
	}

	@Test
	public void beatsDraw() {
		// given
		Weapon rhs = rock;
		// when
		WeaponCompareResult result = rock.beats(rhs);
		// then
		Assert.assertEquals(WeaponCompareResult.DRAW, result);
	}

	@Test
	public void beatsLHS() {
		// given
		Weapon rhs = scissors;
		// when
		WeaponCompareResult result = rock.beats(rhs);
		// then
		Assert.assertEquals(WeaponCompareResult.LHS_WINS, result);
	}

	@Test
	public void beatsRHS() {
		// given
		Weapon rhs = paper;
		// when
		WeaponCompareResult result = rock.beats(rhs);
		// then
		Assert.assertEquals(WeaponCompareResult.RHS_WINS, result);
	}
}
