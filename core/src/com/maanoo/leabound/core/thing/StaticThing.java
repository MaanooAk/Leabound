package com.maanoo.leabound.core.thing;

import com.maanoo.leabound.core.util.Location;


public abstract class StaticThing extends Thing {

	private final String drawable;
	private final boolean blocking;

	public StaticThing(String name, Location location, String drawable, boolean blocking) {
		super(name, location, null);
		this.drawable = drawable;
		this.blocking = blocking;
	}

	@Override
	public final boolean isBlocking() {
		return blocking;
	}

	@Override
	public final String getDrawable() {
		return drawable;
	}

}
