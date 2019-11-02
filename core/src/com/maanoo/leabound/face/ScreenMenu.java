package com.maanoo.leabound.face;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Align;
import com.maanoo.leabound.LeaboundGame;

public class ScreenMenu extends StageScreen {

	public ScreenMenu(LeaboundGame game) {
		super(game);
	}

	@Override
	public void create(int width, int height) {

		final Image logo = new Image(game.skin.getDrawable("logo-30"));
		logo.setSize(30 * 4, 30 * 4);
		logo.setPosition(width / 2, height, Align.top);

		getStage().addActor(logo);
	}

	@Override
	public void changed(ChangeEvent event, Actor actor) {

	}

}
