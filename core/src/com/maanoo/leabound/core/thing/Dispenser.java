package com.maanoo.leabound.core.thing;

import com.maanoo.leabound.core.board.Board;
import com.maanoo.leabound.core.util.Direction;
import com.maanoo.leabound.core.util.Location;


public final class Dispenser extends StateThing {

	private static final SimpleState Idle = new SimpleState("dispenser_idle", true);
	private static final SimpleState Used = new SimpleState("dispenser_used", true);

	private final Board board; // TODO remove
	private final PickUp dispenseObject;

	public Dispenser(Location location, Direction rotation, Board board, PickUp dispenseObject) {
		super("Dispenser", location, rotation, Idle, Used);
		this.board = board;
		this.dispenseObject = dispenseObject;
	}

	@Override
	public boolean onThingActiveChange(Thing thing) {
		if (thing == this) return false;

		if (getState() == Used) return false;

		if (!thing.isActive()) return false;
		if (!activeFlow(thing.getLocation())) return false;

		dispenseObject.getLocation().set(getLocation()).add(getRotation().vector);
		board.addThing(dispenseObject);

		return nextState();
	}

}
