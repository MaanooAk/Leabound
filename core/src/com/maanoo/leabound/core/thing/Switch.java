package com.maanoo.leabound.core.thing;

import com.maanoo.leabound.core.Player;
import com.maanoo.leabound.core.util.Location;


public class Switch extends StateThing {

	// TODO add directed switch

	private static final SimpleState Off = new SimpleState("switch_idle", true);
	private static final SimpleState On = new SimpleState("switch_active", true);

	public Switch(Location location) {
		super("Switch", location, null, Off, On);
	}

	@Override
	public boolean onPlayerBounce(Player player) {

		if (isActive()) {
			deactivate();
		} else {
			activate();
		}

		nextState();
		return true;
	}

}
