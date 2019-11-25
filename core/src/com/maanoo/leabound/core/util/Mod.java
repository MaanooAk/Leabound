package com.maanoo.leabound.core.util;

/**
 * Keep track of the number of modifications of the state of the object.
 *
 * @author Akritas Akritidis
 */
public class Mod {

	private int mod;

	/**
	 * Declare that the state has been modified.
	 */
	protected final void mod() {
		mod++;
	}

	/**
	 * Returns the number of modifications that have been declared.
	 *
	 * @return the number of modifications
	 */
	public final int getMod() {
		return mod;
	}

}
