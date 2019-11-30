package com.maanoo.leabound.core.gene.subs;

import com.badlogic.gdx.utils.Align;

import com.maanoo.leabound.core.board.Board;
import com.maanoo.leabound.core.gene.BoardArea;
import com.maanoo.leabound.core.gene.Concept;
import com.maanoo.leabound.core.gene.SubGenerator;
import com.maanoo.leabound.core.gene.ThingGenerator;
import com.maanoo.leabound.core.util.Location;
import com.maanoo.leabound.core.util.Ra;


public class GenCenterThing extends SubGenerator {

	private final ThingGenerator generator;

	public GenCenterThing(ThingGenerator generator) {
		super(a(Concept.Dummy, Concept.Small));
		this.generator = generator;
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

	@Override
	public int getPadding() {
		return 0;
	}

}
