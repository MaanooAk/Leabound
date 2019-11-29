package com.maanoo.leabound.core.util;

import com.badlogic.gdx.utils.Align;


/**
 * The four 2d directions with auxiliary information.
 *
 * @author Akritas Akritidis
 */
public enum Direction {

	/** Right - End */
	Right(1, 0, Align.left),

	/** Down - Bottom */
	Down(0, -1, Align.top),

	/** Left - Start */
	Left(-1, 0, Align.right),

	/** Up - Top */
	Up(0, 1, Align.bottom),

	;

	/**
	 * The default direction for the rotating sprites.
	 */
	public static final Direction Default = Right; // must have ordinal of 0

	/**
	 * Returns a random direction.
	 * 
	 * @return one of the four directions
	 */
	public static Direction random() {
		return Ra.random(values());
	}

	/** Unit d of x. */
	public final int dx;
	/** Unit d of y. */
	public final int dy;
	/** Unit d of x and y. */
	public final Location vector;

	/**
	 * The opposite {@link Align}
	 */
	public final int origin;

	/**
	 * Distance of degrees from the {@link #Default} direction.
	 */
	public final int degrees;

	private Direction(int dx, int dy, int origin) {
		this.dx = dx;
		this.dy = dy;
		this.origin = origin;

		degrees = ordinal() * 90;
		vector = new Location(dx, dy);
	}

}
