package com.maanoo.leabound.core.gene.subs;

import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import com.maanoo.leabound.core.gene.BoardArea;
import com.maanoo.leabound.core.gene.Concept;
import com.maanoo.leabound.core.gene.SubGenerator.SubGeneratorTransform;
import com.maanoo.leabound.core.item.Item;
import com.maanoo.leabound.core.thing.LockedChest;
import com.maanoo.leabound.core.thing.Thing;
import com.maanoo.leabound.core.util.Ra;


public class GenReward extends SubGeneratorTransform {

	private static final Item[] items = new Item[] { Item.Parts, Item.UpgradeLife, Item.UpgradeLife, Item.MasterKey };

	public GenReward() {
		super(a(Concept.Reward, Concept.Big));
	}

	@Override
	protected void generate(Array<Thing> things, BoardArea area, float level) {

		things.add(new LockedChest(area.get(Align.left).add(3, 2), Item.MasterKey, Ra.random(items)));
		things.add(new LockedChest(area.get(Align.right).add(-3, -2), Item.MasterKey, Ra.random(items)));

	}

}
