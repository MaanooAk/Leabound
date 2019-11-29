package com.maanoo.leabound.core.thing;

import com.badlogic.gdx.utils.Align;

import com.maanoo.leabound.core.Player;
import com.maanoo.leabound.core.board.Board;
import com.maanoo.leabound.core.board.BoardTransfom;
import com.maanoo.leabound.core.util.Direction;
import com.maanoo.leabound.core.util.Location;
import com.maanoo.leabound.core.util.Mod;


/**
 * Abstract thing of the {@link Board}.
 *
 * @author Akritas Akritidis
 */
public abstract class Thing extends Mod {

	private final String name;

	private Location location;
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
	public Thing(String name, Location location, Direction rotation) {
		this.name = name;
		this.location = location == null ? new Location() : location;
		this.rotation = rotation;

		active = false;
		destroyed = false;
	}

	public void reset(BoardTransfom tra) {

		rotation = tra.rotation(rotation);

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
	 * @param  player the player
	 * @return        if there was a change
	 */
	public boolean onPlayerEnter(Player player) {
		return false;
	}

	/**
	 * Called when player leaves the same location.
	 *
	 * @param  player the player
	 * @return        if there was a change
	 */
	public boolean onPlayerLeave(Player player) {
		return false;
	}

	/**
	 * Called when player tries to enter the same location but is blocked.
	 *
	 * @param  player the player
	 * @return        if there was a change
	 */
	public boolean onPlayerBounce(Player player) {
		return false;
	}

	/**
	 * Called when the object is created.
	 *
	 * @param  player the player
	 * @return        if there was a change
	 */
	public boolean onCreate(Player player) {
		return false;
	}

	/**
	 * Called when another thing changes.
	 *
	 * @param  thing  the thing that changed
	 * @param  player the player
	 * @return        if there was a change
	 */
	public boolean onThingActiveChange(Thing thing, Player player) {
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

	public boolean activeFlow(Location location) {
		return getLocation().dst(location) <= 1.05f; // 0.05 epsilon
	}

	public final String getName() {
		return name;
	}

	public final Location getLocation() {
		return location;
	}

	public final Direction getRotation() {
		return rotation == null ? Direction.Default : rotation;
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
