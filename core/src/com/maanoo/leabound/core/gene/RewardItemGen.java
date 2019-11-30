package com.maanoo.leabound.core.gene;

import com.maanoo.leabound.core.item.Item;
import com.maanoo.leabound.core.util.Ra;


/**
 * Generates random item as rewards for boards based on difficulty and count.
 *
 * @author Akritas Akritidis
 */
public class RewardItemGen {

	public final static Item[] get(float difficulty, int count) {
		final Item[] items = new Item[count];

		for (int i = 0; i < items.length; i++) {
			items[i] = get(difficulty, i, items.length);
		}
		return items;
	}

	private static Item get(float difficulty, int index, int count) {

		if (difficulty > .5f && index == 0 && Ra.chance(difficulty - .5f)) {
			return Item.MasterKey;
		}

		return Item.Parts;
	}
}
