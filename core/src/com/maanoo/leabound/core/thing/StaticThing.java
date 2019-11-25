package com.maanoo.leabound.core.thing;

import com.badlogic.gdx.math.Vector2;


public abstract class StaticThing extends Thing {

	private final String drawable;
	private final boolean blocking;

	public StaticThing(String name, Vector2 location, String drawable, boolean blocking) {
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
