package com.maanoo.leabound.core.util;

/**
 * Simple wrapper of an object with its weight.
 * <p>
 * The weight may change over time.
 *
 * @param <T> the type of entry
 *
 * @author Akritas Akritidis
 */
public final class WeightEntry<T> implements Weight {

	private final T entry;
	private final float weight;

	/**
	 * Constructs based on the give values
	 *
	 * @param entry  any object
	 * @param weight the weight of the entry
	 */
	public WeightEntry(T entry, float weight) {
		this.entry = entry;
		this.weight = weight;
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
