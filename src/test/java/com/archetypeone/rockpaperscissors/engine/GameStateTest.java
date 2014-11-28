package com.archetypeone.rockpaperscissors.engine;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.archetypeone.rockpaperscissors.weapons.RockPaperScissorsWeaponsFactory;
import com.archetypeone.rockpaperscissors.weapons.WeaponCompareResult;

public class GameStateTest {

	private GameState sut;

	@Before
	public void setUp() {
		sut = new GameState(GameType.ROBOT_VS_ROBOT, 5);
	}

	@Test
	public void updateRoundStateStillInPlayPlayer1WinsRound() {
		// given
		RoundState state = new RoundState();
		state.setPlayer1Weapon(RockPaperScissorsWeaponsFactory.ROCK);
		state.setPlayer2Weapon(RockPaperScissorsWeaponsFactory.SCISSORS);
		state.setResult(WeaponCompareResult.LHS_WINS);
		// when
		sut.updateRoundState(state);
		// then
		Assert.assertNull(sut.getWinner());
		Assert.assertFalse(sut.isCompleted());
	}

	@Test
	public void updateRoundStateStillInPlayPlayer2WinsRound() {
		// given
		RoundState state = new RoundState();
		state.setPlayer1Weapon(RockPaperScissorsWeaponsFactory.SCISSORS);
		state.setPlayer2Weapon(RockPaperScissorsWeaponsFactory.ROCK);
		state.setResult(WeaponCompareResult.RHS_WINS);
		// when
		sut.updateRoundState(state);
		// then
		Assert.assertNull(sut.getWinner());
		Assert.assertFalse(sut.isCompleted());
	}

	@Test
	public void updateRoundStateWonPlayer1() {
		// given
		RoundState state = new RoundState();
		state.setPlayer1Weapon(RockPaperScissorsWeaponsFactory.ROCK);
		state.setPlayer2Weapon(RockPaperScissorsWeaponsFactory.SCISSORS);
		state.setResult(WeaponCompareResult.LHS_WINS);
		sut.setWinsRemaining(1);
		sut.setWinsToPlayFor(1);
		// when
		sut.updateRoundState(state);
		// then
		Assert.assertEquals("Player 1", sut.getWinner());
		Assert.assertTrue(sut.isCompleted());
	}

	@Test
	public void updateRoundStateWonPlayer2() {
		// given
		RoundState state = new RoundState();
		state.setPlayer1Weapon(RockPaperScissorsWeaponsFactory.SCISSORS);
		state.setPlayer2Weapon(RockPaperScissorsWeaponsFactory.ROCK);
		state.setResult(WeaponCompareResult.RHS_WINS);
		sut.setWinsRemaining(1);
		sut.setWinsToPlayFor(1);
		// when
		sut.updateRoundState(state);
		// then
		Assert.assertEquals("Player 2", sut.getWinner());
		Assert.assertTrue(sut.isCompleted());
	}

	@Test
	public void updateRoundStateDraw() {
		// given
		RoundState state = new RoundState();
		state.setPlayer1Weapon(RockPaperScissorsWeaponsFactory.SCISSORS);
		state.setPlayer2Weapon(RockPaperScissorsWeaponsFactory.SCISSORS);
		state.setResult(WeaponCompareResult.DRAW);
		// when
		sut.updateRoundState(state);
		// then
		Assert.assertNull(sut.getWinner());
		Assert.assertFalse(sut.isCompleted());
	}

	@Test
	public void updateRoundStateWinsRemainingDraw() {
		// given
		RoundState state = new RoundState();
		state.setResult(WeaponCompareResult.DRAW);
		// when
		sut.updateRoundState(state);
		// then
		Assert.assertNull(sut.getWinner());
		Assert.assertEquals(sut.getWinsToPlayFor(), sut.getWinsRemaining());
	}

	@Test
	public void updateRoundStateWinsRemainingPlayer1() {
		// given
		RoundState state = new RoundState();
		state.setResult(WeaponCompareResult.LHS_WINS);
		// when
		sut.updateRoundState(state);
		// then
		Assert.assertNull(sut.getWinner());
		Assert.assertEquals(sut.getWinsToPlayFor() - 1, sut.getWinsRemaining());
	}

	@Test
	public void updateRoundStateWinsRemainingPlayer2() {
		// given
		RoundState state = new RoundState();
		state.setResult(WeaponCompareResult.RHS_WINS);
		// when
		sut.updateRoundState(state);
		// then
		Assert.assertNull(sut.getWinner());
		Assert.assertEquals(sut.getWinsToPlayFor() - 1, sut.getWinsRemaining());
	}
}
