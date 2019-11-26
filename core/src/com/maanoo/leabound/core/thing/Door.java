package com.maanoo.leabound.core.thing;

import com.maanoo.leabound.core.Player;
import com.maanoo.leabound.core.item.Item;
import com.maanoo.leabound.core.util.Location;


public class Door extends StaticThing {

	// TODO create graphic

	private final Item key;

	public Door(Location location, Item key) {
		super("Door", location, "solid-dgrey", true);
		this.key = key;
	}

	@Override
	public boolean onPlayerBounce(Player player) {

		if (player.deductItem(key, 1)) {

			destroy();
			return true;
		} else {
			player.messages.add("You [warning]do not[] have the correct [key]key[]");
			return false;
		}
	}

}
