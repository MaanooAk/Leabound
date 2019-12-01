package com.maanoo.leabound.face.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
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

	public static Provider provider;

	public static void take() {

		take("Leabound." + System.currentTimeMillis() + ".png");
	}

	private static void take(final String localPath) {

		if (provider == null) {
			// not supported
			return;
		}

		final int w = Gdx.graphics.getBackBufferWidth();
		final int h = Gdx.graphics.getBackBufferHeight();

		final byte[] pixels = ScreenUtils.getFrameBufferPixels(0, 0, w, h, true);

		for (int i = 4; i < pixels.length; i += 4) pixels[i - 1] = (byte) 255;

		final Pixmap pixmap = new Pixmap(w, h, Pixmap.Format.RGBA8888);
		BufferUtils.copy(pixels, 0, pixmap.getPixels(), pixels.length);

		provider.writePixmap(Gdx.files.local(localPath), pixmap);

		pixmap.dispose();
	}

	public static interface Provider {

		public boolean writePixmap(FileHandle file, Pixmap pixmap);
	}

}