package com.maanoo.leabound.core.gene.subs;

import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import com.maanoo.leabound.core.gene.BoardArea;
import com.maanoo.leabound.core.gene.Concept;
import com.maanoo.leabound.core.gene.RewardItemGen;
import com.maanoo.leabound.core.gene.SubGenerator.SubGeneratorTransform;
import com.maanoo.leabound.core.item.Item;
import com.maanoo.leabound.core.thing.PickUp;
import com.maanoo.leabound.core.thing.Thing;
import com.maanoo.leabound.core.thing.Wall;
import com.maanoo.leabound.core.util.Location;


public class SimpleMaze extends SubGeneratorTransform {

	public SimpleMaze() {
		super(a(Concept.Maze, Concept.Big));
	}

	@Override
	protected void generate(Array<Thing> things, BoardArea area, float level) {

		final Item[] rewards = RewardItemGen.get(.7f, 2);

		// ==

		final Location p = area.get(Align.center);

		final Array<Thing> a1 = new Array<Thing>();
		final Array<Thing> a2 = new Array<Thing>();

		final int size = 5;
		for (int i = 1; i <= size; i += 2) {
			a1.clear();
			a2.clear();

			things.add(new Wall(p.cpy(-i - 1, i)));
			things.add(new Wall(p.cpy(-i - 1, -i - 1)));
			things.add(new Wall(p.cpy(i, i)));
			things.add(new Wall(p.cpy(i, -i - 1)));

			for (int x = -i; x < i; x++) {
				(i != 3 ? things : a1).add(new Wall(p.cpy(x, i)));
				(i != 3 ? things : a2).add(new Wall(p.cpy(x, -i - 1)));
			}
			for (int y = -i; y < i; y++) {
				(i == 3 ? things : a1).add(new Wall(p.cpy(i, y)));
				(i == 3 ? things : a2).add(new Wall(p.cpy(-i - 1, y)));
			}

			a1.shuffle();
			a2.shuffle();

			final int count = i == 1 ? 1 : 2;
			for (int j = 0; j < count; j++) { // TODO replace j

				final Location loc = (j % 2 == 0 ? a1 : a2).pop().getLocation();

				if (i == size) {
					things.add(new PickUp(loc, rewards[j], 0));
				}
			}

			things.addAll(a1);
			things.addAll(a2);
		}

		things.shuffle();
		makeFirstWallFake(things);
	}

	@Override
	public int getPadding() {
		return 0;
	}

}
