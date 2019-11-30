package com.maanoo.leabound.core.gene.subs;

import com.badlogic.gdx.utils.Align;

import com.maanoo.leabound.core.board.Board;
import com.maanoo.leabound.core.gene.BoardArea;
import com.maanoo.leabound.core.gene.Concept;
import com.maanoo.leabound.core.gene.RewardItemGen;
import com.maanoo.leabound.core.gene.SubGenerator;
import com.maanoo.leabound.core.item.Item;
import com.maanoo.leabound.core.thing.LockedChest;
import com.maanoo.leabound.core.thing.OnePassDoor;
import com.maanoo.leabound.core.thing.PickUp;
import com.maanoo.leabound.core.thing.Wall;
import com.maanoo.leabound.core.util.Location;
import com.maanoo.leabound.core.util.Ra;


public class GenPassTwoChest extends SubGenerator {

	// TODO convert to SubGeneratorTransform

	private final float defficulty;

	protected GenPassTwoChest(Concept size, float defficulty) {
		super(a(Concept.Pass, size));
		this.defficulty = defficulty;
	}

	public static class Big extends GenPassTwoChest {

		public Big() {
			super(Concept.Big, .6f);
		}

		@Override
		public boolean can(Board board, BoardArea area, int emptyAreas, boolean small, boolean medium,
				boolean big) {
			return big;
		}

	}

	public static class Medium extends GenPassTwoChest {

		public Medium() {
			super(Concept.Medium, .4f);
		}

		@Override
		public boolean can(Board board, BoardArea area, int emptyAreas, boolean small, boolean medium,
				boolean big) {
			return medium;
		}

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
			p3 = p1.cpy(1, 2);
		} else {
			p3 = p1.cpy(2, 1);
		}

		final Item[] keys = new Item[] { Item.Key, Item.KeyA, Item.KeyB, Item.KeyC };
		Ra.shuffle(keys);

		final Item key1 = keys[0];
		final Item key2 = keys[1];

		final PickUp pickup1 = new PickUp(p1, key1, 0);
		final Item pickup2 = key2;
		final Item pickup3 = RewardItemGen.get(defficulty, 1)[0];

		board.addThing(pickup1);
		board.addThing(new LockedChest(p2, pickup1.getItem(), pickup2));
		board.addThing(new LockedChest(p3, pickup2, pickup3));

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
