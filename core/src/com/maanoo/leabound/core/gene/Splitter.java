package com.maanoo.leabound.core.gene;

import com.badlogic.gdx.utils.Align;


public abstract class Splitter {

	public static final Splitter Identity = new Identity();
	public static final Splitter HalfV = new Half(true);
	public static final Splitter HalfH = new Half(false);
	public static final Splitter Quarter = new Quarter();

	// === class ===

	public abstract BoardArea[] split(BoardArea all);

	// === impls ===

	private static class Identity extends Splitter {

		@Override
		public BoardArea[] split(BoardArea all) {

			return new BoardArea[] {
					new BoardArea(all)
			};
		}

	}

	private static class Half extends Splitter {

		private final boolean direction;

		public Half(boolean direction) {
			this.direction = direction;
		}

		@Override
		public BoardArea[] split(BoardArea all) {

			if (direction) {
				return new BoardArea[] {
						new BoardArea(all.x, all.y, all.w / 2, all.h, all.align | Align.left),
						new BoardArea(all.x + all.w / 2, all.y, all.w / 2, all.h, all.align | Align.right)
				};

			} else {
				return new BoardArea[] {
						new BoardArea(all.x, all.y, all.w, all.h / 2, all.align | Align.top),
						new BoardArea(all.x, all.y + all.h / 2, all.w, all.h / 2, all.align | Align.bottom)
				};
			}
		}

	}

	private static class Quarter extends Splitter {

		public Quarter() {
		}

		@Override
		public BoardArea[] split(BoardArea all) {

			final int x = all.x;
			final int y = all.y;
			final int w = all.w / 2;
			final int h = all.h / 2;

			return new BoardArea[] {
					new BoardArea(x, y, w, h, Align.topLeft),
					new BoardArea(x + w, y, w, h, Align.topRight),
					new BoardArea(x, y + h, w, h, Align.bottomLeft),
					new BoardArea(x + w, y + h, w, h, Align.bottomRight)
			};
		}

	}

}
