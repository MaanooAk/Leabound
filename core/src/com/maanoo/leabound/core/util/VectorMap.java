package com.maanoo.leabound.core.util;

import java.util.HashMap;
import java.util.Map.Entry;

import com.badlogic.gdx.utils.Array;


public final class VectorMap<T> {

	private final HashMap<Location, T> map;

	private Array<Location> locations;
	private Array<T> values;

	private boolean invalid;

	public VectorMap() {

		map = new HashMap<Location, T>();

//		locations = new Array<Vector2>();
//		values = new Array<T>();

		update();
	}

	private void update() {
//		System.out.println(System.currentTimeMillis() + " update");

		locations = new Array<Location>();
		values = new Array<T>();

		for (final Entry<Location, T> i : map.entrySet()) {
			locations.add(i.getKey());
			values.add(i.getValue());
		}

		invalid = false;
	}

	// === change ===

	public void put(Location location, T value) {
		map.put(location, value);

		invalid = true;
	}

	public void remove(Location location) {
		map.remove(location);

		invalid = true;
	}

	// === access ===

	public boolean contains(Location location) {
		return map.containsKey(location);
	}

	public T get(Location location) {
		return map.get(location);
	}

	// === iterator ===

	public Iterable<Location> locations() {
		if (invalid) update();
		return locations;
	}

	public Iterable<T> values() {
		if (invalid) update();
		return values;
	}

	public Iterable<T> valuesInstace() {
//		System.out.println(System.currentTimeMillis() + " valuesInstace");
//		new Throwable().printStackTrace();

		if (invalid) update();
		return new Array<T>(values);
	}

}
