package com.maanoo.leabound.core.gene;

import com.badlogic.gdx.utils.Array;

import com.maanoo.leabound.core.Player;
import com.maanoo.leabound.core.board.Board;
import com.maanoo.leabound.core.board.BoardTransfom;
import com.maanoo.leabound.core.board.Bound;
import com.maanoo.leabound.core.board.Guides;
import com.maanoo.leabound.core.gene.subs.GenCenterThing;
import com.maanoo.leabound.core.gene.subs.GenFake;
import com.maanoo.leabound.core.gene.subs.GenPassTwoChest;
import com.maanoo.leabound.core.gene.subs.GenReward;
import com.maanoo.leabound.core.gene.subs.GenSimpleLogic;
import com.maanoo.leabound.core.gene.subs.GenSimpleLogicBig;
import com.maanoo.leabound.core.gene.subs.SimpleMaze;
import com.maanoo.leabound.core.gene.subs.TripleAnd;
import com.maanoo.leabound.core.thing.FakeWall;
import com.maanoo.leabound.core.thing.OnePassDoor;
import com.maanoo.leabound.core.thing.Wall;
import com.maanoo.leabound.core.util.Location;
import com.maanoo.leabound.core.util.Ra;
import com.maanoo.leabound.core.util.WeightEntry;


public class Generator {

	// TODO clean up
	// TODO move rotations and flips from the sub-generators to here

	private final Array<WeightEntry<Integer>> splits;
	private final Array<WeightEntry<SubGenerator>> subs;

	public Generator() {
		splits = new Array<WeightEntry<Integer>>();
		subs = new Array<WeightEntry<SubGenerator>>();

		splits.add(new WeightEntry<Integer>(100, 1));
		splits.add(new WeightEntry<Integer>(50, 2));
		splits.add(new WeightEntry<Integer>(200, 3));
		splits.add(new WeightEntry<Integer>(50, 4));

		subs.add(new WeightEntry<SubGenerator>(25, new GenCenterThing(null)));
		subs.add(new WeightEntry<SubGenerator>(40, new GenCenterThing(ThingGenerator.Parts)));
		subs.add(new WeightEntry<SubGenerator>(100, new GenCenterThing(ThingGenerator.HealGround)));

		subs.add(new WeightEntry<SubGenerator>(100, new GenSimpleLogic()));
		subs.add(new WeightEntry<SubGenerator>(75, new GenPassTwoChest.Medium()));
		subs.add(new WeightEntry<SubGenerator>(100, new TripleAnd()));

		subs.add(new WeightEntry<SubGenerator>(100777, new GenFake()));

		subs.add(new WeightEntry<SubGenerator>(100, new GenSimpleLogicBig()));
		subs.add(new WeightEntry<SubGenerator>(75, new GenPassTwoChest.Big()));
		subs.add(new WeightEntry<SubGenerator>(100, new SimpleMaze()));
	}

	public final Board generate(Player player, ConceptSequence.Entry centry) {

		if (centry.Concepts[0] == Concept.Guide) {
			assert centry.Concepts.length == 1;

			final Board board = Guides.get(centry.message).buildBoard(player);

			board.transform(Ra.random(
					BoardTransfom.Identity, BoardTransfom.FlipX));

			return board;
		}

		// TODO implement

		return generate(player);
	}

	private final Board generate(Player player) {

		final BoardArea area = new BoardArea(-8, -7, 16, 14, 0);
		final Board b = new Board("", new Bound(area.w, area.h, player));

		final Array<BoardArea> areas = new Array<BoardArea>();
		areas.add(area);

		// concepts

		final boolean coReward = player.getBoardIndex() % 5 == 4;

		// split areas

		final int count;
		if (coReward) {
			count = 1;
		} else {
			count = Ra.random(splits).get();
		}
//		System.out.println("Splits: " + count);

		splitBoardArea(areas, count);

		// generate areas

		final Array<WeightEntry<SubGenerator>> asubs = new Array<WeightEntry<SubGenerator>>();

		int padding = 0;

		int emptyAreas = areas.size;
		for (final BoardArea i : areas) {
			emptyAreas -= 1;

			if (coReward) {

				asubs.clear();
				asubs.add(new WeightEntry<SubGenerator>(10, new GenReward()));

			} else {

				asubs.clear();
				for (final WeightEntry<SubGenerator> sub : subs) {
					if (sub.get().can(b, i, emptyAreas)) asubs.add(sub);
				}

			}

			final SubGenerator sub = Ra.randomWeighted(asubs).get();

			sub.generate(b, i, player.getLevel());

			padding = Math.max(padding, sub.getPadding());

			// keep for debugging
//			for (int x = 1; x < i.w - 1; x++) {
//				b.addThing(new OnePassDoor(new Location(i.x + x, i.y)));
//				b.addThing(new OnePassDoor(new Location(i.x + x, i.y + i.h - 1)));
//			}
//			for (int y = 1; y < i.h - 1; y++) {
//				b.addThing(new OnePassDoor(new Location(i.x, i.y + y)));
//				b.addThing(new OnePassDoor(new Location(i.x + i.w - 1, i.y + y)));
//			}

		}

		decorate(b, area, padding);

		// == transform

		b.transform(Ra.random(
				BoardTransfom.Identity, BoardTransfom.FlipX, BoardTransfom.FlipY, BoardTransfom.FlipXY));

		// == free the center

		if (b.hasThing(Location.Zero)) {
			if (b.getThing(Location.Zero).isBlocking()) {
				b.addThing(new OnePassDoor(new Location(0, 0)));
			}
		}

		b.onCreate();

		return b;
	}

	private void splitBoardArea(final Array<BoardArea> areas, final int count) {

		final boolean order = Ra.bool();
		final Splitter first = order ? Splitter.HalfH : Splitter.HalfV;
		final Splitter second = order ? Splitter.HalfV : Splitter.HalfH;

		if (count >= 2) {
			areas.addAll(first.split(areas.removeIndex(0)));
		}
		if (count >= 3) {
			areas.addAll(second.split(areas.removeIndex(0)));
		}
		if (count >= 4) {
			areas.addAll(second.split(areas.removeIndex(0)));
		}

	}

	private void decorate(Board b, BoardArea area, int padding) {
		final Location i = new Location();
		final Location inext1 = new Location();
		final Location inext2 = new Location();

		final BoardArea using = new BoardArea(0, 0, 0, 0, 0);

		for (int x = 0; x < area.w; x++) {
			for (int y = 0; y < area.h; y++) {
				i.set(area.x + x, area.y + y);

				if (b.hasThing(i)) {
					using.extend(i, padding);
				}
			}
		}

		final Array<Location> walls = new Array<Location>();

		for (int x = 0; x < area.w; x++) {
			for (int y = 0; y < area.h; y++) {
				i.set(area.x + x, area.y + y);

				inext1.set(i).add(Ra.next(3) - 1, Ra.next(3) - 1);
				inext2.set(i).add(Ra.next(3) - 1, Ra.next(3) - 1);

				final int next = (using.contains(inext1) ? 1 : 0) + (using.contains(inext2) ? 1 : 0);

				if (!using.contains(i) && Ra.chance(0.2f - next * 0.05f)) {

					walls.add(i.cpy());
				}
			}
		}

		walls.shuffle();

		if (walls.size > 2) {
			// add a single fake wall
			b.addThing(new FakeWall(walls.pop()));
		}

		while (walls.size > 0) {
			b.addThing(new Wall(walls.pop()));
		}
	}

}
