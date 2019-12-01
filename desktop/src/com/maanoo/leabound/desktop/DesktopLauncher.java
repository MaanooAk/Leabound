package com.maanoo.leabound.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;

import com.maanoo.leabound.LeaboundGame;
import com.maanoo.leabound.face.util.Screenshot;


public class DesktopLauncher {

	public static void main(String[] arg) {
		final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Leabound";

		config.width = 800;
		config.height = 600;
		config.resizable = false;
//		config.samples = 4;

		Screenshot.provider = new ScreenshotProvider();

		new LwjglApplication(new LeaboundGame(), config);
	}

	private static final class ScreenshotProvider implements Screenshot.Provider {

		@Override
		public boolean writePixmap(FileHandle file, Pixmap pixmap) {

			try {
				PixmapIO.writePNG(file, pixmap);
				return true;
			} catch (final Exception e) {
				e.printStackTrace();
				return false;
			}
		}

	}

}
