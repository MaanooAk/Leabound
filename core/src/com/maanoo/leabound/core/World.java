package com.maanoo.leabound.core;

import com.maanoo.leabound.core.board.Board;
import com.maanoo.leabound.core.thing.Thing;


public class World {

	// TODO move things from the face to here

	@Deprecated
	public final Player player;
	private Board board;

	public World() {

		player = new Player(2);
	}

	// === change ===

	public void setBoard(Board board) {
		this.board = board;

		{ // TODO move
			if (board.hasThing(player.location)) {

				boolean change = false;
				if (board.getThing(player.location).onPlayerEnter(player)) {
					change = true;
					board.updateOnActvateChange(board.getThing(player.location));
				}

				// TODO refactor to method
				while (change) {
					change = false;

					for (final Thing i : board) {
						if (!i.isDestroyed()) continue;

						board.removeThing(i.getLocation());

						change = true;
						break;
					}
				}
			}
		}
	}

	// === access ===

	public Player getPlayer() {
		return player;
	}

	public Board getBoard() {
		return board;
	}

}
