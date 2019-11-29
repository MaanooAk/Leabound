package com.maanoo.leabound.core.thing;

import com.maanoo.leabound.core.Player;
import com.maanoo.leabound.core.board.BoardTransfom;
import com.maanoo.leabound.core.util.Direction;
import com.maanoo.leabound.core.util.Location;

public final class Dispenser extends StateThing {

	private static final SimpleState Idle = new SimpleState("dispenser_idle", true);
	private static final SimpleState Used = new SimpleState("dispenser_used", true);

	private final PickUp dispenseObject;

	public Dispenser(Location location, Direction rotation, PickUp dispenseObject) {
		super("Dispenser", location, rotation, Idle, Used);
		this.dispenseObject = dispenseObject;
	}

	@Override
	public void reset(BoardTransfom tra) {
		super.reset(tra);

		dispenseObject.reset(tra);
	}

	@Override
	public boolean onThingActiveChange(Thing thing, Player player) {
		if (thing == this) return false;

		if (getState() == Used) return false;

		if (!thing.isActive()) return false;
		if (!activeFlow(thing.getLocation())) return false;

		dispenseObject.getLocation().set(getLocation()).add(getRotation().vector);
		player.getBoard().addThing(dispenseObject);

		return nextState();
	}

}
