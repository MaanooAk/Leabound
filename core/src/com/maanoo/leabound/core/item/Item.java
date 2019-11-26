package com.maanoo.leabound.core.item;

import java.util.HashMap;
import java.util.NoSuchElementException;

import com.maanoo.leabound.core.Player;
import com.maanoo.leabound.core.board.Board;


/**
 * Immutable enum-like items.
 *
 * @author Akritas Akritidis
 */
public class Item {

	// === static ===

	private static final HashMap<String, Item> map = new HashMap<String, Item>();

	public static Item get(String name) {

		final Item item = map.get(name);
		if (item == null) throw new NoSuchElementException(name);

		return item;
	}

	// === class ===

	private final String name;
	private final Type type;

	/**
	 * Life expectancy of the item.
	 *
	 * @author Akritas Akritidis
	 */
	public enum Type {
		/** Persistent item */
		Global,
		/** Destroyed on leap */
		Local,
		/** Destroyed on peak up */
		Instant
	}

	private Item(String name, Type type) {
		this.name = name;
		this.type = type;

		map.put(name, this);
	}

	/**
	 * @return If the item was consumed.
	 */
	public boolean activate(Board board, Player player) {
		return false;
	}

	// === access ===

	public final String getName() {
		return name;
	}

	public final boolean isGlobal() {
		return type == Type.Global;
	}

	public final boolean isLocal() {
		return type == Type.Local;
	}

	public final boolean isInstant() {
		return type == Type.Instant;
	}

	public String getDrawable() {
		return getName();
	}

	// === object ===

	@Override
	public final int hashCode() {
		return name.hashCode();
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;

		final Item other = (Item) obj;
		return name.equals(other.name);
	}

	@Override
	public final String toString() {
		return name;
	}

}
