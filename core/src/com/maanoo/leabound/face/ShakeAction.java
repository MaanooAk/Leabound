package com.maanoo.leabound.face;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.maanoo.leabound.core.util.Ra;


public class ShakeAction extends TemporalAction {

	private final float dx, dy;
	private float x, y;

	public ShakeAction(float d, float duration) {
		this(d, d, duration);
	}

	public ShakeAction(float dx, float dy, float duration) {
		super(duration);
		this.dx = dx;
		this.dy = dy;
	}

	@Override
	protected void begin() {
		x = getTarget().getX();
		y = getTarget().getY();
	}

	@Override
	protected void update(float percent) {
		final float mul = Interpolation.sineIn.apply(1 - percent);
		getTarget().setPosition(x + Ra.next(-dx, dx) * mul, y + Ra.next(-dy, dy) * mul);
	}

	@Override
	protected void end() {
		getTarget().setPosition(x, y);
	}

}
