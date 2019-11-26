package com.maanoo.leabound.core.thing;

import com.maanoo.leabound.core.util.Direction;
import com.maanoo.leabound.core.util.Location;


public abstract class Gate extends Thing {

	private final String drawableOff;
	private final String drawableOn;

	private final Wire internal;

	private final transient Location outputLocation;

	public Gate(String name, Location location, Direction rotation, boolean startActive,
			String drawable) {
		this(name, location, rotation, startActive, drawable, drawable + "_on");
	}

	private Gate(String name, Location location, Direction rotation, boolean startActive,
			String drawableOff, String drawableOn) {
		super(name, location, rotation);
		this.drawableOff = drawableOff;
		this.drawableOn = drawableOn;

		outputLocation = location.cpy().add(rotation.vector);

		internal = new Wire(location);

		if (startActive) activate();
	}

	@Override
	public final boolean isBlocking() {
		return false;
	}

	// === events ===

	@Override
	public final boolean onCreate() {
		return onThingActiveChange(this);
	}

	@Override
	public final boolean onThingActiveChange(Thing thing) {

		if (!internal.onThingActiveChange(thing)) return false;
		internal.getSources().removeValue(this, true);

//		internal.onThingActiveChange(thing);

		if (!isActive() && mustActivate(internal.getSources().size)) {
			activate();
			return true;
		} else if (isActive() && mustDeactivate(internal.getSources().size)) {
			deactivate();
			return true;
		}

		return false;
	}

	protected abstract boolean mustActivate(int sources);

	protected boolean mustDeactivate(int sources) {
		return !mustActivate(sources);
	}

	// === access ===

	@Override
	public final String getDrawable() {
		return isActive() ? drawableOn : drawableOff;
	}

	public final Wire getInternal() {
		return internal;
	}

	@Override
	public boolean activeFlow(Location location) {
		return location.equals(outputLocation);
	}

}
