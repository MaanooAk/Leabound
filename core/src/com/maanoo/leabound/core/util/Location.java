package com.maanoo.leabound.core.util;

public class Location {

	// TODO simplify and extend

	public static final Location Zero = new Location();

	public int x;
	public int y;

	public Location() {
		this.x = 0;
		this.y = 0;
	}

	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Location(Location location) {
		this(location.x, location.y);
	}

	public Location cpy() {
		return new Location(x, y);
	}

	public Location set(Location vector) {
		this.x = vector.x;
		this.y = vector.y;
		return this;
	}

	public Location set(int x, int y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public Location add(Location vector) {
		this.x += vector.x;
		this.y += vector.y;
		return this;
	}

	public Location add(int x, int y) {
		this.x += x;
		this.y += y;
		return this;
	}

	public Location mulAdd(Location vector, int mul) {
		this.x += vector.x * mul;
		this.y += vector.y * mul;
		return this;
	}

	public float dst(Location v) {
		final float x_d = v.x - x;
		final float y_d = v.y - y;
		return x_d * x_d + y_d * y_d;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;

		final Location other = (Location) obj;
		return x == other.x && y == other.y;
	}

	@Override
	public int hashCode() { // TODO
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

}
