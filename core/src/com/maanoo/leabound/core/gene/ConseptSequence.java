package com.maanoo.leabound.core.gene;

import static com.maanoo.leabound.core.gene.Consept.*;

import java.util.Arrays;

import com.badlogic.gdx.utils.Array;


public class ConseptSequence {

	private final Array<Consept[]> list;

	public ConseptSequence() {
		list = new Array<Consept[]>();

		add(5, Guide);

		add(1, Logic, Big);
		add(1, Maze, Big);
		add(1, Pass, Big);
		add(1, Logic, Medium, Single);

		add(1, Reward, Big);
	}

	private void generate() {

		add(4);
		add(1, Reward, Big);
	}

	private void add(int count, Consept... consepts) {
		for (int i = 0; i < count; i++) {
			list.add(consepts);
		}
	}

	public Consept[] get(int index) {
		if (index >= list.size) generate();
		return list.get(index);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();

		for (int i = 0; i < list.size; i++) {
			sb.append(i + 1).append(" ").append(Arrays.toString(list.get(i))).append("\n");
		}
		return sb.toString();
	}

}
