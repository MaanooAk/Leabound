package com.maanoo.leabound.core.thing;

import com.badlogic.gdx.utils.Align;

import com.maanoo.leabound.core.Player;
import com.maanoo.leabound.core.board.BoardTransfom;
import com.maanoo.leabound.core.item.Item;
import com.maanoo.leabound.core.util.Location;


public final class LockedChest extends StaticThing {

	private final Item key;

	private final PickUp pickup;

	public LockedChest(Location location, Item key, Item content) {
		this(location, key, new PickUp(null, content, Align.center));
	}

	private LockedChest(Location location, Item key, PickUp pickup) {
		super("Locked Chest", location, "locked-chest_" + key.getName(), true);
		this.key = key;
		this.pickup = pickup;
	}

	@Override
	public void reset(BoardTransfom tra) {
		super.reset(tra);

		pickup.reset(tra);
	}

	@Override
	public boolean onPlayerBounce(Player player) {

		if (player.deductItem(key, 1)) {

			destroy();

			pickup.getLocation().set(getLocation());
			player.getBoard().addThing(pickup);

			return true;
		} else {
			player.messages.add("You [warning]do not[] have the correct [key]key[]");
			return false;
		}

	}

}
