package com.maanoo.leabound.core;

import com.maanoo.leabound.core.util.Mod;


/**
 * Player life and max life.
 * <p>
 * Max life is an integer and life is a float.
 * <p>
 * Offers modifications count.
 *
 * @author Akritas Akritidis
 */
public final class Life extends Mod {

	private float life;
	private int max;

	/**
	 * Constructs a player life with the given life and max life.
	 *
	 * @param life the life
	 * @param max  the max life
	 */
	public Life(float life, int max) {
		this.life = life;
		this.max = max;
	}

	// === change ===
	/**
	 * Add to life and the max life.
	 *
	 * @param d amount of life
	 */
	public void add(int d) {
		life += d;
		max += d;
		mod();
	}

	/**
	 * Add life bounded by the max health.
	 *
	 * @param delta amount of health.
	 */
	public void heal(float delta) {
		life += delta;
		if (life > max) life = max;
		mod();
	}

	/**
	 * Remove life bounded by zero.
	 *
	 * @param delta amount of damage
	 */
	public void damage(float delta) {
		life -= delta;
		if (life < 0) life = 0;
		mod();
	}

	// === access ===

	/**
	 * Returns the current life
	 *
	 * @return the life
	 */
	public float get() {
		return life;
	}

	/**
	 * If the life is zero
	 *
	 * @return if the life is zero
	 */
	public boolean isDead() {
		return life <= 0;
	}

	/**
	 * Returns the current max life
	 *
	 * @return the max life
	 */
	public int getMax() {
		return max;
	}

}
