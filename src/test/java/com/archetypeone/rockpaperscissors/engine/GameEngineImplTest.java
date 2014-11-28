package com.archetypeone.rockpaperscissors.engine;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.archetypeone.rockpaperscissors.weapons.RockPaperScissorsWeaponsFactory;
import com.archetypeone.rockpaperscissors.weapons.Weapon;
import com.archetypeone.rockpaperscissors.weapons.WeaponsFactory;

public class GameEngineImplTest {

	private GameEngineImpl sut;
	private WeaponsFactory weaponsFactory;
	private Weapon rock;
	private Weapon paper;
	private Weapon scissors;

	@Before
	public void setUp() {
		sut = spy(new GameEngineImpl());
		weaponsFactory = new RockPaperScissorsWeaponsFactory();
		weaponsFactory.initialize();
		sut.setWeaponsFactory(weaponsFactory);
		rock = weaponsFactory.getWeapon(RockPaperScissorsWeaponsFactory.ROCK);
		paper = weaponsFactory.getWeapon(RockPaperScissorsWeaponsFactory.PAPER);
		scissors = weaponsFactory
				.getWeapon(RockPaperScissorsWeaponsFactory.SCISSORS);
	}

	@Test
	public void getWeapons() {
		// when
		String[] names = sut.getWeapons();
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
	public void playGameRobotVsRobotDraw() {
		// given
		GameState state = new GameState(GameType.ROBOT_VS_ROBOT, 1);
		// when
		when(sut.computeWeapon()).thenReturn(rock).thenReturn(rock);
		sut.playGame(state);
		// then
		Assert.assertEquals(0, state.getPlayer1Score());
		Assert.assertEquals(0, state.getPlayer2Score());
	}

	@Test
	public void playGameRobotVsRobotPlayer1Wins() {
		// given
		GameState state = new GameState(GameType.ROBOT_VS_ROBOT, 1);
		// when
		when(sut.computeWeapon()).thenReturn(rock).thenReturn(scissors);
		sut.playGame(state);
		// then
		Assert.assertEquals(0, state.getPlayer2Score());
		Assert.assertEquals(1, state.getPlayer1Score());
	}

	@Test
	public void playGameRobotVsRobotPlayer2Wins() {
		// given
		GameState state = new GameState(GameType.ROBOT_VS_ROBOT, 1);
		// when
		when(sut.computeWeapon()).thenReturn(rock).thenReturn(paper);
		sut.playGame(state);
		// then
		Assert.assertEquals(0, state.getPlayer1Score());
		Assert.assertEquals(1, state.getPlayer2Score());
	}

	@Test
	public void playGameHumanVsRobotDraw() {
		// given
		GameState state = new GameState(GameType.HUMAN_VS_ROBOT, 1);
		// when
		when(sut.computeWeapon()).thenReturn(rock);
		sut.playGame(state, RockPaperScissorsWeaponsFactory.ROCK);
		// then
		Assert.assertEquals(0, state.getPlayer1Score());
		Assert.assertEquals(0, state.getPlayer2Score());
	}

	@Test
	public void playGameHumanVsRobotPlayer1Wins() {
		// given
		GameState state = new GameState(GameType.HUMAN_VS_ROBOT, 1);
		// when
		when(sut.computeWeapon()).thenReturn(scissors);
		sut.playGame(state, RockPaperScissorsWeaponsFactory.ROCK);
		// then
		Assert.assertEquals(0, state.getPlayer2Score());
		Assert.assertEquals(1, state.getPlayer1Score());
	}

	@Test
	public void playGameHumanVsRobotPlayer2Wins() {
		// given
		GameState state = new GameState(GameType.HUMAN_VS_ROBOT, 1);
		// when
		when(sut.computeWeapon()).thenReturn(paper);
		sut.playGame(state, RockPaperScissorsWeaponsFactory.ROCK);
		// then
		Assert.assertEquals(0, state.getPlayer1Score());
		Assert.assertEquals(1, state.getPlayer2Score());
	}
}
