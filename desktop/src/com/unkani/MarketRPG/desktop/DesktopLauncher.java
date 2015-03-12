package com.unkani.MarketRPG.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.unkani.MarketRPG.MarketRPG;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "MarketRPG";
        config.width = 1280;
        config.height = 720;


		new LwjglApplication(new MarketRPG(), config);
	}
}
