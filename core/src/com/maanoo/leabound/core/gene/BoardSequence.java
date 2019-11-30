package com.maanoo.leabound.core.gene;

import com.maanoo.leabound.core.Player;
import com.maanoo.leabound.core.board.Board;


public final class BoardSequence {

	private final ConceptSequence ConceptSequence;
	private final Generator generator;

	private int index;

	public BoardSequence(ConceptSequence ConceptSequence, Generator generator) {
		this.ConceptSequence = ConceptSequence;
		this.generator = generator;

		index = -1;
	}

	public Board next(Player player) {

		index += 1;
		final Board board = generator.generate(player, ConceptSequence.get(index));

		return board;
	}

	public int getIndex() {
		return index;
	}

}
