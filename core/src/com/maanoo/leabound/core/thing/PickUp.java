package com.maanoo.leabound.core.thing;

import com.maanoo.leabound.core.Player;
import com.maanoo.leabound.core.board.BoardTransfom;
import com.maanoo.leabound.core.item.Item;
import com.maanoo.leabound.core.util.Location;


public final class PickUp extends StaticThing {

	private final Item item;
	private int origin;

	public PickUp(Location location, Item item, int origin) {
		super("Pick Up", location, item.getDrawable(), false);
		this.item = item;
		this.origin = origin;
	}

	@Override
	public void reset(BoardTransfom tra) {
		super.reset(tra);

		origin = tra.align(origin);
	}

	@Override
	public boolean onPlayerEnter(Player player) {
		if (isDestroyed()) return false;

		player.pickup(item);
		destroy();

		return true;
	}

	@Override
	public int getOrigin() {
		return origin;
	}

	public Item getItem() {
		return item;
	}

}
