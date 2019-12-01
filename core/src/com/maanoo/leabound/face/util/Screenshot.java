package com.maanoo.leabound.face.util;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.ScreenUtils;


/**
 * Take screenshots of the active window.
 *
 * @author Akritas Akritidis
 */
public final class Screenshot {

	private Screenshot() {
	}

	public static void take() {

		take("Leabound." + System.currentTimeMillis() + ".png");
	}

	private static void take(final String localPath) {

		// not supported on html
		if (Gdx.app.getType() == ApplicationType.WebGL) return;

		final int w = Gdx.graphics.getBackBufferWidth();
		final int h = Gdx.graphics.getBackBufferHeight();

		final byte[] pixels = ScreenUtils.getFrameBufferPixels(0, 0, w, h, true);

		for (int i = 4; i < pixels.length; i += 4) pixels[i - 1] = (byte) 255;

		final Pixmap pixmap = new Pixmap(w, h, Pixmap.Format.RGBA8888);
		BufferUtils.copy(pixels, 0, pixmap.getPixels(), pixels.length);

		PixmapIO.writePNG(Gdx.files.local(localPath), pixmap);

		pixmap.dispose();
	}

}