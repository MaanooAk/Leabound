package com.maanoo.leabound.core.gene;

import com.badlogic.gdx.utils.Align;
import com.maanoo.leabound.core.board.Board;
import com.maanoo.leabound.core.item.Item;
import com.maanoo.leabound.core.thing.AndGate;
import com.maanoo.leabound.core.thing.Dispenser;
import com.maanoo.leabound.core.thing.LockedChest;
import com.maanoo.leabound.core.thing.OnePassDoor;
import com.maanoo.leabound.core.thing.PickUp;
import com.maanoo.leabound.core.thing.PressurePlate;
import com.maanoo.leabound.core.thing.Switch;
import com.maanoo.leabound.core.thing.Wall;
import com.maanoo.leabound.core.thing.Wire;
import com.maanoo.leabound.core.util.Direction;
import com.maanoo.leabound.core.util.Location;
import com.maanoo.leabound.core.util.Ra;

public abstract class SubGenerator {

	// === class ===

	private SubGenerator() {
	}

	public abstract boolean can(Board board, BoardArea area, int emptyAreas);

	public abstract Object generate(Board board, BoardArea area, float level);

	// === impls ===

	private static final int LocationsSM = 60;
	private static final int LocationsMB = 120;

	public static class CenterThing extends SubGenerator {

		private final ThingGenerator generator;

		public CenterThing(ThingGenerator generator) {
			this.generator = generator;
		}

		@Override
		public boolean can(Board board, BoardArea area, int emptyAreas) {
			return area.getLocations() < LocationsSM;
		}

		@Override
		public Object generate(Board board, BoardArea area, float level) {

			if (generator == null) return null;

			final Location location = new Location(
					area.getX(Align.center) - 1 + Ra.next(-1, 1),
					area.getY(Align.center) + Ra.next(-1, 1));

			board.addThing(generator.generate(board, location));

			return null;
		}

	}

	public static class LogicProblem1 extends SubGenerator {

		@Override
		public boolean can(Board board, BoardArea area, int emptyAreas) {
			return area.getLocations() > LocationsSM && area.getLocations() < LocationsMB;
		}

		@Override
		public Object generate(Board board, BoardArea area, float level) {

			final Location p1, p2;
			final Direction rot;

			int dis1 = Math.max(1, 2 - (int) level + Ra.next(2));
			int dis2 = Math.max(1, 2 - (int) level + Ra.next(2));

			if (area.isSquary()) {

				dis1 += 1;
				dis2 += 1;

				if (Ra.bool()) {
					p1 = area.get(Align.bottomLeft).add(dis2, dis1);
					p2 = area.get(Align.topRight).add(-dis2, -dis1);
					rot = Direction.Right;
				} else {
					p1 = area.get(Align.bottomLeft).add(dis2, dis1);
					p2 = area.get(Align.topRight).add(-dis1, -dis2);
					rot = Direction.Left;
				}

			} else if (area.isHorizontal()) {

				if (Ra.bool()) {
					p1 = area.get(Align.bottomLeft).add(dis1, 1);
					p2 = area.get(Align.bottomRight).add(-dis2, 1);
					rot = Direction.Up;
				} else {
					p1 = area.get(Align.topLeft).add(dis1, -1);
					p2 = area.get(Align.topRight).add(-dis2, -1);
					rot = Direction.Down;
				}

			} else {

				if (Ra.bool()) {
					p1 = area.get(Align.bottomLeft).add(1, dis1);
					p2 = area.get(Align.topLeft).add(1, -dis2);
					rot = Direction.Right;
				} else {
					p1 = area.get(Align.bottomRight).add(-1, dis1);
					p2 = area.get(Align.topRight).add(-1, -dis2);
					rot = Direction.Left;
				}

			}

			return generateThings(board, p1, p2, rot);
		}

		protected Object generateThings(Board board, final Location p1, final Location p2, final Direction rot) {

			final Location p3 = between(p1, p2);

			final Location p4 = p3.cpy().mulAdd(rot.vector, 2);

			final PickUp pickup = new PickUp(null, Item.Parts, rot.origin);

			if (Ra.bool()) {
				board.addThing(new Switch(p1));
				board.addThing(new Switch(p2));
			} else if (Ra.bool()) {
				board.addThing(new PressurePlate(p1));
				board.addThing(new Switch(p2));
			} else {
				board.addThing(new Switch(p1));
				board.addThing(new PressurePlate(p2));
			}
			board.addThing(new AndGate(p3, rot));
			board.addThing(new Dispenser(p4, rot, pickup));

			wire(board, p1, p3);
			wire(board, p2, p3);
			wire(board, p3, p4);

			return null;

		}

	}

	public static class LogicProblem2 extends LogicProblem1 {

		@Override
		public boolean can(Board board, BoardArea area, int emptyAreas) {
			return area.getLocations() > LocationsMB;
		}

		@Override
		protected Object generateThings(Board board, final Location p1, final Location p2, final Direction rot) {

			final Location p3 = between(p1, p2);

			final Location p4 = p3.cpy().mulAdd(rot.vector, 2);
			final Location p5 = p3.cpy().mulAdd(rot.vector, -3);

			final PickUp pickup1 = new PickUp(null, Item.Key, rot.origin);
			final PickUp pickup2 = new PickUp(null, Item.MasterKey, rot.origin);

			if (Ra.bool()) {
				board.addThing(new Switch(p1));
				board.addThing(new Switch(p2));
			} else if (Ra.bool()) {
				board.addThing(new PressurePlate(p1));
				board.addThing(new Switch(p2));
			} else {
				board.addThing(new Switch(p1));
				board.addThing(new PressurePlate(p2));
			}
			board.addThing(new AndGate(p3, rot));
			board.addThing(new Dispenser(p4, rot, pickup1));
			board.addThing(new LockedChest(p5, pickup1.getItem(), pickup2));

			wire(board, p1, p3);
			wire(board, p2, p3);
			wire(board, p3, p4);

			return null;
		}

	}

	public static class OnePassProblem extends SubGenerator {

		@Override
		public boolean can(Board board, BoardArea area, int emptyAreas) {
			return area.getLocations() > LocationsSM;
		}

		@Override
		public Object generate(Board board, BoardArea area, float level) {

			final Location p1, p2, p3;

			final int dis1 = Math.max(2, 2 - (int) level + Ra.next(2));
			final int dis2 = Math.max(2, 2 - (int) level + Ra.next(2));

			if (Ra.bool()) {
				p1 = area.get(Align.bottomLeft).add(dis2, dis1);
				p2 = area.get(Align.topRight).add(-dis2, -dis1);
			} else {
				p1 = area.get(Align.bottomLeft).add(dis2, dis1);
				p2 = area.get(Align.topRight).add(-dis1, -dis2);
			}

			if (Ra.bool()) {
				p3 = p1.cpy().add(1, 2);
			} else {
				p3 = p1.cpy().add(2, 1);
			}

			final Item[] keys = new Item[] { Item.Key, Item.KeyA, Item.KeyB, Item.KeyC };
			Ra.shuffle(keys);

			final Item key1 = keys[0];
			final Item key2 = keys[1];

			final PickUp pickup1 = new PickUp(p1, key1, 0);
			final PickUp pickup2 = new PickUp(null, key2, Align.center);
			final PickUp pickup3 = new PickUp(null, Item.Parts, Align.center);

			board.addThing(pickup1);
			board.addThing(new LockedChest(p2, pickup1.getItem(), pickup2));
			board.addThing(new LockedChest(p3, pickup2.getItem(), pickup3));

			//

			final float fill = Math.min(.9f, .5f + level * .1f);

			final Location i = new Location();
			for (int x = 0; x < area.w; x++) {
				for (int y = 0; y < area.h; y++) {
					i.set(area.x + x, area.y + y);

					final boolean border = x == 0 || y == 0 || x == area.w - 1 || y == area.h - 1;

					if (!board.hasThing(i) && Ra.chance(border ? .5f : fill)) {

						if (Ra.chance(.05f) || border) {
							board.addThing(new Wall(i.cpy()));
						} else {
							board.addThing(new OnePassDoor(i.cpy()));
						}

					}
				}
			}

			return null;
		}

	}

	// === utils ===

	private static Location between(final Location p1, final Location p2) {
		return new Location(
				p1.x + Math.abs(p2.x - p1.x) / 2,
				p1.y + Math.abs(p2.y - p1.y) / 2);

	}

	private static boolean wire(Board board, Location p1, Location p2) {

		if (p1.y == p2.y && Math.abs(p1.x - p2.x) == 1) {
			// adjecent
		} else if (p1.x == p2.x && Math.abs(p1.y - p2.y) == 1) {
			// adjecent

		} else if (p1.y == p2.y) {

			if (p1.x > p2.x) return wire(board, p2, p1);

			for (int x = p1.x + 1; x <= p2.x - 1; x++) {
				board.addThing(new Wire(new Location(x, p1.y)));
			}

		} else if (p1.x == p2.x) {

			if (p1.y > p2.y) return wire(board, p2, p1);

			for (int y = p1.y + 1; y <= p2.y - 1; y++) {
				board.addThing(new Wire(new Location(p1.x, y)));
			}

		} else {

			final Location corner = new Location(p2.x, p1.y);

			wire(board, p1, corner);
			wire(board, corner, p2);

			board.addThing(new Wire(corner));
		}

		return true;
	}

}
