package com.archetypeone.rockpaperscissors.engine;

import java.io.Serializable;

public class GameState implements Serializable {

	private static final long serialVersionUID = -7918826373236467352L;

	private GameType gameType;
	private int winsToPlayFor;
	private RoundState roundState;
	private boolean completed;
	private String winner;
	private int winsRemaining;
	private int player1Score = 0;
	private int player2Score = 0;

	public GameState(GameType gameType, int winsToPlayFor) {
		this.gameType = gameType;
		this.winsToPlayFor = winsToPlayFor;
		this.winsRemaining = winsToPlayFor;
	}

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	public int getWinsToPlayFor() {
		return winsToPlayFor;
	}

	void setWinsToPlayFor(int winsToPlayFor) {
		this.winsToPlayFor = winsToPlayFor;
	}

	public RoundState getRoundState() {
		return roundState;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public int getWinsRemaining() {
		return winsRemaining;
	}

	public void setWinsRemaining(int winsRemaining) {
		this.winsRemaining = winsRemaining;
	}

	public int getPlayer1Score() {
		return player1Score;
	}

	public int getPlayer2Score() {
		return player2Score;
	}

	public void updateRoundState(RoundState roundState) {
		this.roundState = roundState;
		// update wins
		switch (roundState.getResult()) {
		case LHS_WINS:
			player1Score++;
			if (player1Score == winsToPlayFor) {
				winner = "Player 1";
				completed = true;
			}
			break;
		case RHS_WINS:
			player2Score++;
			if (player2Score == winsToPlayFor) {
				winner = "Player 2";
				completed = true;
			}
			break;
		case DRAW:
			// do nothing
			break;
		}
		winsRemaining = winsToPlayFor - Math.max(player1Score, player2Score);
	}

	void setPlayer1Score(int player1Score) {
		this.player1Score = player1Score;
	}

	void setPlayer2Score(int player2Score) {
		this.player2Score = player2Score;
	}
}
