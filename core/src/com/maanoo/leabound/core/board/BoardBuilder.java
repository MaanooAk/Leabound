package com.maanoo.leabound.core.board;

import com.badlogic.gdx.utils.Align;

import com.maanoo.leabound.core.Player;
import com.maanoo.leabound.core.item.Item;
import com.maanoo.leabound.core.thing.AndGate;
import com.maanoo.leabound.core.thing.Dispenser;
import com.maanoo.leabound.core.thing.Display;
import com.maanoo.leabound.core.thing.Door;
import com.maanoo.leabound.core.thing.LatchGate;
import com.maanoo.leabound.core.thing.LockedChest;
import com.maanoo.leabound.core.thing.NotGate;
import com.maanoo.leabound.core.thing.OnePassDoor;
import com.maanoo.leabound.core.thing.PickUp;
import com.maanoo.leabound.core.thing.PressurePlate;
import com.maanoo.leabound.core.thing.Switch;
import com.maanoo.leabound.core.thing.Thing;
import com.maanoo.leabound.core.thing.Wall;
import com.maanoo.leabound.core.thing.Wire;
import com.maanoo.leabound.core.util.Direction;
import com.maanoo.leabound.core.util.Location;
import com.maanoo.leabound.core.util.Ra;


public class BoardBuilder {

	private static final int BoardWidth = 16;
	private static final int BoardHeight = 14;

	// === class ===

	private String name;
	private String board;
	private String[] things;
	private int w;
	private int h;

	public BoardBuilder(String name, String board, String... things) {
		this(name, BoardWidth, BoardHeight, board, things);
	}

	public BoardBuilder(String name, int w, int h, String board, String... things) {
		this.name = name;
		this.w = w;
		this.h = h;
		this.board = board;
		this.things = things;
	}

	public Board build(Player player) {
		return build(player, new Bound(w, h, player));
	}

	public Board build(Player player, Bound bound) {

		final Board b = new Board(name, bound);

		Location location = new Location(-w / 2, h / 2 - 1);

		for (final char c : board.toCharArray()) {

			if (c == '\n') {
				location.set(-w / 2, location.y - 1);
				continue;
			}

			if (c == ' ') {
				// no op
			} else if (c == 'X') {
				b.addThing(new Wall(location));
				location = location.cpy();
			} else if (c == 'W' || c == '-') {
				b.addThing(new Wire(location));
				location = location.cpy();
			} else if (c == 'P') {
				b.addThing(new PressurePlate(location));
				location = location.cpy();
			} else if (c == 'S') {
				b.addThing(new Switch(location));
				location = location.cpy();
			} else if (c == 'O') {
				b.addThing(new OnePassDoor(location));
				location = location.cpy();
			} else if (c == 'A') {
				b.addThing(new AndGate(location, Direction.Down));
				location = location.cpy();
			} else if (c == 'a') {
				b.addThing(new AndGate(location, Direction.Up));
				location = location.cpy();
			} else if (c == 'N') {
				b.addThing(new NotGate(location, Direction.Down));
				location = location.cpy();
			} else if (c == 'L') {
				b.addThing(new LatchGate(location, Direction.Down));
				location = location.cpy();
			} else {

				final Thing thing = parseThing(findThingDefiniton(c, things), b, location);
				if (thing != null) {

					b.addThing(thing);
					location = location.cpy();
				}
			}
			location.add(1, 0);
		}

		b.onCreate();

		return b;
	}

	private static String findThingDefiniton(char c, String[] things) {

		for (int i = 0; i < things.length; i += 2) {
			if (things[i].charAt(0) == c) return things[i + 1];
		}

		throw new RuntimeException("Unkown thing definition: " + c);
	}

	private static Thing parseThing(String text, Board board, Location location) {

		final String[] parts = text.split("[ ]+");

		if (parts[0].equals("dispenser")) {
			final Direction rotation = parseRotation(parts[1]);
			final Item item = parseItem(parts[2]);

			return new Dispenser(location, rotation, item);

		} else if (parts[0].equals("locked-chest")) {
			final Item unlock = parseItem(parts[1]);
			final Item item = parseItem(parts[2]);

			return new LockedChest(location, unlock, item);

		} else if (parts[0].equals("door")) {
			final Item unlock = parseItem(parts[1]);

			return new Door(location, unlock);

		} else if (parts[0].equals("pick-up")) {
			final Item item = parseItem(parts[1]);

			return new PickUp(location, item, Align.center);

		} else if (parts[0].equals("wall")) {
			final float chance = Integer.parseInt(parts[1]) / 100f;

			if (!Ra.chance(chance)) return null;

			return new Wall(location);

		} else if (parts[0].equals("and-gate")) {
			final Direction rotation = parseRotation(parts[1]);

			return new AndGate(location, rotation);

		} else if (parts[0].equals("display")) {
			final String param = parts[1].replace("_", " ");

			return new Display(location, param);

		} else {
			throw new RuntimeException("Unknown thing: " + parts[0]);
		}

	}

	private static Item parseItem(String text) {

		return Item.get(text);
	}

	private static Direction parseRotation(String text) {

		if (text.equals("r") || text.equals(">")) return Direction.Right;
		if (text.equals("d") || text.equals("V")) return Direction.Down;
		if (text.equals("l") || text.equals("<")) return Direction.Left;
		if (text.equals("u") || text.equals("^")) return Direction.Up;

		throw new RuntimeException(text);
	}

}
