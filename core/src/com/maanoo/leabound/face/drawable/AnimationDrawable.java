package com.maanoo.leabound.face.drawable;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;


public class AnimationDrawable extends BaseDrawable {

	private final float duration;
	private final Drawable[] drawables;

	private float time;

	public AnimationDrawable(float duration, Drawable... drawables) {
		super(drawables[0]);
		this.duration = duration;
		this.drawables = drawables;

		time = 0;
	}

	public void moveToStart() {
		time = 0;
	}

	public void moveToEnd() {
		time = duration * drawables.length;
	}

	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {

		final int index = (int) (time / duration);
		drawables[index < drawables.length ? index : drawables.length - 1].draw(batch, x, y, width, height);
	}

	public Action updateAction() {
		return new Update(this);
	}

	// === actions ===

	private static final class Update extends Action {

		private AnimationDrawable animation;

		public Update(AnimationDrawable animation) {
			this.animation = animation;
		}

		@Override
		public boolean act(float delta) {
			animation.time += delta;
			return false;
		}

	}

}
