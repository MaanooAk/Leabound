package com.maanoo.leabound.face.widget;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

import com.maanoo.leabound.core.board.Bound;


public class ViewBound extends ViewInWorld {

	// TODO make graphic for trail

	private final Image vBorder;
	private final Image vTrail;

	private Bound bound;

	public ViewBound(Skin skin, int gsize) {
		super(gsize);

		vBorder = new Image(skin, "border");
		vBorder.getColor().a = 1f;
		addActor(vBorder);

		vTrail = new Image(skin, "solid-black");
		vTrail.getColor().a = 0.5f;
		addActor(vTrail);

	}

	public void set(Bound bound) {
		this.bound = bound;
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		vBorder.setSize(
				bound.getWidth() * gsize,
				bound.getHeight() * gsize);
		vTrail.setSize(
				bound.getActualWidth(Interpolation.linear) * gsize,
				bound.getActualHeight(Interpolation.linear) * gsize);

		vBorder.setPosition(cx, cy, Align.center);
		vTrail.setPosition(cx, cy, Align.center);

	}

}
