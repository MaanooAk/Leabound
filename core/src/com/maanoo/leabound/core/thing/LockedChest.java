package com.maanoo.leabound.core.thing;

import com.maanoo.leabound.core.Player;
import com.maanoo.leabound.core.board.Board;
import com.maanoo.leabound.core.item.Item;
import com.maanoo.leabound.core.util.Location;


public class LockedChest extends StaticThing {

	private final Item key;

	private final Board board;
	private final PickUp pickup;

	public LockedChest(Location location, Item key, Board board, PickUp pickup) {
		super("Locked Chest", location, "locked-chest_" + key.getName(), true);
		this.key = key;
		this.board = board;
		this.pickup = pickup;
	}

	@Override
	public boolean onPlayerBounce(Player player) {

		if (player.deductItem(key, 1)) {

			destroy();

			pickup.getLocation().set(getLocation());
			board.addThing(pickup);

			return true;
		} else {
			player.messages.add("You [warning]do not[] have the correct [key]key[]");
			return false;
		}

	}

}
