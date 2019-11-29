package com.maanoo.leabound.face.widget;

import java.util.Comparator;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import com.maanoo.leabound.core.Player;
import com.maanoo.leabound.core.item.Item;


public class ViewBag extends ViewMod {

	private final Skin skin;

	private final Player player;

	private final Image vBack;

	public ViewBag(Skin skin, Player player) {
		super(player.bag);
		this.skin = skin;
		this.player = player;

		vBack = new Image(skin, "solid-black");
		vBack.getColor().a = 0.75f;
		vBack.setSize(0, 30);

		refresh();
	}

	@Override
	protected void refresh() {
		clearChildren();

		setHeight(30);

		final HorizontalGroup hg = new HorizontalGroup();
//		hg.setSize(getWidth(), getHeight());

		final Array<Item> items = new Array<Item>();

		for (final Item i : player.bag) {
			if (player.bag.has(i)) items.add(i);
		}

		items.sort(new Comparator<Item>() {

			@Override
			public int compare(Item o1, Item o2) {

				if (o1.isLocal() != o2.isLocal()) return o1.isLocal() ? 1 : -1;

				return -Integer.compare(player.bag.get(o1), player.bag.get(o2));
			}

		});

		boolean firstLocal = true;

		for (final Item i : items) {
			if (i.isLocal() && firstLocal) {
				// TODO better split gui and code
				firstLocal = false;
				hg.addActor(new Label("  [#ffffff80]| ", skin));
			}

			final int count = player.bag.get(i);

			final Label label = new Label(" " + (count == 1 ? "" : (count + " ")) + "", skin);
			final Image icon = new Image(skin, i.getName());
			icon.setOrigin(Align.center);

			hg.addActor(label);
			hg.addActor(icon);

		}

		hg.setPosition(10, 15, Align.left);

		hg.layout();
		setWidth(hg.getPrefWidth() + 20);

		vBack.addAction(Actions.sizeTo(getWidth(), getHeight(), 0.3f, Interpolation.circleOut));

		addActor(vBack);
		addActor(hg);

//		setDebug(true, true);
	}

}
