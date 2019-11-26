package com.maanoo.leabound.core.thing;

import com.maanoo.leabound.core.util.Direction;
import com.maanoo.leabound.core.util.Location;


public class NotGate extends Gate {

	public NotGate(Location location, Direction rotation) {
		super("Not Gate", location, rotation, "not-gate");

		activate();
	}

	@Override
	protected boolean mustActivate(int sources) {
		return sources == 0;
	}

}
