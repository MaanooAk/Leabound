package com.maanoo.leabound.core.gene;

import static com.maanoo.leabound.core.gene.Concept.*;

import java.util.Arrays;

import com.badlogic.gdx.utils.Array;

import com.maanoo.leabound.core.board.pre.Guides;


public class ConceptSequence {

	public static final class Entry {

		/**
		 * An ordered collection of concepts. Duplicates values are allowed.
		 */
		public final Concept[] concepts;

		/**
		 * A message to show with the board. In case where one of the concepts is
		 * {@link Concept#Guide}, the message if used as the name of guide and the
		 * message given by the guide board will be used.
		 */
		public final String message;

		/**
		 * @param concepts see {@link #concepts}
		 * @param message  see {@link #message}
		 */
		public Entry(Concept[] concepts, String message) {
			this.concepts = concepts;
			this.message = message;
		}

		public final boolean hasConcept(Concept check) {

			for (final Concept i : concepts) {
				if (i.equals(check)) return true;
			}
			return false;
		}

	}

	private final Array<Entry> list;

	public ConceptSequence() {
		list = new Array<Entry>();
	}

	protected void generate(int requested) {

		if (requested == 0) {

			add(Guides.Part);
			add(Guides.Chest);
			add(Guides.PressurePlate);
			add(Guides.Switches);
			add(Guides.Pass);

			add(1, Logic, Big);
			add(1, Maze, Big);
			add(1, Pass, Big);
			add(Guides.NotGate);
			add(1, Logic, Medium, Single);

			add(Guides.Fake);
			add(1);
			add(1);
			add(1);
			add(1, Reward, Big);

			add(1);
			add(1);
			add(1);
			add(1);
			add(1, Reward, Big);

		} else {

			add(4);
			add(1, Reward, Big);

		}
	}

	protected final void add(int count, Concept... concepts) {
		add(count, null, concepts);
	}

	protected final void add(int count, String message, Concept... concepts) {
		for (int i = 0; i < count; i++) {
			list.add(new Entry(concepts, message));
		}
	}

	protected final void add(Guides guide) {
		add(1, guide.name(), Concept.Guide);
	}

	public final Entry get(int index) {
		if (index >= list.size) generate(index);
		return list.get(index);
	}

	@Override
	public final String toString() {
		final StringBuilder sb = new StringBuilder();

		for (int i = 0; i < list.size; i++) {
			final Entry entry = list.get(i);
			sb.append(i + 1).append(" ").append(Arrays.toString(entry.concepts));
			if (entry.message != null) sb.append(" '").append(entry.message).append("'");
			sb.append("\n");
		}
		return sb.toString();
	}

}
