package com.maanoo.leabound.core.board;

import java.util.Iterator;

import com.maanoo.leabound.core.thing.Thing;
import com.maanoo.leabound.core.util.Location;
import com.maanoo.leabound.core.util.VectorMap;


public class Board implements Iterable<Thing> {

	public final String name;

	private final Bound bound;

	private final VectorMap<Thing> things;

	public Board(String name, Bound bound) {
		this.name = name;
		this.bound = bound;

		things = new VectorMap<Thing>();
	}

	public Bound getBound() {
		return bound;
	}

	public void addThing(Thing thing) {
		things.put(thing.getLocation(), thing);
	}

	public boolean hasThing(Location location) {
		return things.contains(location);
	}

	public Thing getThing(Location location) {
		return things.get(location);
	}

	public void removeThing(Location location) {
		things.remove(location);
	}

	@Override
	public Iterator<Thing> iterator() {
		return things.values().iterator();
	}

	// === events ===

	public void onCreate() {

		for (final Thing i : things.valuesInstace()) {

			updateOnActvateChange(i);
		}

	}

	public void updateOnActvateChange(Thing source) {

		for (final Thing i : things.valuesInstace()) { // TODO iter ?
			if (i.isDestroyed()) continue;

			final boolean change = i.onThingActiveChange(source);
			if (change) {
				updateOnActvateChange(i);
			}
		}

	}

}
