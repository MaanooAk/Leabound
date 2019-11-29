package com.maanoo.leabound.core.thing;

import com.maanoo.leabound.core.Player;
import com.maanoo.leabound.core.util.Location;


public final class PressurePlate extends StateThing {

	private static final SimpleState Idle = new SimpleState("pressure-plate_idle", false);
	private static final SimpleState Pressed = new SimpleState("pressure-plate_pressed", false);

	public PressurePlate(Location location) {
		super("Pressure Plate", location, null, Idle, Pressed);
	}

	@Override
	public boolean onPlayerEnter(Player player) {
		activate();
		return nextState();
	}

	@Override
	public boolean onPlayerLeave(Player player) {
		deactivate();
		return nextState();
	}

}
