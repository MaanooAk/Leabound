package com.maanoo.leabound.core.gene;

public enum Concept {

	Guide("guide"),

	Logic("logic"),
	Pass("pass"),
	Maze("maze"),
	Fake("fake"),

	AdvLogic("adv-logic"),

	Dummy("dummy"),
	Reward("reward"),

	//

	Small("small"),
	Medium("medium"),
	Big("big"),

	//

	Single(":single", true),

	;

	// === class ===

	public final String name;
	public final boolean mod;

	private Concept(String name) {
		this(name, false);
	}

	private Concept(String name, boolean mod) {
		this.name = name;
		this.mod = mod;
	}

	// === object ===

	@Override
	public String toString() {
		return name;
	}

}
