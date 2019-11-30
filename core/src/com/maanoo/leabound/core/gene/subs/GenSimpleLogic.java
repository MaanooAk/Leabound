package com.maanoo.leabound.core.gene.subs;

import com.badlogic.gdx.utils.Align;

import com.maanoo.leabound.core.board.Board;
import com.maanoo.leabound.core.gene.BoardArea;
import com.maanoo.leabound.core.gene.Concept;
import com.maanoo.leabound.core.gene.RewardItemGen;
import com.maanoo.leabound.core.gene.SubGenerator;
import com.maanoo.leabound.core.item.Item;
import com.maanoo.leabound.core.thing.AndGate;
import com.maanoo.leabound.core.thing.Dispenser;
import com.maanoo.leabound.core.thing.PressurePlate;
import com.maanoo.leabound.core.thing.Switch;
import com.maanoo.leabound.core.util.Direction;
import com.maanoo.leabound.core.util.Location;
import com.maanoo.leabound.core.util.Ra;


public class GenSimpleLogic extends SubGenerator {

	// TODO convert to SubGeneratorTransform

	public GenSimpleLogic() {
		super(a(Concept.Logic, Concept.Medium));
	}

	@Override
	public Object generate(Board board, BoardArea area, float level) {

		final Location p1, p2;
		final Direction rot;

		int dis1 = Math.max(1, 2 - (int) level + Ra.next(2));
		int dis2 = Math.max(1, 2 - (int) level + Ra.next(2));

		if (area.isSquary()) {

			dis1 += 1;
			dis2 += 1;

			if (Ra.bool()) {
				p1 = area.get(Align.bottomLeft).add(dis2, dis1);
				p2 = area.get(Align.topRight).add(-dis2, -dis1);
				rot = Direction.Right;
			} else {
				p1 = area.get(Align.bottomLeft).add(dis2, dis1);
				p2 = area.get(Align.topRight).add(-dis1, -dis2);
				rot = Direction.Left;
			}

		} else if (area.isHorizontal()) {

			if (Ra.bool()) {
				p1 = area.get(Align.bottomLeft).add(dis1, 1);
				p2 = area.get(Align.bottomRight).add(-dis2, 1);
				rot = Direction.Up;
			} else {
				p1 = area.get(Align.topLeft).add(dis1, -1);
				p2 = area.get(Align.topRight).add(-dis2, -1);
				rot = Direction.Down;
			}

		} else {

			if (Ra.bool()) {
				p1 = area.get(Align.bottomLeft).add(1, dis1);
				p2 = area.get(Align.topLeft).add(1, -dis2);
				rot = Direction.Right;
			} else {
				p1 = area.get(Align.bottomRight).add(-1, dis1);
				p2 = area.get(Align.topRight).add(-1, -dis2);
				rot = Direction.Left;
			}

		}

		return generateThings(board, p1, p2, rot);
	}

	protected Object generateThings(Board board, final Location p1, final Location p2, final Direction rot) {

		final Location p3 = between(p1, p2);

		final Location p4 = p3.cpy().mulAdd(rot.vector, 2);

		final Item pickup = RewardItemGen.get(.1f, 1)[0];

		if (Ra.bool()) {
			board.addThing(new Switch(p1));
			board.addThing(new Switch(p2));
		} else if (Ra.bool()) {
			board.addThing(new PressurePlate(p1));
			board.addThing(new Switch(p2));
		} else {
			board.addThing(new Switch(p1));
			board.addThing(new PressurePlate(p2));
		}
		board.addThing(new AndGate(p3, rot));
		board.addThing(new Dispenser(p4, rot, pickup));

		wire(board, p1, p3);
		wire(board, p2, p3);
		wire(board, p3, p4);

		return null;

	}

}
