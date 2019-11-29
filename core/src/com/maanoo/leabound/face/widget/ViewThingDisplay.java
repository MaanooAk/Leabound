package com.maanoo.leabound.face.widget;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.maanoo.leabound.LeaboundGame;
import com.maanoo.leabound.core.Statistics;
import com.maanoo.leabound.core.thing.Display;
import com.maanoo.leabound.face.ScreenGame;
import com.maanoo.leabound.face.StageScreen;

public class ViewThingDisplay extends ViewThing {

	private final Display display;

	private final Label label;

	private final Runnable command;

	ViewThingDisplay(Skin skin, int gsize, Display display, int slide) {
		super(skin, gsize, display, slide);
		this.display = display;

		final String text = display.getText();

		label = new Label(text, skin);
		label.setAlignment(Align.center);
		label.layout();
		label.setPosition(getWidth() / 2, getHeight(), Align.top);
		addActor(label);

		label.addAction(floating(-10, 1, Interpolation.pow4));

		if (text.startsWith("$Reset")) {
			command = new Runnable() {

				@Override
				public void run() {
					if (!label.isVisible()) return;

					Statistics.resetAll();

					// TODO fix
					((StageScreen) LeaboundGame.I.getScreen()).fadeScreen(new ScreenGame(LeaboundGame.I), 0);
				}
			};

		} else command = null;

		updaetLabel();
	}

	private void updaetLabel() {
		label.setVisible(display.isTextVisible());

		if (display.isTextVisible() && command != null) {
			addAction(Actions.sequence(
					Actions.delay(2), // delay before command activates
					Actions.run(command)));
		}
	}

	@Override
	protected void refresh() {
		super.refresh();

		updaetLabel();
	}

	// === animation ===

	private static float floatOffset = 1;

	private static Action floating(float amount, float duration, Interpolation interpolation) {
		amount *= floatOffset;
		floatOffset = -floatOffset;

		return Actions.sequence( //
				Actions.moveBy(0, -amount / 2, duration / 2, interpolation),
				Actions.forever(Actions.sequence(//
						Actions.moveBy(0, amount, duration, interpolation),
						Actions.moveBy(0, -amount, duration, interpolation))));
	}

}
