package com.maanoo.leabound.core.thing;


import com.maanoo.leabound.core.util.Direction;
import com.maanoo.leabound.core.util.Location;


public abstract class StateThing extends Thing {

	private State[] states;
	private int state;

	public StateThing(String name, Location location, Direction rotation, State... states) {
		super(name, location, rotation);
		this.states = states;

		state = 0;
	}

	protected final boolean nextState() {
		assert states.length > 1;

		state = (state + 1) % states.length;
		mod();
		return true;
	}

	// === access ===

	@Override
	public final String getDrawable() {
		return states[state].getDrawable();
	}

	@Override
	public final boolean isBlocking() {
		return states[state].isBlocking();
	}

	public State getState() {
		return states[state];
	}

	public int getStateCount() {
		return states.length;
	}

	// === states ===

	public static interface State {

		public String getDrawable();

		public boolean isBlocking();

	}

	public static class SimpleState implements State {

		private final String drawable;
		private final boolean blocking;

		public SimpleState(String drawable, boolean blocking) {
			this.drawable = drawable;
			this.blocking = blocking;
		}

		@Override
		public String getDrawable() {
			return drawable;
		}

		@Override
		public boolean isBlocking() {
			return blocking;
		}

	}

}
