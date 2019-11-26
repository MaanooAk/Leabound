package com.maanoo.leabound.face.widget;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.maanoo.leabound.core.thing.Display;
import com.maanoo.leabound.core.thing.Thing;


public final class ViewsThing {

	private ViewsThing() {
	}

	public static final ViewThing create(Skin skin, int gsize, Thing thing, int slide) {

		if (thing instanceof Display) {
			return new ViewThingDisplay(skin, gsize, (Display) thing, slide);
		}

		return new ViewThing(skin, gsize, thing, slide);
	}

}
