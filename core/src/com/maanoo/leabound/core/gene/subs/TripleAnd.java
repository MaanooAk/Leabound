package com.maanoo.leabound.core.gene.subs;

import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import com.maanoo.leabound.core.gene.BoardArea;
import com.maanoo.leabound.core.gene.Concept;
import com.maanoo.leabound.core.gene.SubGenerator.SubGeneratorTransform;
import com.maanoo.leabound.core.item.Item;
import com.maanoo.leabound.core.thing.AndGate;
import com.maanoo.leabound.core.thing.Dispenser;
import com.maanoo.leabound.core.thing.NotGate;
import com.maanoo.leabound.core.thing.Switch;
import com.maanoo.leabound.core.thing.Thing;
import com.maanoo.leabound.core.thing.Wire;
import com.maanoo.leabound.core.util.Direction;
import com.maanoo.leabound.core.util.Location;
import com.maanoo.leabound.core.util.Ra;


public class TripleAnd extends SubGeneratorTransform {

	public TripleAnd() {
		super(a(Concept.Logic, Concept.Medium));
	}

	@Override
	protected void generate(Array<Thing> things, BoardArea area, float level) {

		final Location p = area.get(Align.center);

		final Direction dispencerRotation = Ra.random(Direction.Left, Direction.Left, Direction.Up, Direction.Down);

		final int perm = Ra.next(3);

		p.add(-4, 0);
		things.add(new Dispenser(p.cpy(), dispencerRotation, Item.Parts));
		p.add(1, 0);
		things.add(new Wire(p.cpy()));
		p.add(1, 0);
		things.add(new AndGate(p.cpy(), Direction.Left));
		things.add(new Wire(p.cpy(0, 1)));
		things.add(new Wire(p.cpy(0, -1)));
		p.add(1, 0);
		if (perm == 0) {
			things.add(new Wire(p.cpy(0, 1)));
			things.add(new NotGate(p.cpy(0, -1), Direction.Left));
		} else if (perm == 1) {
			things.add(new NotGate(p.cpy(0, 1), Direction.Left));
			things.add(new Wire(p.cpy(0, -1)));
		} else {
			things.add(new Wire(p.cpy(0, 1)));
			things.add(new Wire(p.cpy(0, -1)));
		}
		p.add(1, 0);
		things.add(new Wire(p.cpy(0, 1)));
		things.add(new Wire(p.cpy(0, -1)));
		p.add(1, 0);
		things.add(new AndGate(p.cpy(0, 1), Direction.Left));
		things.add(new AndGate(p.cpy(0, -1), Direction.Left));
		things.add(new Wire(p.cpy(0, 2)));
		things.add(new Wire(p.cpy(0, 0)));
		things.add(new Wire(p.cpy(0, -2)));
		p.add(1, 0);
		things.add(new Wire(p.cpy(0, 2)));
		if (perm == 2) {
			things.add(new NotGate(p.cpy(0, 0), Direction.Left));
		} else {
			things.add(new Wire(p.cpy(0, 0)));
		}
		things.add(new Wire(p.cpy(0, -2)));
		p.add(1, 0);
		things.add(new Switch(p.cpy(0, 2)));
		things.add(new Wire(p.cpy(0, 0)));
		things.add(new Switch(p.cpy(1, 0)));
		things.add(new Switch(p.cpy(0, -2)));

	}

}