package com.maanoo.leabound.core;

import com.badlogic.gdx.utils.Array;
import com.maanoo.leabound.core.board.Board;
import com.maanoo.leabound.core.item.Item;
import com.maanoo.leabound.core.item.ItemBundle;
import com.maanoo.leabound.core.util.Location;


public class Player {

	public final Location location;

	public final ItemBundle bag;
	public final Life life;

	private float level;

	public int boardIndex;

	private boolean leaping;
	private Board currectBoard;

	public final Array<String> messages;

	public Player(int startMaxlife) {
		location = new Location();

		bag = new ItemBundle();

		life = new Life(startMaxlife - 1, startMaxlife);
		level = 0;

		boardIndex = 0;

		leaping = false;
		messages = new Array<String>();

		bag.add(Item.Parts, 5);
	}

	public void pickup(Item item) {

		if (!item.activate(currectBoard, this)) {

			bag.add(item, 1);
		}

	}

	public void leapStart() {
		assert canLeap();

		bag.deduct(Item.Parts, 1);
		leaping = true;
	}

	public void leapPeak() {
		assert leaping;

		removeLocalItems();
	}

	public void leapEnd(Board board) {
		assert leaping;

		leaping = false;
		currectBoard = board;
	}

	private void removeLocalItems() {

		bag.removeLocal();
	}

	public void addLife(int d) {
		life.add(d);
		level += d;
	}

	public void addLevel(float amount) {
		level += amount;
	}

	// === map ===

	public boolean deductItem(Item item, int count) {
		return bag.deduct(item, count);
	}

	// == access ===

	public boolean isLeaping() {
		return leaping;

	}

	public boolean canLeap() {
		return bag.get(Item.Parts) > 0;
	}

	public float getLevel() {
		return level;
	}

	public Board getBoard() {
		return currectBoard;
	}

}
