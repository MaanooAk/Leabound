package com.maanoo.leabound.face;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.maanoo.leabound.LeaboundGame;


public abstract class StageScreen implements Screen {

	public final LeaboundGame game;

	private final Stage stage;

	private final Actor fading;

//	public GLProfiler prof;

	public StageScreen(LeaboundGame game) {
		this.game = game;

		stage = new Stage(new ScreenViewport());

		fading = new Image(game.skin, "solid-black");
		fading.setTouchable(Touchable.disabled);
		fading.getColor().a = 0;

//		prof = new GLProfiler(Gdx.graphics);
//		prof.enable();
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
		stage.getRoot().clearChildren();

		create(width, height);

		fading.setSize(width, height);
		stage.addActor(fading);

		stage.addListener(eventListener);
	}

	public abstract void create(int width, int height);

	public final <T extends Actor> T add(T actor) {
		stage.addActor(actor);
		return actor;
	}

	public final void addAll(Actor... actors) {
		for (final Actor i : actors)
			stage.addActor(i);
	}

	public abstract void changed(ChangeEvent event, Actor actor);

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();

		preRender(delta);
		stage.draw();
		postRender(delta);
	}

	public void renderOnTop(float delta) {
		stage.act();
		stage.draw();
	}

	public void preRender(float delta) {
	}

	public void postRender(float delta) {
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

	// ===

	protected void moveToFront(Actor actor) {

		final Group parent = actor.getParent();
		if (parent == null) return;

		actor.remove();
		parent.addActor(actor);

		while (actor.hasActions())
			actor.act(1000);
	}

	public void fadeScreen(final StageScreen screen, float delay) {

		moveToFront(fading);

		screen.fadeIn();

		fading.addAction(Actions.sequence( //
				Actions.delay(delay), //
				Actions.fadeIn(0.3f, Interpolation.sineOut), //
				Actions.run(new Runnable() {

					@Override
					public void run() {
						game.setScreen(screen);
					}

				})));
	}

	public StageScreen fadeIn() {

		moveToFront(fading);

		fading.addAction(Actions.sequence( //
				Actions.alpha(1), //
				Actions.fadeOut(0.3f, Interpolation.sineIn) //
		));

		return this;
	}

}
