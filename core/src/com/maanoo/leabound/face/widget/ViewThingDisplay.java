package com.maanoo.leabound.face.widget;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.maanoo.leabound.core.thing.Display;


public class ViewThingDisplay extends ViewThing {

	private final Display display;

	private final Label label;

	ViewThingDisplay(Skin skin, int gsize, Display display, int slide) {
		super(skin, gsize, display, slide);
		this.display = display;

		label = new Label(display.getText(), skin);
		label.setAlignment(Align.center);
		label.layout();
		label.setPosition(getWidth() / 2, getHeight() / 2, Align.center);
		addActor(label);

		label.addAction(floating(-10, 1, Interpolation.pow4));

		updaetLabel();
	}

	private void updaetLabel() {
		label.setVisible(display.isTextVisible());
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
