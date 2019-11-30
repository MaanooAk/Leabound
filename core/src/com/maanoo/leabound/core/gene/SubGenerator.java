package com.maanoo.leabound.core.gene;

import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import com.maanoo.leabound.core.board.Board;
import com.maanoo.leabound.core.board.BoardTransfom;
import com.maanoo.leabound.core.thing.FakeWall;
import com.maanoo.leabound.core.thing.Thing;
import com.maanoo.leabound.core.thing.Wall;
import com.maanoo.leabound.core.thing.Wire;
import com.maanoo.leabound.core.util.Location;
import com.maanoo.leabound.core.util.Ra;


public abstract class SubGenerator {

	// === class ===

	public final Concept[] Concepts;

	public SubGenerator(Concept[] Concepts) {
		this.Concepts = Concepts;
	}

	protected final static Concept[] a(Concept... elements) {
		return elements;
	}

	public final boolean hasConcept(Concept Concept) {

		for (final Concept i : Concepts) {
			if (i.equals(Concept)) return true;
		}
		return false;
	}

	public final boolean can(Board board, BoardArea area, int emptyAreas) {
		return can(board, area, emptyAreas, //
				area.getLocations() < BoardArea.LocationsSM,
				area.getLocations() >= BoardArea.LocationsSM && area.getLocations() <= BoardArea.LocationsMB,
				area.getLocations() > BoardArea.LocationsMB);
	}

	public boolean can(Board board, BoardArea area, int emptyAreas, boolean small, boolean medium, boolean big) {

		if (small && hasConcept(Concept.Small)) return true;
		if (medium && hasConcept(Concept.Medium)) return true;
		if (big && hasConcept(Concept.Big)) return true;

		return false;
	}

	// TODO use this instead, is it needed?
//	public Object generate(Board board, BoardArea area, Player player) {
//		return generate(board, area, player.getLevel());
//	}

	public abstract Object generate(Board board, BoardArea area, float level);

	public int getPadding() {
		return 1;
	}

	// === abstract ===

	public static abstract class SubGeneratorTransform extends SubGenerator {

		private final BoardTransfom[] transforms;

		public SubGeneratorTransform(Concept[] Concepts, BoardTransfom... transforms) {
			super(Concepts);
			this.transforms = transforms;
		}

		public SubGeneratorTransform(Concept[] Concepts) {
			this(Concepts, BoardTransfom.Identity, BoardTransfom.FlipX, BoardTransfom.FlipY, BoardTransfom.FlipXY);
		}

		@Override
		public final Object generate(Board board, BoardArea area, float level) {
			final Array<Thing> things = new Array<Thing>();

			final BoardTransfom tra = Ra.random(transforms);

			final int x = area.x;
			final int y = area.y;
			final int w = area.w;
			final int h = area.h;

			final BoardArea harea;
			if (!area.isHorizontal()) {
				// TODO rotate area.align
				harea = new BoardArea(area.x, area.y, area.h, area.w, area.align);
			} else {
				harea = area;
			}

			generate(things, harea, level);

			if (!area.isHorizontal()) {

				final BoardTransfom traR = BoardTransfom.Rotate;

				for (final Thing i : things) {
					traR.location(i.getLocation(), x, y, w, h);
					i.reset(traR);
				}
			}

			for (final Thing i : things) {
				tra.location(i.getLocation(), x, y, w, h);
				i.reset(tra);

				board.addThing(i);
			}

			return null;
		}

		protected abstract void generate(Array<Thing> things, BoardArea area, float level);

		protected void generateDebug(Array<Thing> things, BoardArea area) {

			things.add(new Wire(area.get(Align.topRight)));
			things.add(new Wire(area.get(Align.topLeft)));
			things.add(new Wire(area.get(Align.bottomRight)));
			things.add(new Wire(area.get(Align.bottomLeft)));
		}

		protected void makeFirstWallFake(Array<Thing> things) {

			Thing wall = null;

			for (int i = 0; i < things.size; i++) {
				if (things.get(i) instanceof Wall) {
					wall = things.removeIndex(i);
					break;
				}
			}

			if (wall == null) return;

			things.add(new FakeWall(wall.getLocation()));
		}

	}

	// === impls ===

	// TODO move out impls
	// TODO use RewardItemGen

	// === utils ===

	protected static Location between(final Location p1, final Location p2) {
		return new Location(
				p1.x + Math.abs(p2.x - p1.x) / 2,
				p1.y + Math.abs(p2.y - p1.y) / 2);

	}

	protected static boolean wire(Board board, Location p1, Location p2) {

		// TODO convert to use thing array

		if (p1.y == p2.y && Math.abs(p1.x - p2.x) == 1) {
			// adjecent
		} else if (p1.x == p2.x && Math.abs(p1.y - p2.y) == 1) {
			// adjecent

		} else if (p1.y == p2.y) {

			if (p1.x > p2.x) return wire(board, p2, p1);

			for (int x = p1.x + 1; x <= p2.x - 1; x++) {
				board.addThing(new Wire(new Location(x, p1.y)));
			}

		} else if (p1.x == p2.x) {

			if (p1.y > p2.y) return wire(board, p2, p1);

			for (int y = p1.y + 1; y <= p2.y - 1; y++) {
				board.addThing(new Wire(new Location(p1.x, y)));
			}

		} else {

			final Location corner = new Location(p2.x, p1.y);

			wire(board, p1, corner);
			wire(board, corner, p2);

			board.addThing(new Wire(corner));
		}

		return true;
	}

}
