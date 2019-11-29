package com.maanoo.leabound;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.Glyph;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import com.maanoo.leabound.face.ScreenGame;


public class LeaboundGame extends Game {

	// TODO fix the vector to location transition (many are named vector)
	// TODO tutorial for not

	public static LeaboundGame I; // TODO remove

	public LeaboundSkin skin;

	public boolean debug = true;

	@Override
	public void create() {
		I = this;// TODO remove

		skin = new LeaboundSkin("base");

		setScreen(new ScreenGame(this).fadeIn());

	}

	public class LeaboundSkin extends Skin {

		public LeaboundSkin(String name) {
			super();

			addRegions(new TextureAtlas(Gdx.files.internal("graphics/all.atlas")));

			add("default-font", new BitmapFont(Gdx.files.internal("graphics/fonts/12.fnt"),
					getRegion("12")));

			load(Gdx.files.internal("graphics/skin." + name + ".json"));

			final BitmapFont font = getFont("default-font");
			font.getData().markupEnabled = true;
			offsetFont(font, 8);
			font.setFixedWidthGlyphs("1234567890");
			font.setFixedWidthGlyphs("t ");

			Colors.put("warning", new Color(0xff9c00ff));
			Colors.put("fade", new Color(0xffffff80));
			Colors.put("key", new Color(0xf0f000ff));
			Colors.put("part", new Color(0x46c1e4FF));
			Colors.put("leap", new Color(0xed4defFF));
//			Colors.put("health", new Color(0x448044ff));
//			Colors.put("attack", new Color(0x804444ff));
			Colors.put("chest", new Color(0xdf8b4eff));

			addDrawable("", new BaseDrawable());

		}

		public void addDrawable(String name, String drawable) {
			addDrawable(name, getDrawable(drawable));
		}

		public void addDrawable(String name, Drawable drawable) {
			add(name, drawable, Drawable.class);
		}

		@Override
		public Drawable getDrawable(String name) {
			return super.getDrawable(name);
		}

		private void offsetFont(final BitmapFont font, int dy) {
			for (final Glyph[] page : font.getData().glyphs) {
				if (page != null)
					for (final Glyph i : page) {
						if (i != null)
							i.yoffset -= dy;
					}
			}
		}

	}

}
