package com.maanoo.leabound.core.gene;

public enum Consept {

	Guide("guide"),

	Logic("logic"),
	Pass("pass"),
	Maze("maze"),

	Dummy("dummy"),
	Reward("reward"),

	//

	Single(":single", true),

	Small(":small", true),
	Medium(":medium", true),
	Big(":big", true),

	;

	// === class ===

	public final String name;
	public final boolean mod;

	private Consept(String name) {
		this(name, false);
	}

	private Consept(String name, boolean mod) {
		this.name = name;
		this.mod = mod;
	}

	// === object ===

	@Override
	public String toString() {
		return name;
	}

}
