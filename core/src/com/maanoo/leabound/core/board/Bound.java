package com.maanoo.leabound.core.board;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import com.maanoo.leabound.core.Player;
import com.maanoo.leabound.core.util.Location;


public class Bound {

	private final Location size;
	private final Vector2 stepDuration;
	private final Vector2 progress;

	private float mult = 1;
	private float multLeft = 0;

	public Bound(int w, int h, Player player) {
		this(w, h, duration(player, w), duration(player, h));
	}

	private static float duration(Player player, float size) {
		if (player.boardIndex == 0) return 0;
		return 50 / size;
	}

	private Bound(int w, int h, float wStepDuration, float hStepDuration) {

		size = new Location(w, h);
		stepDuration = new Vector2(wStepDuration * 1.1f, hStepDuration * 1.1f);

		progress = new Vector2();
	}

	public void setMult(float mult, float duration) {
		this.mult = mult;
		multLeft = duration;
	}

	public void update(float delta) {

		if (mult != 1) {
			multLeft -= delta;
			if (multLeft <= 0) mult = 1;
		}

		progress.add(delta * mult, delta * mult);

		if (stepDuration.x > 0 && progress.x >= stepDuration.x) {
			progress.x -= stepDuration.x;
			size.x -= 2;
		}
		if (stepDuration.y > 0 && progress.y >= stepDuration.y) {
			progress.y -= stepDuration.y;
			size.y -= 2;
		}

	}

	public boolean contains(Location location) {

		// TODO inline

		final Rectangle rec = Rectangle.tmp;
		rec.setSize(size.x - .1f, size.y - .1f);
		rec.setPosition(-size.x / 2, -size.y / 2);

		return rec.contains(location.x, location.y);
	}

	// === access ===

	public int getWidth() {
		return size.x;
	}

	public int getHeight() {
		return size.y;
	}

	public float getProgressX() {
		return 1 - progress.x / stepDuration.x;
	}

	public float getProgressY() {
		return 1 - progress.y / stepDuration.y;
	}

	public float getActualWidth(Interpolation inter) {
		if (stepDuration.x == 0) return getWidth();
		return size.x + inter.apply(getProgressX()) * 2;
	}

	public float getActualHeight(Interpolation inter) {
		if (stepDuration.y == 0) return getHeight();
		return size.y + inter.apply(getProgressY()) * 2;
	}

	public float getMaxProgress() {
		final float px = getProgressX(), py = getProgressY();
		return px > py ? px : py;
	}

}
