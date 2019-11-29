package com.maanoo.leabound.core.util;

/**
 * Simple wrapper of an object with its weight.
 * <p>
 * The weight may change over time.
 *
 * @param  <T> the type of entry
 *
 * @author     Akritas Akritidis
 */
public final class WeightEntry<T> implements Weight {

	private final T entry;
	private final float weight;

	/**
	 * Constructs based on the give values
	 *
	 * @param weight the weight of the entry
	 * @param entry  any object
	 */
	public WeightEntry(float weight, T entry) {
		this.weight = weight;
		this.entry = entry;
	}

	/**
	 * Returns the entry.
	 *
	 * @return the entry
	 */
	public T get() {
		return entry;
	}

	@Override
	public float weight() {
		return weight;
	}

}
