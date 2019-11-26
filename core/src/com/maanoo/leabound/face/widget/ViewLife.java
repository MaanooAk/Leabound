package com.maanoo.leabound.face.widget;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.maanoo.leabound.core.Player;


public class ViewLife extends ViewMod {

	private final Skin skin;

	private final Player player;

	private final Image vBack;

	public ViewLife(Skin skin, Player player) {
		super(player.life);
		this.skin = skin;
		this.player = player;

		vBack = new Image(skin, "solid-black");
		vBack.getColor().a = 0.75f;
		vBack.setSize(0, 30);
		vBack.setOrigin(Align.top);

		refresh();
	}

	private final Array<Image> images1 = new Array<Image>();
	private final Array<Image> images2 = new Array<Image>();

	@Override
	protected void refresh() {
		clearChildren();

		setHeight(30);

		addActor(vBack);

		Image image = null;

		for (int i = 0; i < player.life.getMax(); i++) {

			if (i >= images1.size) {
				images1.add(new Image(skin, "solid-red"));
				images2.add(new Image(skin, "solid-lred"));

				hover(images1.peek());
				hover(images2.peek());
			}

			float height = player.life.get() - i;
			if (height > 1) height = 1;
			else if (height < 0) height = 0;

			image = images1.get(i);
			image.setSize(12, 12);
			image.setPosition(-15 - i * 20, 15 - 6, Align.bottomRight);
			addActor(image);

			if (height != 1 && height != 0) hover(image);

			image = images2.get(i);
			image.setSize(12, 12 * height);
			image.setPosition(-15 - i * 20, 15 - 6, Align.bottomRight);
			addActor(image);

			if (height != 1 && height != 0) hover(image);

		}
		final float w = image == null ? -15 : image.getX(Align.left) - 15;

		setWidth(w);

		vBack.addAction(Actions.sizeTo(w, getHeight(), 0.3f, Interpolation.circleOut));

	}

	private void hover(Image active) {

		addActor(active);
		active.setOrigin(6, 6);
		active.clearActions();

		active.addAction(Actions.sequence(

				Actions.scaleTo(1.5f, 1.5f, .2f, Interpolation.circleOut),
				Actions.delay(.5f),
				Actions.scaleTo(1.0f, 1.0f, .3f, Interpolation.circleOut)

		));

	}

}
