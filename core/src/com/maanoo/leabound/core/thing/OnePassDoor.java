package com.maanoo.leabound.core.thing;

import com.maanoo.leabound.core.Player;
import com.maanoo.leabound.core.util.Location;


public final class OnePassDoor extends StateThing {

	private static final SimpleState State1 = new SimpleState("one-pass_idle", false);
	private static final SimpleState State2 = new SimpleState("one-pass_over", false);
	private static final SimpleState State3 = new SimpleState("wall", true);

	public OnePassDoor(Location location) {
		super("One Pass Door", location, null, State1, State2, State3);
	}

	@Override
	public boolean onPlayerEnter(Player player) {
		return nextState();
	}

	@Override
	public boolean onPlayerLeave(Player player) {
		return nextState();
	}

}
