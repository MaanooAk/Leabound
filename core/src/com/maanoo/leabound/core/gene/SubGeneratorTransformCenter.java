package com.maanoo.leabound.core.gene;

import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import com.maanoo.leabound.core.gene.SubGenerator.SubGeneratorTransform;
import com.maanoo.leabound.core.thing.Thing;
import com.maanoo.leabound.core.util.Location;


public abstract class SubGeneratorTransformCenter extends SubGeneratorTransform {

	private static final Array<Thing> tmpthings = new Array<Thing>();

	public SubGeneratorTransformCenter(Concept[] Concepts) {
		super(Concepts);
	}

	@Override
	protected final void generate(Array<Thing> things, BoardArea area, float level) {
		tmpthings.clear();

		generate(tmpthings, level);

		final BoardArea used = new BoardArea(tmpthings.first().getLocation());
		for (final Thing i : tmpthings) {
			used.extend(i.getLocation(), 0);
		}

		final Location offset = area.get(Align.center);
		offset.add(-used.x - used.w / 2, -used.y - used.h / 2);

		for (final Thing i : tmpthings) {
			i.getLocation().add(offset);
		}

		things.addAll(tmpthings);
	}

	protected abstract void generate(Array<Thing> things, float level);

}
