package com.maanoo.leabound.core.board;

import com.badlogic.gdx.utils.Align;
import com.maanoo.leabound.core.util.Direction;

public class BoardTransfom {

	private BoardTransfom() {
	}

	public Direction rotation(Direction direction) {
		return direction;
	}

	public int align(int align) {
		return align;
	}

	// === instances ===

	public static final BoardTransfom Identity = new BoardTransfom();

	public static final BoardTransfom FlipX = new BoardTransfom() {

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

	public static final BoardTransfom FlipY = new BoardTransfom() {

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

	public static final BoardTransfom FlipXY = new BoardTransfom() {

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
