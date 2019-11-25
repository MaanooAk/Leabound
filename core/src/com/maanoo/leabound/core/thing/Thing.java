package com.maanoo.leabound.core.thing;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.maanoo.leabound.core.Player;
import com.maanoo.leabound.core.board.Board;
import com.maanoo.leabound.core.util.Direction;
import com.maanoo.leabound.core.util.Mod;


/**
 * Abstract thing of the {@link Board}.
 *
 * @author Akritas Akritidis
 */
public abstract class Thing extends Mod {

	private String name;
	private Vector2 location;
	private Direction rotation;

	private boolean active;
	private boolean destroyed;

	/**
	 * Constructs an abstract thing.
	 *
	 * @param name     the unique type name of the thing
	 * @param location the starting location, nullable
	 * @param rotation the starting rotation, nullable
	 */
	public Thing(String name, Vector2 location, Direction rotation) {
		this.name = name;
		this.location = location == null ? new Vector2() : location;
		this.rotation = rotation == null ? Direction.Default : rotation;

		active = false;
		destroyed = false;
	}

	// === modify ===

	protected void activate() {
		if (active) return;

		active = true;
		mod();
	}

	protected void deactivate() {
		if (!active) return;

		active = false;
		mod();
	}

	protected void destroy() {
		if (destroyed) return;

		destroyed = true;
		mod();

		onDestroy();
	}

	// === events ===

	/**
	 * Called when player enters the same location.
	 *
	 * @param player the player
	 * @return if there was a change
	 */
	public boolean onPlayerEnter(Player player) {
		return false;
	}

	/**
	 * Called when player leaves the same location.
	 *
	 * @param player the player
	 * @return if there was a change
	 */
	public boolean onPlayerLeave(Player player) {
		return false;
	}

	/**
	 * Called when player tries to enter the same location but is blocked.
	 *
	 * @param player the player
	 * @return if there was a change
	 */
	public boolean onPlayerBounce(Player player) {
		return false;
	}

	/**
	 * Called when the object is created.
	 *
	 * @return if there was a change
	 */
	public boolean onCreate() {
		return false;
	}

	/**
	 * Called when another thing changes.
	 *
	 * @param thing the thing that changed
	 * @return if there was a change
	 */
	public boolean onThingActiveChange(Thing thing) {
		return false;
	}

	/**
	 * Called when the thing gets outside of the bound.
	 * <p>
	 * Default: destroy the thing.
	 *
	 * @return if there was a change
	 */
	public boolean onOutsideBound() {
		destroy();
		return true;
	}

	/**
	 * Called when the object is destroyed.
	 *
	 * @return if there was a change
	 */
	public boolean onDestroy() {
		return false;
	}

	// === access ===

	public abstract String getDrawable();

	public abstract boolean isBlocking();

	public boolean activeFlow(Vector2 location) {
		return getLocation().dst2(location) <= 1.05f; // 0.05 epsilon
	}

	public final String getName() {
		return name;
	}

	public String getCurrentName() {
		return getName();
	}

	public final Vector2 getLocation() {
		return location;
	}

	public final Direction getRotation() {
		return rotation;
	}

	public int getOrigin() {
		return Align.center;
	}

	public final boolean isActive() {
		return active && !destroyed;
	}

	public final boolean isDestroyed() {
		return destroyed;
	}

	@Override
	public String toString() {
		return getName() + "@" + getLocation();
	}

}
