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

			final int xc = x + w / 2;
			final int dx = location.x - xc;

			location.x = xc - dx - (w % 2 == 1 ? 0 : 1);
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

			final int yc = y + h / 2;
			final int dy = location.y - yc;

			location.y = yc - dy - (h % 2 == 1 ? 0 : 1);
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

	public static final BoardTransfom Rotate = new BoardTransfom("FlipY") {

		@Override
		public void location(Location location, int x, int y, int w, int h) {

			final int rx = location.y - y + x;
			final int ry = location.x - x + y;

			location.set(rx, ry);
		}

		@Override
		public Direction rotation(Direction direction) {
			if (direction == Direction.Up) return Direction.Left;
			if (direction == Direction.Left) return Direction.Down;
			if (direction == Direction.Down) return Direction.Right;
			if (direction == Direction.Right) return Direction.Up;
			return direction;
		}

		@Override
		public int align(int align) {
			if (align == Align.bottom) return Align.right;
			if (align == Align.right) return Align.top;
			if (align == Align.top) return Align.left;
			if (align == Align.left) return Align.bottom;
			return align;
		}

	};

}
