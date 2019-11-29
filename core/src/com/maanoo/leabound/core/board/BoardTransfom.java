package com.maanoo.leabound.core.board;

import com.badlogic.gdx.utils.Align;
import com.maanoo.leabound.core.util.Direction;
import com.maanoo.leabound.core.util.Location;

public class BoardTransfom {

	private String name;

	private BoardTransfom(String name) {
		this.name = name;
	}

	public void location(Location location, int x, int y, int w, int h) {
		// no op
	}

	public Direction rotation(Direction direction) {
		return direction;
	}

	public int align(int align) {
		return align;
	}

	@Override
	public final String toString() {
		return name;
	}

	// === instances ===

	public static final BoardTransfom Identity = new BoardTransfom("Identity");

	public static final BoardTransfom FlipX = new BoardTransfom("FlipX") {

		@Override
		public void location(Location location, int x, int y, int w, int h) {
			location.x = -location.x - 1;
		}

		@Override
		public Direction rotation(Direction direction) {
			if (direction == Direction.Right) return Direction.Left;
			if (direction == Direction.Left) return Direction.Right;
			return direction;
		}

		@Override
		public int align(int align) {
			if (align == Align.right) return Align.left;
			if (align == Align.left) return Align.right;
			return align;
		}

	};

	public static final BoardTransfom FlipY = new BoardTransfom("FlipY") {

		@Override
		public void location(Location location, int x, int y, int w, int h) {
			location.y = -location.y - 1;
		}

		@Override
		public Direction rotation(Direction direction) {
			if (direction == Direction.Up) return Direction.Down;
			if (direction == Direction.Down) return Direction.Up;
			return direction;
		}

		@Override
		public int align(int align) {
			if (align == Align.top) return Align.bottom;
			if (align == Align.bottom) return Align.top;
			return align;
		}

	};

	public static final BoardTransfom FlipXY = new BoardTransfom("FlipXY") {

		@Override
		public void location(Location location, int x, int y, int w, int h) {
			FlipX.location(location, x, y, w, h);
			FlipY.location(location, x, y, w, h);
		}

		@Override
		public Direction rotation(Direction direction) {
			return FlipX.rotation(FlipY.rotation(direction));
		}

		@Override
		public int align(int align) {
			return FlipX.align(FlipY.align(align));
		}

	};

}
