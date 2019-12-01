package com.maanoo.leabound.core.gene.subs;

import com.badlogic.gdx.utils.Array;

import com.maanoo.leabound.core.gene.Concept;
import com.maanoo.leabound.core.gene.RewardItemGen;
import com.maanoo.leabound.core.gene.SubGeneratorTransformCenter;
import com.maanoo.leabound.core.item.Item;
import com.maanoo.leabound.core.thing.AndGate;
import com.maanoo.leabound.core.thing.Dispenser;
import com.maanoo.leabound.core.thing.NotGate;
import com.maanoo.leabound.core.thing.PressurePlate;
import com.maanoo.leabound.core.thing.Switch;
import com.maanoo.leabound.core.thing.Thing;
import com.maanoo.leabound.core.thing.Wire;
import com.maanoo.leabound.core.util.Direction;
import com.maanoo.leabound.core.util.Location;
import com.maanoo.leabound.core.util.Ra;


public class GenLogicZistor extends SubGeneratorTransformCenter {

	public GenLogicZistor() {
		super(a(Concept.AdvLogic, Concept.Big));
	}

	@Override
	protected void generate(Array<Thing> things, float level) {

		final Item[] rewards = RewardItemGen.get(.75f, 1);

		final Location p = new Location();

		things.add(new PressurePlate(adv(p)));
		things.add(new Wire(adv(p)));
		branch(things, adv(p));
		things.add(new Wire(adv(p)));
		branch(things, adv(p));
		things.add(new Wire(adv(p)));
		branch(things, adv(p));
		things.add(new Wire(adv(p)));

		things.add(new Dispenser(adv(p), Direction.Up, rewards[0]));
	}

	protected Location adv(final Location p) {
		return p.add(1, 0).cpy();
	}

	protected Location advU(final Location p) {
		return p.add(0, 1).cpy();
	}

	protected void branch(Array<Thing> things, final Location p) {

		things.add(new AndGate(p.cpy(), Direction.Right));
		things.add(new Wire(advU(p)));

		if (Ra.chance(.3f)) {
			things.add(new Wire(advU(p)));
		}

		if (Ra.bool()) {
			things.add(new NotGate(advU(p), Direction.Down));
		} else {
			things.add(new Wire(advU(p)));
		}
		things.add(new Wire(advU(p)));
		things.add(new Switch(advU(p)));
	}

}
