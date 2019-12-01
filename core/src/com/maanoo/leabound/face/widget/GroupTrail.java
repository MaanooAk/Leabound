package com.maanoo.leabound.face.widget;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

import com.maanoo.leabound.core.util.Ra;


public class GroupTrail extends Group {

	private static final float Size = .1f;
	private static final float Duration = 1.7f;

	private final Skin skin;
	private final int gsize;

	public GroupTrail(Skin skin, int gsize) {
		this.skin = skin;
		this.gsize = gsize;

	}

	private ViewParti getReadyParti() {

		for (final Actor i : getChildren()) {
			if (!i.hasActions()) return (ViewParti) i;
		}

		final ViewParti v = new ViewParti(skin, gsize, this);
		addActor(v);

//		System.out.println(getChildren().size);

		return v;
	}

	public void move(ViewPlayer vPlayer, int dx, int dy) {

		final float x = vPlayer.getX(Align.center);
		final float y = vPlayer.getY(Align.center);

		for (int i = 0; i < 3; i++) {

			final ViewParti v = getReadyParti();

			final float dxy = gsize * (1 - Size) / 4;

			v.setPosition(x + Ra.next(-dxy, dxy), y + Ra.next(-dxy, dxy), Align.center);

			v.getColor().a = .8f;
			v.addAction(Actions.fadeOut(Duration * (i + 1), Interpolation.circleIn));

			v.addAction(Actions.moveBy(gsize * -dx * Size, gsize * -dy * Size, .3f, Interpolation.circleOut));

		}

	}

	public void leap(ViewPlayer vPlayer, float duration) {

		for (final Actor i : getChildren()) {
			if (i.hasActions()) {
				i.clearActions();
				i.addAction(Actions.fadeOut(duration / 2));
			}
		}

	}

	private static class ViewParti extends Image {

		public ViewParti(Skin skin, int gsize, GroupTrail group) {
			super(skin, "solid-lgreen");

			setSize(gsize * Size, gsize * Size);
			setOrigin(Align.center);

			getColor().a = 0;
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			// most cases
			if (getColor().a == 0) return;

			super.draw(batch, parentAlpha);
		}

	}
}
