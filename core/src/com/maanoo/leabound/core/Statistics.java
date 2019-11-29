package com.maanoo.leabound.core;

public class Statistics {

	// TODO use

	public static final Statistics Run = new Statistics();
	public static final Statistics Session = new Statistics();
	public static final Statistics SessionBefore = new Statistics();

	public static void newRun() {
		Run.reset();
	}

	public static void endRun() {
		SessionBefore.set(Session);
		Session.add(Run);
	}

	// === class ===

	public float time;

	public int taps;
	public int moves;

	public int leaps;
	public int parts;

	public void reset() {
		time = 0;
		taps = 0;
		moves = 0;
		leaps = 0;
		parts = 0;
	}

	public void add(Statistics other) {
		time += other.time;
		taps += other.taps;
		moves += other.moves;
		leaps += other.leaps;
		parts += other.parts;
	}

	public final void set(Statistics other) {
		reset();
		add(other);
	}

}
