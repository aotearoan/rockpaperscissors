package com.archetypeone.rockpaperscissors.engine;

public enum GameType {
	HUMAN_VS_ROBOT(false), ROBOT_VS_ROBOT(true);

	private final boolean isPlayer1Robot;

	GameType(boolean isPlayer1Robot) {
		this.isPlayer1Robot = isPlayer1Robot;
	}

	public boolean isPlayer1Robot() {
		return isPlayer1Robot;
	}
}
