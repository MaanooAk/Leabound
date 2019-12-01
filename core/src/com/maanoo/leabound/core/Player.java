package com.maanoo.leabound.core;

import com.badlogic.gdx.utils.Array;

import com.maanoo.leabound.core.board.Board;
import com.maanoo.leabound.core.gene.BoardSequence;
import com.maanoo.leabound.core.gene.ConceptSequence;
import com.maanoo.leabound.core.gene.Generator;
import com.maanoo.leabound.core.item.Item;
import com.maanoo.leabound.core.item.ItemBundle;
import com.maanoo.leabound.core.util.Location;


public final class Player {

	private final String drawable;
	public final Location location;

	public final ItemBundle bag;
	public final Life life;

	private float level;

	public BoardSequence boardSequence;

	private boolean leaping;
	private Board currectBoard;

	public final Array<String> messages;

	public Player(int startMaxlife) {
		drawable = "player-red"; // TODO extend
		location = new Location();

		bag = new ItemBundle();

		life = new Life(startMaxlife - 1, startMaxlife);
		level = 0;

		boardSequence = new BoardSequence(new ConceptSequence(), new Generator());

		leaping = false;
		messages = new Array<String>();

		bag.add(Item.Fuel, 5);
	}

	// === change ===

	// == leap

	public void leapStart() {
		assert canLeap();

		bag.deduct(Item.Fuel, 1);
		leaping = true;
	}

	public void leapPeak() {

		removeLocalItems();
	}

	public void leapEnd(Board board) {

		leaping = false;
		currectBoard = board;
	}

	public Board nextBoard() {
		return boardSequence.next(this);
	}

	// == progress

	public void addLife(int d) {
		life.add(d);
		level += d;
	}

	public void addLevel(float amount) {
		level += amount;
	}

	// == items

	public void pickup(Item item) {

		if (!item.activate(currectBoard, this)) {

			bag.add(item, 1);
		}

		if (item == Item.Parts) Statistics.Run.parts += 1;
	}

	public boolean deductItem(Item item, int count) {
		return bag.deduct(item, count);
	}

	private void removeLocalItems() {
		bag.removeLocal();
	}

	// == access ===

	public boolean isLeaping() {
		return leaping;
	}

	public boolean canLeap() {
		return bag.get(Item.Fuel) > 0;
	}

	public String getDrawable() {
		return drawable;
	}

	public int getBoardIndex() {
		return boardSequence.getIndex();
	}

	public float getLevel() {
		return level;
	}

	public Board getBoard() {
		return currectBoard;
	}

}
