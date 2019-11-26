package com.maanoo.leabound.face.widget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class ViewShadow extends Image {

	// TODO make graphic
	// TODO move to ViewInWorld

	private final int gsize;
	private final int cx;
	private final int cy;

	public ViewShadow(Skin skin, int gsize) {
		super(skin, "solid-white");
		this.gsize = gsize;

		getColor().a = 0f;
		setSize(gsize, gsize);

		cx = Gdx.graphics.getWidth() / 2;
		cy = Gdx.graphics.getHeight() / 2;
	}

	// === animations ===

	public void show(int x, int y) {

		addAction(Actions.moveTo(cx + x * gsize, cy + y * gsize));

		addAction(Actions.alpha(0.1f));
		addAction(Actions.fadeOut(0.4f));
	}

}
