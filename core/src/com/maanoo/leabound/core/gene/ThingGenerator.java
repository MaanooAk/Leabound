package com.maanoo.leabound.core.gene;

import com.maanoo.leabound.core.board.Board;
import com.maanoo.leabound.core.item.Item;
import com.maanoo.leabound.core.thing.PickUp;
import com.maanoo.leabound.core.thing.Thing;
import com.maanoo.leabound.core.util.Location;


public abstract class ThingGenerator {

	// TODO remove

	public static final ThingGenerator Parts = new ThingGenerator() {

		@Override
		public Thing generate(Board board, Location location) {
			return new PickUp(location, Item.Parts, 0);
		}

	};

	public static final ThingGenerator HealGround = new ThingGenerator() {

		@Override
		public Thing generate(Board board, Location location) {
			return new PickUp(location, Item.Heal, 0);
		}

	};

	public abstract Thing generate(Board board, Location location);

}
