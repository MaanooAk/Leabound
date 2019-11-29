package com.maanoo.leabound.face.widget;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

import com.maanoo.leabound.core.thing.PickUp;
import com.maanoo.leabound.core.thing.Thing;


public class ViewThing extends Group implements EventListener {

	// TODO extend ViewMod
	// TODO clean up

	protected final Skin skin;

	private final Thing thing;
	private int mod;

	protected final Image image;

	ViewThing(Skin skin, int gsize, Thing thing, int slide) {
		this.thing = thing;
		this.skin = skin;

		mod = thing.getMod();

		image = new Image(skin.getDrawable(thing.getDrawable()));
		image.setRotation(-thing.getRotation().degrees);
		image.setSize(gsize, gsize);
		addActor(image);

		image.addListener(this); // TODO remove

		setSize(gsize, gsize);
		setPosition(
				gsize * thing.getLocation().x,
				gsize * thing.getLocation().y);

		if (slide != 0) slideIn(slide);
	}

	public void slideIn(int align) {

		image.setOrigin(align);

		image.setScale(0);
		image.addAction(Actions.scaleTo(1, 1, 0.2f, Interpolation.circleOut));
	}

	public Thing getThing() {
		return thing;
	}

	@Override
	public final void act(float delta) {
		super.act(delta);

		if (thing.getMod() != mod) {
			mod = thing.getMod();

			refresh();

		}
	}

	protected void refresh() {

		if (thing.isDestroyed()) { // TODO get reason

			image.setOrigin(Align.center);

			if (thing instanceof PickUp && ((PickUp) thing).getItem().isInstant()) {
				// instant item

				image.addAction(Actions.sequence(
						Actions.scaleTo(1.5f, 1.5f, 0.2f, Interpolation.circleOut),
						Actions.visible(false)));
				image.addAction(Actions.fadeOut(0.2f, Interpolation.circleOut));

			} else {

				image.addAction(Actions.sequence(
						Actions.scaleTo(0, 0, 0.2f, Interpolation.circleIn),
						Actions.visible(false)));
			}

		} else {
			image.setDrawable(skin, thing.getDrawable());
		}

	}

	@Override
	public boolean handle(Event event) { // TODO remove

		if (event instanceof InputEvent) {
			final InputEvent input = (InputEvent) event;

			if (input.getType() == Type.touchDown) {
				System.out.println(thing.toString());
			}
		}

		return false;
	}

}
