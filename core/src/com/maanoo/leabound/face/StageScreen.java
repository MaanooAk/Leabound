package com.maanoo.leabound.face;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.maanoo.leabound.LeaboundGame;

public abstract class StageScreen implements Screen {

	public final LeaboundGame game;

	private final Stage stage;

	public StageScreen(LeaboundGame game) {
		this.game = game;

		stage = new Stage(new ScreenViewport());
	}

	public void open() {
		game.setScreen(this);
	}

	@Override
	public void dispose() {

		stage.dispose();
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void resume() {
		Gdx.input.setInputProcessor(stage);
	}

	private final EventListener eventListener = new EventListener() {

		@Override
		public boolean handle(Event event) {

			if (event instanceof InputEvent) {
				handleInputEvent((InputEvent) event);
			} else if (event instanceof ChangeEvent) {
				changed((ChangeEvent) event, event.getTarget());
			} else {
				return false;
			}
			return false;
		}
	};

	public final EventListener getChangeListener() {
		return eventListener;
	}

	@Override
	public void resize(int width, int height) {
		stage.clear();
		create(width, height);
		stage.addListener(eventListener);
	}

	public abstract void create(int width, int height);

	public abstract void changed(ChangeEvent event, Actor actor);

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();
	}

	public void renderOnTop(float delta) {
		stage.act();
		stage.draw();
	}

	@Override
	public void pause() {

	}

	@Override
	public void hide() {

	}

	protected void handleInputEvent(InputEvent event) {
	}

	public final Stage getStage() {
		return stage;
	}

}
