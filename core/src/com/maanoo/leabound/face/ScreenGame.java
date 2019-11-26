package com.maanoo.leabound.face;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Align;
import com.maanoo.leabound.LeaboundGame;
import com.maanoo.leabound.core.Player;
import com.maanoo.leabound.core.World;
import com.maanoo.leabound.core.board.Board;
import com.maanoo.leabound.core.board.BoardBuilder;
import com.maanoo.leabound.core.board.Boards;
import com.maanoo.leabound.core.board.Bound;
import com.maanoo.leabound.core.gene.Generator;
import com.maanoo.leabound.core.item.Item;
import com.maanoo.leabound.core.thing.Thing;
import com.maanoo.leabound.core.util.Location;
import com.maanoo.leabound.face.widget.ViewBag;
import com.maanoo.leabound.face.widget.ViewBound;
import com.maanoo.leabound.face.widget.ViewLife;
import com.maanoo.leabound.face.widget.ViewMessage;
import com.maanoo.leabound.face.widget.ViewPlayer;
import com.maanoo.leabound.face.widget.ViewShadow;
import com.maanoo.leabound.face.widget.ViewThing;
import com.maanoo.leabound.face.widget.ViewsThing;


public class ScreenGame extends StageScreen {

	private Group group;
	private Group groupThings;

	private int gsize = 40;

	private int dx;
	private int dy;

	private ViewPlayer vPlayer;
	private ViewShadow vShadow;
	private ViewBound vBound;
	private Image damage; // TODO move out

	private World world;

	private ViewBag vBag;
	private ViewLife vLife;
	private ViewMessage vMessage;

	private Label version;

	public ScreenGame(LeaboundGame game) {
		super(game);
	}

	@Override
	public void create(int width, int height) {

		// TODO call this once, one is called twice

		world = new World();
		final Player player = world.getPlayer();

		player.location.set(0, -2);

		dx = 0;
		dy = 1;

		//

		group = new Group();

		{
			final Image background = new Image(game.skin.getDrawable("solid-blue"));
			background.setSize(width, height);
			background.setPosition(0, 0);
			getStage().addActor(background);
		}
		{
			final Image background = new Image(game.skin.getDrawable("solid-green"));
			background.setSize(width, height);
			background.setPosition(0, 0);
//			background.addAction(faiding(3, Interpolation.sine));
			getStage().addActor(background);
		}

		damage = new Image(game.skin, "solid-red");
		damage.getColor().a = 0;
		damage.setSize(width, height);
		getStage().addActor(damage);

		{

			world.setBoard(new BoardBuilder("Menu", 14, 10, ""

					+ "              \n"
					+ "              \n"
					+ "              \n"
					+ "           S  \n"
					+ "           -  \n"
					+ "           -  \n"
					+ "  -----P   N  \n"
					+ "  -        -  \n"
					+ "  1        2  \n"
					+ "              \n"

					, "x", "wall 50", "1", "display Move\nV\nArrows_/_WASD", "2", "display Leap\nV\nSpace"

			).build(player, new Bound(14, 10, player)));

			vBound = new ViewBound(game.skin, gsize);
			group.addActor(vBound);

			groupThings = new Group();
			groupThings.setPosition(width / 2, height / 2, Align.center);
			group.addActor(groupThings);

			vBound.set(world.getBoard().getBound());

			getStage().addActor(group);

		}

		logo = new Label("Leabound", game.skin);
		logo.setAlignment(Align.center);
		logo.setOrigin(Align.center);
		logo.setFontScale(4);
		logo.setPosition(width / 2, height - 80, Align.top);
		group.addActor(logo);

//		final Image logo = new Image(game.skin.getDrawable("logo-30"));
//		logo.setSize(30 * 4, 30 * 4);
//		logo.setPosition(width / 2, height - 10, Align.top);
//		group.addActor(logo);

		version = new Label("Leabound - v 0.1", game.skin);
		version.setPosition(5, 5, Align.bottomLeft);
		getStage().addActor(version);

		vShadow = new ViewShadow(game.skin, gsize);
		getStage().addActor(vShadow);

		vPlayer = new ViewPlayer(game.skin, gsize, world.getPlayer());
		vPlayer.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 - 2 * gsize);
		getStage().addActor(vPlayer);

		vBag = new ViewBag(game.skin, player);
		vBag.setPosition(0, height, Align.topLeft);
		getStage().addActor(vBag);

		vLife = new ViewLife(game.skin, player);
		vLife.setPosition(width, height, Align.topLeft);
		getStage().addActor(vLife);

		vMessage = new ViewMessage(game.skin, player);
		vMessage.setFixedPosition(width, 0, Align.bottomRight);
		getStage().addActor(vMessage);

//		fadeIn(keys1, .4f);
//		fadeIn(keys2, .45f);

//		final Label start = new Label("Leap to start", game.skin);
//		start.setPosition(width * .5f, height * .5f, Align.center);
//		group.addActor(start);

		player.messages.add("*[leap]Leap[] to start[]");
	}

//	private void fadeIn(Actor actor, float delay) {
//
//		actor.getColor().a = 0;
//
//		actor.addAction(Actions.sequence(
//				Actions.delay(delay),
//				Actions.fadeIn(.3f)));
//	}

	@Override
	public void changed(ChangeEvent event, Actor actor) {

	}

	@Override
	protected void handleInputEvent(InputEvent event) {
//		final Board board = world.board;

		if (event.getType() != InputEvent.Type.keyDown)
			return;

		if (world.getPlayer().life.isDead()) return;

		if (event.getKeyCode() == Keys.SPACE || event.getKeyCode() == Keys.ENTER) {
			playerLeap();

		} else if (event.getKeyCode() == Keys.W || event.getKeyCode() == Keys.UP) {
			playerMove(0, 1);

		} else if (event.getKeyCode() == Keys.S || event.getKeyCode() == Keys.DOWN) {
			playerMove(0, -1);

		} else if (event.getKeyCode() == Keys.A || event.getKeyCode() == Keys.LEFT) {
			playerMove(-1, 0);

		} else if (event.getKeyCode() == Keys.D || event.getKeyCode() == Keys.RIGHT) {
			playerMove(1, 0);

		} else
			// debug things
			if (game.debug && event.getKeyCode() == Keys.M) {

				world.getPlayer().pickup(Item.Parts);
				world.getPlayer().pickup(Item.MasterKey);

				world.getBoard().getBound().setMult(-1, 2);

			} else if (game.debug && event.getKeyCode() == Keys.L) {

				world.getPlayer().addLife(1);

			}

	}

	private void playerLeap() {
		final Player player = world.getPlayer();

		if (player.isLeaping())
			return;

		if (!player.canLeap()) {
			player.messages.add("You [warning]cannot[] leap, you need a [part]part[]");
			return;
		}

		final float duration = 0.7f / 2;
		final int distance = 600 * 2;

		vPlayer.leap(duration);
		getStage().addAction(new ShakeAction(5, 0.2f));

		player.leapStart();

		player.messages.add("Leap #" + (player.boardIndex + 1));

		group.addAction(Actions.sequence(

				Actions.moveBy(-distance * dx, -distance * dy, duration, Interpolation.sineOut),
				Actions.moveBy(2 * distance * dx, 2 * distance * dy),

				Actions.run(new Runnable() {

					@Override
					public void run() {
						player.leapPeak();
						newGroup();

					}

				}),

				Actions.moveBy(-distance * dx, -distance * dy, duration, Interpolation.circleOut),

				Actions.run(new Runnable() {

					@Override
					public void run() {
						player.leapEnd(world.getBoard());
					}

				})));
	}

	private void playerMove(final int x, final int y) {
		dx = x;
		dy = y;

		if (group.hasActions())
			return;

		final Board board = world.getBoard();
		final Player player = world.getPlayer();

		vPlayer.rotate(dx, dy);

		{ // check for block
			final Location newlocation = new Location();
			newlocation.set(player.location).add(x, y);

			// blocking thing
			final Thing thing = board.getThing(newlocation);
			if (thing != null && thing.isBlocking()) {
				if (thing.onPlayerBounce(player)) {
					board.updateOnActvateChange(thing);
				}

				vPlayer.bounceThing(dx, dy);
				vShadow.show(player.location.x + dx, player.location.y + dy);
				return;
			}

			if (!board.getBound().contains(player.location)) {

			} else
				// outside bound
				if (!board.getBound().contains(newlocation)) {

					vPlayer.bounceBound(dx, dy);
					return;
				}
		}

		{
			boolean change = false;

			if (board.hasThing(player.location)) {
				if (board.getThing(player.location).onPlayerLeave(player)) {
					change = true;
					board.updateOnActvateChange(board.getThing(player.location));
				}
			}

			player.location.add(x, y);
			player.addLevel(.006f);

			if (board.hasThing(player.location)) {
				if (board.getThing(player.location).onPlayerEnter(player)) {
					change = true;
					board.updateOnActvateChange(board.getThing(player.location));
				}
			}

			// clean up destroyed
			while (change) {
				change = false;

				for (final Thing i : board) {
					if (!i.isDestroyed()) continue;

					board.removeThing(i.getLocation());

//					thingImages.get(i).remove();
//					thingImages.remove(i);

					change = true;
					break;
				}
			}

		}

		vPlayer.move(dx, dy);
		vShadow.show(player.location.x, player.location.y);

	}

	private HashMap<Thing, ViewThing> thingImages = new HashMap<Thing, ViewThing>();
	private Label logo;

	private void newGroup() {
		group.clearChildren();

		final Player player = world.getPlayer();

		player.location.set(0, 0);

		player.boardIndex += 1;
		if (Boards.fixed.containsKey(player.boardIndex)) {

			world.setBoard(Boards.fixed.get(player.boardIndex).build(player));
		} else {
			world.setBoard(new Generator().generate(player));
		}

		final Board board = world.getBoard();

		vBound.set(board.getBound());
		group.addActor(vBound);

		thingImages.clear();

		groupThings.clear();
		group.addActor(groupThings);

		if (board.name.length() > 0) {
			player.messages.add(board.name);
		}
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		final Board board = world.getBoard();
		final Player player = world.getPlayer();

		final Bound bound = board.getBound();

		bound.update(delta);

//		final Rectangle rec = new Rectangle();
//		rec.setSize(((int) bound.w / gsize / 2) * 2, ((int) bound.h / gsize / 2) * 2);
//		rec.setCenter(0, 0);

		if (!player.isLeaping() && !board.getBound().contains(player.location)) {

			if (!damage.hasActions()) {
				damage.addAction(Actions.alpha(0.7f, 0.3f, Interpolation.circleOut));
			}
			// TODO red border

			player.life.damage(delta);

			if (player.life.isDead() && !getStage().getRoot().hasActions()) {

				damage.addAction(Actions.sequence(
						Actions.fadeIn(.2f, Interpolation.bounceOut),
						Actions.fadeOut(1, Interpolation.sine)));

				vShadow.setVisible(false);
//				playerImage.addAction(Actions.sequence());

//				getStage().getRoot().setOrigin(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
				getStage().addAction(Actions.sequence(

						new ShakeAction(20, .5f),
						Actions.delay(.2f),
						Actions.fadeOut(.4f),
						Actions.delay(1)

				));
				fadeScreen(new ScreenGame(game), 1);

				player.messages.add("You are [warning]dead[] ...");

				// TODO game over
			}

		} else {

			if (damage.getColor().a != 0 && !damage.hasActions()) {
				damage.addAction(Actions.alpha(0f, 0.1f, Interpolation.circleIn));
			}

		}

		for (final Thing i : board) {

			if (!board.getBound().contains(i.getLocation())) {
				if (i.onOutsideBound()) {
					board.updateOnActvateChange(i);

					board.removeThing(i.getLocation());
				}
			}
		}

		final Image object = new Image(game.skin.getDrawable("solid-black"));
		object.getColor().a = 0.1f;
		object.setSize(gsize, gsize);

		for (final Thing thing : board) {

			if (!thingImages.containsKey(thing)) {

				final ViewThing image = ViewsThing.create(game.skin, gsize, thing, thing.getOrigin());
				groupThings.addActor(image);

				thingImages.put(thing, image);
			}

		}

		if (Gdx.graphics.getFramesPerSecond() < 55) {
			version.setVisible(true);
			version.setText(Gdx.graphics.getFramesPerSecond() + "");
		} else {
			version.setVisible(false);
		}

	}

}
