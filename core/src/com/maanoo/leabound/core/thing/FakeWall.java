package com.maanoo.leabound.core.thing;

import com.badlogic.gdx.utils.Align;

import com.maanoo.leabound.core.Player;
import com.maanoo.leabound.core.board.BoardTransfom;
import com.maanoo.leabound.core.item.Item;
import com.maanoo.leabound.core.util.Direction;
import com.maanoo.leabound.core.util.Location;


public final class FakeWall extends Thing {

	// TODO crate abstract of health kind things

	private final String[] drawables;
	private final int health;

	private int damage;

	public FakeWall(Location location) {
		super("Fake Wall", location, Direction.random());

		drawables = new String[] {
				"fake-wall", "fake-wall_1", "fake-wall_2", "fake-wall_3",
		};

		health = drawables.length;
		damage = 0;
	}

	@Override
	public void reset(BoardTransfom tra) {
		super.reset(tra);

		damage = 0;
	}

	@Override
	public boolean isBlocking() {
		return true;
	}

	@Override
	public boolean onPlayerBounce(Player player) {
		damage += 1;

		if (damage == health) {
			destroy();

			player.getBoard().addThing(new PickUp(getLocation().cpy(), Item.Parts, Align.center));
		}

		mod();
		return true;
	}

	@Override
	public String getDrawable() {
		return drawables[damage];
	}

}
