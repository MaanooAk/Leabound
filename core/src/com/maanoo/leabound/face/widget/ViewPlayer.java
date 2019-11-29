package com.maanoo.leabound.face.widget;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

import com.maanoo.leabound.core.Player;
import com.maanoo.leabound.face.drawable.AnimationDrawable;


public class ViewPlayer extends ViewInWorld {

	private final AnimationDrawable anim;

	private Image image;

	public ViewPlayer(Skin skin, int gsize, Player player) {
		super(gsize);

		final String drawable = player.getDrawable();

		anim = new AnimationDrawable(0.08f,
				skin.getDrawable(drawable + ".jumb_2"), // TODO change to underscore
				skin.getDrawable(drawable + ".jumb_1"),
				skin.getDrawable(drawable));
		anim.moveToEnd();
		addAction(anim.updateAction());

		image = new Image(anim);
		image.setSize(gsize, gsize);

		setSize(gsize, gsize);
		addActor(image);
	}

	// === animations ===

	public void leap(float duration) {

		addAction(Actions.sequence( //
				Actions.scaleTo(1.2f, 1.2f, duration, Interpolation.sineOut),
				Actions.scaleTo(1, 1, duration, Interpolation.sineIn) //
		));

		addAction(Actions.moveTo(cx, cy, duration * 2, Interpolation.sine));
	}

	public void bounceBound(int dx, int dy) {

		addAction(Actions.sequence( //
				Actions.moveBy(dx * gsize * 0.1f, dy * gsize * 0.1f, 0.1f, Interpolation.circleOut),
				Actions.moveBy(-dx * gsize * 0.1f, -dy * gsize * 0.1f, 0.1f, Interpolation.circleOut) //
		));
	}

	public void bounceThing(int dx, int dy) {

		addAction(Actions.sequence( //
				Actions.moveBy(dx * gsize * 0.2f, dy * gsize * 0.2f, 0.1f, Interpolation.circleOut),
				Actions.moveBy(-dx * gsize * 0.2f, -dy * gsize * 0.2f, 0.1f, Interpolation.circleOut) //
		));
	}

	public void rotate(int dx, int dy) {
		setOrigin(Align.center);

		final int targetRotation = dy * 90 - 90 + (dx == -1 ? 180 : 0);

		final RotateToAction rotateAction = Actions.rotateTo(targetRotation, 0.1f);
		rotateAction.setUseShortestDirection(true);

		addAction(rotateAction);
	}

	public void move(int dx, int dy) {

		addAction(Actions.moveBy(dx * gsize, dy * gsize, 0.5f, Interpolation.circleOut));
		anim.moveToStart();
	}

	@Override
	public void clearActions() {
		throw new RuntimeException();
	}

}
