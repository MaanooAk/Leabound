package com.maanoo.leabound.face.widget;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import com.maanoo.leabound.core.Player;


public class ViewMessage extends Group {

	// TODO clean up

	private final Player player;

	private final Image back;
	private final Image fore;
	private final Label label;

	public ViewMessage(Skin skin, Player player) {
		this.player = player;

		setHeight(24 + 6);

		back = new Image(skin, "solid-black");
		back.getColor().a = 0.7f;
		fore = new Image(skin, "solid-white");
		fore.getColor().a = 0f;

		label = new Label("", skin);

		addActor(back);
		addActor(label);
		addActor(fore);
	}

	private float posX;
	private float posY;
	private int posAlignment;

	public void setFixedPosition(float x, float y, int alignment) {
		posX = x;
		posY = y;
		posAlignment = alignment;

		setPosition(posX, posY, posAlignment);
	}

	public void message(String text) {

		final float durationMin = .5f;
		float durationMax = 3.0f;

		if (text.charAt(0) == '!') {
			text = text.substring(1);
			durationMax = 10;
		} else if (text.charAt(0) == '*') {
			text = text.substring(1);
			durationMax = 100;
		}

		label.setText(text);
		label.layout();

		setWidth(label.getPrefWidth() + 40);
		back.setSize(getWidth(), getHeight());
		fore.setSize(getWidth(), getHeight());

		label.setPosition(20 + 3, getHeight() / 2);

		setPosition(posX, posY - 14, posAlignment);

		addAction(Actions.sequence(

				Actions.moveBy(0, 20, 0.3f, Interpolation.circleOut),
				Actions.moveBy(0, -6, 0.3f, Interpolation.circleOut),
				Actions.delay(durationMin)

		));
		addAction(Actions.sequence(

				Actions.delay(durationMax),
				Actions.moveBy(0, -getHeight(), 0.3f, Interpolation.circleIn)

		));

		if (durationMax > 5) {
			// flicker
			fore.addAction(Actions.sequence(

					Actions.alpha(1),
					Actions.alpha(.0f, 0.2f, Interpolation.circleIn),
					Actions.alpha(.8f, 0.08f, Interpolation.circleIn),
					Actions.alpha(.0f, 0.08f, Interpolation.circleIn),

					Actions.forever(Actions.sequence(

							Actions.delay(3),
							Actions.alpha(.5f, 0.1f, Interpolation.circleIn),
							Actions.alpha(.0f, 0.05f, Interpolation.circleIn)

					))));

		} else {
			// reset
			fore.clearActions();
			fore.getColor().a = 0f;
		}

	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (getActions().size <= 100 && player.messages.size > 0) {
			clearActions();

			message(player.messages.removeIndex(0));
		}
	}

}
