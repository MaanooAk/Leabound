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


public class ThingImage extends Group implements EventListener {

	protected final Skin skin;

	private final Thing thing;
	private int mod;

	protected final Image image;

	public ThingImage(Thing thing, Skin skin, int gsize, int slide) {
		this.thing = thing;
		this.skin = skin;

		mod = thing.getMod();

		image = new Image(skin.getDrawable(thing.getDrawable()));
		image.setRotation(-thing.getRotation().degrees);
		image.setSize(gsize, gsize);
		image.setPosition(
				gsize * thing.getLocation().x,
				gsize * thing.getLocation().y);

		image.addListener(this);

		addActor(image);

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
	public void act(float delta) {
		super.act(delta);

		if (thing.getMod() != mod) {
			mod = thing.getMod();

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
	}

	@Override
	public boolean handle(Event event) {

		if (event instanceof InputEvent) {
			final InputEvent input = (InputEvent) event;

			if (input.getType() == Type.touchDown) {
				System.out.println(thing.toString());
			}
		}

		return false;
	}

}
