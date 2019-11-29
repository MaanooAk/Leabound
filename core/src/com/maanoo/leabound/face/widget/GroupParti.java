package com.maanoo.leabound.face.widget;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

import com.maanoo.leabound.core.util.Ra;


public class GroupParti extends Group {

	private final int w;
	private final int h;

	private float duration = 2f;

	public GroupParti(Skin skin, int w, int h, int gsize) {
		this.w = w;
		this.h = h;

		for (int i = 0; i < 20; i++) {

			final ViewParti v = new ViewParti(skin, gsize, this);
			v.addAction(Actions.delay(Ra.next(duration)));
			addActor(v);

		}

		setSize(w, h);
	}

	public void re(ViewParti v) {

		v.setPosition(Ra.next(w), Ra.next(h), Align.center);

		v.addAction(Actions.scaleTo(0, 0));
		v.addAction(Actions.alpha(.15f));

		v.addAction(Actions.scaleTo(4, 4, duration));
		v.addAction(Actions.alpha(.0f, duration));

	}

	private static class ViewParti extends Image {

		private GroupParti group;

		public ViewParti(Skin skin, int gsize, GroupParti group) {
			super(skin, "solid-lgreen");
			this.group = group;

			setSize(gsize, gsize);
			setOrigin(Align.center);

			getColor().a = 0;
		}

		@Override
		public void act(float delta) {
			super.act(delta);

			if (!hasActions()) {
				group.re(this);
			}
		}

	}

}
