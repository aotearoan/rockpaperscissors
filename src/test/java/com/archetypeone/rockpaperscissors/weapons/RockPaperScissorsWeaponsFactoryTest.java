package com.archetypeone.rockpaperscissors.weapons;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RockPaperScissorsWeaponsFactoryTest {

	private RockPaperScissorsWeaponsFactory sut;
	Weapon rock;
	Weapon scissors;
	Weapon paper;

	@Before
	public void setUp() {
		sut = new RockPaperScissorsWeaponsFactory();
		sut.initialize();
		rock = sut.getWeapon(RockPaperScissorsWeaponsFactory.ROCK);
		scissors = sut.getWeapon(RockPaperScissorsWeaponsFactory.SCISSORS);
		paper = sut.getWeapon(RockPaperScissorsWeaponsFactory.PAPER);
	}

	@Test
	public void getWeaponRock() {
		// when
		Weapon result = sut.getWeapon(RockPaperScissorsWeaponsFactory.ROCK);
		// then
		Assert.assertEquals(RockPaperScissorsWeaponsFactory.ROCK,
				result.getName());
	}

	@Test
	public void getWeaponPaper() {
		// when
		Weapon result = sut.getWeapon(RockPaperScissorsWeaponsFactory.PAPER);
		// then
		Assert.assertEquals(RockPaperScissorsWeaponsFactory.PAPER,
				result.getName());
	}

	@Test
	public void getWeaponScissors() {
		// when
		Weapon result = sut.getWeapon(RockPaperScissorsWeaponsFactory.SCISSORS);
		// then
		Assert.assertEquals(RockPaperScissorsWeaponsFactory.SCISSORS,
				result.getName());
	}

	@Test
	public void getWeaponNames() {
		// when
		String[] names = sut.getWeaponNames();
		Set<String> result = new HashSet<String>();
		result.addAll(Arrays.asList(names));
		// then
		Assert.assertEquals(3, result.size());
		Assert.assertTrue(result.contains(RockPaperScissorsWeaponsFactory.ROCK));
		Assert.assertTrue(result
				.contains(RockPaperScissorsWeaponsFactory.PAPER));
		Assert.assertTrue(result
				.contains(RockPaperScissorsWeaponsFactory.SCISSORS));
	}

	@Test
	public void initializeRockBeatsScissors() {
		Assert.assertEquals(WeaponCompareResult.LHS_WINS, rock.beats(scissors));
	}

	@Test
	public void initializeRockDrawsWithRock() {
		Assert.assertEquals(WeaponCompareResult.DRAW, rock.beats(rock));
	}

	@Test
	public void initializeRockBeatenByPaper() {
		Assert.assertEquals(WeaponCompareResult.RHS_WINS, rock.beats(paper));
	}

	@Test
	public void initializeScissorsBeatsPaper() {
		Assert.assertEquals(WeaponCompareResult.LHS_WINS, scissors.beats(paper));
	}

	@Test
	public void initializeScissorsDrawsWithScissors() {
		Assert.assertEquals(WeaponCompareResult.DRAW, scissors.beats(scissors));
	}

	@Test
	public void initializeScissorsBeatenByRock() {
		Assert.assertEquals(WeaponCompareResult.RHS_WINS, scissors.beats(rock));
	}

	@Test
	public void initializePaperBeatsRock() {
		Assert.assertEquals(WeaponCompareResult.LHS_WINS, paper.beats(rock));
	}

	@Test
	public void initializePaperDrawsWithPaper() {
		Assert.assertEquals(WeaponCompareResult.DRAW, paper.beats(paper));
	}

	@Test
	public void initializePaperBeatenByScissors() {
		Assert.assertEquals(WeaponCompareResult.RHS_WINS, paper.beats(scissors));
	}
}
