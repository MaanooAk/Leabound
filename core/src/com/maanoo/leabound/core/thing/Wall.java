package com.maanoo.leabound.core.thing;

import com.maanoo.leabound.core.util.Location;


public final class Wall extends StaticThing {

	public Wall(Location location) {
		super("Wall", location, "wall", true);
	}

}
