package com.maanoo.leabound.core.thing;

import com.maanoo.leabound.core.Player;
import com.maanoo.leabound.core.board.BoardTransfom;
import com.maanoo.leabound.core.util.Location;


public class Display extends StateThing {

	// TODO make graphic
	// TODO extract super class

	private static final SimpleState Off = new SimpleState("solid-black", true);
	private static final SimpleState On = new SimpleState("", false);

	private final String text;

	private final Wire internal;

	public Display(Location location, String text) {
		super("Display", location, null, Off, On);
		this.text = text;

		internal = new Wire(location);
	}

	@Override
	public void reset(BoardTransfom tra) {
		super.reset(tra);

		internal.getLocation().set(getLocation());
		internal.reset(tra);
	}

	@Override
	public boolean onThingActiveChange(Thing thing, Player player) {

		if (!internal.onThingActiveChange(thing, player)) return false;

		if (internal.isActive() && getState() == Off) {
			return nextState();

		} else if (!internal.isActive() && getState() == On) {
			return nextState();

		} else {
			return false;
		}
	}

	// === access ===

	public boolean isTextVisible() {
		return getState() == On;
	}

	public String getText() {
		return text;
	}

}
