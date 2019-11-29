package com.maanoo.leabound.face.widget;

import com.badlogic.gdx.scenes.scene2d.Group;

import com.maanoo.leabound.core.util.Mod;


public abstract class ViewMod extends Group {

	private final Mod mod;
	private int lastmod;

	public ViewMod(Mod mod) {
		this.mod = mod;

		lastmod = 0;
	}

	protected abstract void refresh();

	@Override
	public final void act(float delta) {
		super.act(delta);

		if (lastmod != mod.getMod()) {
			lastmod = mod.getMod();

			refresh();
		}
	}

}
