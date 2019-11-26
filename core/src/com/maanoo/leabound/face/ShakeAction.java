package com.maanoo.leabound.face;

import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.maanoo.leabound.core.util.Ra;


public class ShakeAction extends TemporalAction {

	private final float d;
	private float x, y;

	public ShakeAction(float d, float duration) {
		super(duration);
		this.d = d;
	}

	@Override
	protected void begin() {
		x = getTarget().getX();
		y = getTarget().getY();
	}

	@Override
	protected void update(float percent) {
		getTarget().setPosition(x + Ra.next(-d, d), y + Ra.next(-d, d));
	}

	@Override
	protected void end() {
		getTarget().setPosition(x, y);
	}

}
