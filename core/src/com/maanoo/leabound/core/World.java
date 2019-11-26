package com.maanoo.leabound.core;

import com.maanoo.leabound.core.board.Board;


public class World {

	// TODO move things from the face to here

	public final Player player;

	public Board board;

	public World() {

		player = new Player(2);
	}

}
