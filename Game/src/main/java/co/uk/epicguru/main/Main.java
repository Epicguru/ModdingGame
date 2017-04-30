package co.uk.epicguru.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;

import co.uk.epicguru.logging.Log;

public final class Main extends Game {

	public static void main(String... args){
		// Create game instance
		Main main = new Main();

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Moddable Game!");

		new Lwjgl3Application(main, config);
	}

	public void create() {
		Log.info("Hello world!");
		
	}

	public void render() {
		float c = 0.8f;
		Gdx.gl.glClearColor(c, c, c, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	public void dispose() {
		Log.info("Bye bye!");
	}	
}
