package com.maanoo.leabound.core.thing;

import com.maanoo.leabound.core.util.Direction;
import com.maanoo.leabound.core.util.Location;


public final class AndGate extends Gate {

	public AndGate(Location location, Direction rotation) {
		super("And Gate", location, rotation, "and-gate");
	}

	@Override
	protected boolean mustActivate(int sources) {
		return sources >= 2;
	}

}
