package com.maanoo.leabound.face.widget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;


public abstract class ViewInWorld extends Group {

	protected final int gsize;
	protected final int cx;
	protected final int cy;

	public ViewInWorld(int gsize) {
		this.gsize = gsize;

		cx = Gdx.graphics.getWidth() / 2;
		cy = Gdx.graphics.getHeight() / 2;
	}

}
