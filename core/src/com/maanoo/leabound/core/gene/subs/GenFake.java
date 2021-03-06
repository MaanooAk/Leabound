package com.maanoo.leabound.core.gene.subs;

import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import com.maanoo.leabound.core.gene.BoardArea;
import com.maanoo.leabound.core.gene.Concept;
import com.maanoo.leabound.core.gene.SubGenerator.SubGeneratorTransform;
import com.maanoo.leabound.core.thing.FakeWall;
import com.maanoo.leabound.core.thing.Thing;
import com.maanoo.leabound.core.util.Location;
import com.maanoo.leabound.core.util.Ra;


public class GenFake extends SubGeneratorTransform {

	public GenFake() {
		super(a(Concept.Fake, Concept.Medium, Concept.Big));
	}

	@Override
	protected void generate(Array<Thing> things, BoardArea area, float level) {

		final Location p = area.get(Align.center);

		p.add(Ra.next(1, area.w / 4), 0);

		things.add(new FakeWall(p));

	}

	@Override
	public int getPadding() {
		return 0;
	}

}
