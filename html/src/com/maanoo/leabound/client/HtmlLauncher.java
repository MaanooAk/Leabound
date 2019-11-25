package com.maanoo.leabound.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.maanoo.leabound.LeaboundGame;

public class HtmlLauncher extends GwtApplication {

	@Override
	public GwtApplicationConfiguration getConfig() {
		final GwtApplicationConfiguration config = new GwtApplicationConfiguration(800, 600);

//		config.antialiasing = true;

		return config;
	}

	@Override
	public ApplicationListener createApplicationListener() {
		return new LeaboundGame();
	}
}