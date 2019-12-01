package com.maanoo.leabound.core.gene.subs;

import com.badlogic.gdx.utils.Array;

import com.maanoo.leabound.core.gene.RewardItemGen;
import com.maanoo.leabound.core.item.Item;
import com.maanoo.leabound.core.thing.Dispenser;
import com.maanoo.leabound.core.thing.NotGate;
import com.maanoo.leabound.core.thing.PressurePlate;
import com.maanoo.leabound.core.thing.Thing;
import com.maanoo.leabound.core.thing.Wire;
import com.maanoo.leabound.core.util.Direction;
import com.maanoo.leabound.core.util.Location;


public class GenLogicZistorPlus extends GenLogicZistor {

	@Override
	protected void generate(Array<Thing> things, float level) {

		final Item[] rewards = RewardItemGen.get(.75f, 1);

		final Location p = new Location();

		things.add(new PressurePlate(adv(p)));
		things.add(new Wire(adv(p)));
		branch(things, adv(p));
		things.add(new Wire(adv(p)));
		things.add(new NotGate(adv(p), Direction.Right));
		things.add(new Wire(adv(p)));
		branch(things, adv(p));
		things.add(new Wire(adv(p)));
		branch(things, adv(p));
		things.add(new Wire(adv(p)));

		things.add(new Dispenser(adv(p), Direction.Up, rewards[0]));
	}

}
