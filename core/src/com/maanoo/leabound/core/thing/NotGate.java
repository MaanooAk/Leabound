package com.maanoo.leabound.core.thing;

import com.maanoo.leabound.core.board.BoardTransfom;
import com.maanoo.leabound.core.util.Direction;
import com.maanoo.leabound.core.util.Location;


public final class NotGate extends Gate {

	public NotGate(Location location, Direction rotation) {
		super("Not Gate", location, rotation, "not-gate");

		activate();
	}

	@Override
	public void reset(BoardTransfom tra) {
		super.reset(tra);

		activate();
	}

	@Override
	protected boolean mustActivate(int sources) {
		return sources == 0;
	}

}
