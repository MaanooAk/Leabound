package com.maanoo.leabound.core.item;

import java.util.Iterator;

import com.badlogic.gdx.utils.ObjectIntMap;
import com.badlogic.gdx.utils.ObjectIntMap.Entry;

import com.maanoo.leabound.core.util.Mod;


/**
 * Collection of {@link Item}.
 * <p>
 * On iteration item counts may be zero.
 *
 * @author Akritas Akritidis
 */
public class ItemBundle extends Mod implements Iterable<Item> {

	// TODO replace the ObjectIntMap

	private final ObjectIntMap<Item> map;

	public ItemBundle() {
		map = new ObjectIntMap<Item>();
	}

	// === change ===

	public void add(Item item, int count) {

		map.getAndIncrement(item, 0, count);
		mod();
	}

	public boolean deduct(Item item, int count) {
		if (get(item) < count) return false;

		add(item, -count);
		return true;
	}

	public void removeLocal() {

		// TODO improve
		for (final Iterator<Entry<Item>> iter = map.iterator(); iter.hasNext();) {
			final ObjectIntMap.Entry<Item> i = iter.next();

			if (i.key.isLocal()) iter.remove();
		}
		mod();
	}

	// === access ===

	public int get(Item i) {
		return map.get(i, 0);
	}

	public boolean has(Item i) {
		return map.get(i, 0) > 0;
	}

	@Override
	public Iterator<Item> iterator() {
		return map.keys().iterator();
	}

}
