package com.archetypeone.rockpaperscissors.client;

import com.archetypeone.rockpaperscissors.engine.GameEngine;

public interface Client {

	public abstract void start();

	public abstract void setGameEngine(GameEngine gameEngine);

}