package com.maanoo.leabound;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.maanoo.leabound.face.ScreenMenu;

public class LeaboundGame extends Game {

	public Skin skin;

	@Override
	public void create() {

		skin = new Skin(Gdx.files.internal("graphics/skin.base.json"));
		skin.addRegions(new TextureAtlas("graphics/all.atlas"));

		setScreen(new ScreenMenu(this));

	}

}
