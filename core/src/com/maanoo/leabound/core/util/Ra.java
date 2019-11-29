package com.maanoo.leabound.core.util;

import java.util.Random;

import com.badlogic.gdx.utils.Array;

/**
 * Collection of random number and object selector.
 * <p>
 * Powered by the {@linkplain java.util.Random}.
 *
 * @author Akritas Akritidis
 */
public final class Ra {

	private Ra() {
	}

	private static final Random ra = new Random();

	public static int next(int bound) {
		return ra.nextInt(bound);
	}

	public static int next(int min, int max) {
		return min + ra.nextInt(max - min + 1);
	}

	public static float next(float bound) {
		return ra.nextFloat() * bound;
	}

	public static float next(float min, float bound) {
		return min + ra.nextFloat() * (bound - min);
	}

	public static boolean chance(float chance) {
		return ra.nextFloat() < chance;
	}

	public static boolean bool() {
		return ra.nextBoolean();
	}

	public static <T> T random(T... array) {
		return array[next(array.length)];
	}

	public static <T> T random(Array<T> array) {
		return array.get(next(array.size));
	}

	public static <T> void shuffle(T[] array) {

		for (int i = 0; i < array.length; i++) {
			final int target = i + Ra.next(array.length - i);

			final T tmp = array[i];
			array[i] = array[target];
			array[target] = tmp;
		}
	}

	public static <T extends Weight> T randomWeighted(Array<T> array) {

		float unit = 0;
		for (final T i : array) unit += i.weight();

		float target = next(unit);
		for (final T i : array) {
			target -= i.weight();

			if (target <= 0) return i;
		}

		return array.get(array.size - 1); // for float errors
	}

}
