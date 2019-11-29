package com.maanoo.leabound.core;

public class Statistics {

	// TODO use

	public static final Statistics Run = new Statistics();
	public static final Statistics Session = new Statistics();
	public static final Statistics SessionBefore = new Statistics();

	public static int runs = 0;
	public static int bestLeaps = 0;
	public static boolean newBest = false;

	public static void resetAll() {
		Run.reset();
		Session.reset();
		SessionBefore.reset();

		runs = 0;
		bestLeaps = 0;
		newBest = false;
	}

	public static void newRun() {
		Run.reset();
	}

	public static void endRun() {
		SessionBefore.set(Session);
		Session.add(Run);

		runs += 1;
		newBest = false;
		if (Run.leaps > bestLeaps) {
			bestLeaps = Run.leaps;
			newBest = true;
		}
	}

	public static String text() {
		final StringBuilder sb = new StringBuilder();

		sb.append("Statistics [fade](run/session)[] / ").append(runs).append(" runs\n");

		sb.append("").append(pers(Run.time / 60)).append(" [fade]").append(pers(Session.time / 60))
				.append("[] minutes\n");
		sb.append("").append(perm(Run.taps / Run.time)).append(" [fade]").append(perm(Session.taps / Session.time))
				.append("[] taps/m\n");
		sb.append("").append(perm(Run.moves / Run.time)).append(" [fade]").append(perm(Session.moves / Session.time))
				.append("[] moves/m\n");
		sb.append("").append(perm(Run.leaps / Run.time)).append(" [fade]").append(perm(Session.leaps / Session.time))
				.append("[] leaps/m\n");
		sb.append("").append(perm(Run.parts / Run.time)).append(" [fade]").append(perm(Session.parts / Session.time))
				.append("[] parts/m\n");

		return sb.toString();
	}

	private static final float pers(float value) {
		return (int) (value * 10) / 10f;
	}

	private static final float perm(float value) {
		return pers(value * 60);
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

	@Override
	public String toString() {
		return "[time=" + time + ", taps=" + taps + ", moves=" + moves + ", leaps=" + leaps + ", parts="
				+ parts + "]";
	}

}
