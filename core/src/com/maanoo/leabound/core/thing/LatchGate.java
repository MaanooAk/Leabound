package com.maanoo.leabound.core.thing;

import com.maanoo.leabound.core.util.Direction;
import com.maanoo.leabound.core.util.Location;


public class LatchGate extends Gate {

	public LatchGate(Location location, Direction rotation) {
		super("Latch Gate", location, rotation, "latch-gate");
	}

	@Override
	protected boolean mustActivate(int sources) {
		return sources > 0;
	}

	@Override
	protected boolean mustDeactivate(int sources) {
		return false;
	}

}
