package com.maanoo.leabound.core.thing;

import com.badlogic.gdx.utils.Array;
import com.maanoo.leabound.core.util.Location;


public final class Wire extends StateThing {

	// TODO simplify

	private static final SimpleState Off = new SimpleState("wire", false);
	private static final SimpleState On = new SimpleState("wire_on", false);

	private final Array<Thing> sources;

	public Wire(Location location) {
		super("Wire", location, null, Off, On);

		sources = new Array<Thing>(4);
	}

	// === events ===

	@Override
	public boolean onThingActiveChange(Thing thing) {
		if (thing == this) return false;

		if (sources.contains(thing, true)) {

			if (!thing.isActive()) {
				sources.removeValue(thing, true);

				if (sources.size == 0) {
					deactivate();
					nextState();
				}
				return true;
			}
			return false;
		}

		if (!thing.isActive()) return false;
		if (!thing.activeFlow(getLocation())) return false;

		if (thing instanceof Wire) {
			final Wire wire = (Wire) thing;

			final int startSize = sources.size;

			for (final Thing i : wire.sources) {
				if (!sources.contains(i, true)) {
					sources.add(i);
				}
			}

			if (startSize == sources.size) return false; // no change

		} else {
			sources.add(thing);
		}

		if (!isActive()) {
			activate();
			nextState();
		}
		return true; // at least sources changed
	}

	// === access ===

	public Array<Thing> getSources() {
		return sources;
	}

}
