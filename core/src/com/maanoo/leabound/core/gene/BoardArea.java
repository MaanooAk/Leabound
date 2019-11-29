package com.maanoo.leabound.core.gene;

import static com.badlogic.gdx.utils.Align.bottom;
import static com.badlogic.gdx.utils.Align.left;
import static com.badlogic.gdx.utils.Align.right;
import static com.badlogic.gdx.utils.Align.top;

import com.maanoo.leabound.core.util.Location;


public class BoardArea {

	public static final int LocationsSM = 60;
	public static final int LocationsMB = 120;

	public int x;
	public int y;
	public int w;
	public int h;
	public int align;

	public BoardArea(int x, int y, int w, int h, int align) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.align = align;
	}

	public BoardArea(BoardArea other) {
		this(other.x, other.y, other.w, other.h, other.align);
	}

	public void extend(Location location, int around) {
		if (location.x < x + around) {
			final int dx = x - location.x + around;
			x -= dx;
			w += dx;
		} else if (location.x + around >= x + w) {
			w = location.x - x + 1 + around;
		}

		if (location.y < y + around) {
			final int dy = y - location.y + around;
			y -= dy;
			h += dy;
		} else if (location.y + around >= y + h) {
			h = location.y - y + 1 + around;
		}
	}

	// === access ===

	public boolean isSquary() {
		return Math.abs(w - h) <= 2;
	}

	public boolean isHorizontal() {
		return w > h;
	}

	public int getLocations() {
		return w * h;
	}

	public int getX(int alignment) {
		int x = this.x;
		if ((alignment & right) != 0) x += w - 1;
		else if ((alignment & left) == 0) x += w / 2;
		return x;
	}

	public int getY(int alignment) {
		int y = this.y;
		if ((alignment & top) != 0) y += h - 1;
		else if ((alignment & bottom) == 0) y += h / 2;
		return y;
	}

	public Location get(int alignment) {
		return new Location(getX(alignment), getY(alignment));
	}

	public int getMinSize() {
		return w < h ? w : h;
	}

	public boolean contains(int x, int y) {
		return this.x <= x && this.x + w > x && this.y <= y && this.y + h > y;
	}

	public boolean contains(Location point) {
		return contains(point.x, point.y);
	}

	// === object ===

	@Override
	public String toString() {
		return "[" + x + "," + y + "," + w + "," + h + "]";
	}

}
