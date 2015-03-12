package com.unkani.MarketRPG;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.unkani.screens.GameScreen;

import java.util.HashMap;
import java.util.Map;


public class MarketRPG extends Game implements GestureListener {
    //Image
    private OrthographicCamera camera;
    private Stage stage;
    private Skin skin;
    private GestureDetector gestureDet;
    private InputMultiplexer multiplexer;
    private Array<InputProcessor> processors;

    private SpriteBatch batch;
    //private Sprite sprite;
    private Texture placeholder;
    private Texture background;


    //Text
    private BitmapFont font;
    private String message = "No gesture";
    private int w, h;

    private Map<Integer, TouchInfo> touches = new HashMap<Integer, TouchInfo>();

	@Override
	public void create () {
        Gdx.app.log("MarketRPG", "created");
        setScreen(new GameScreen());
        camera = new OrthographicCamera(1280, 720); //affects only the Spritebatch.
        stage = new Stage();
        gestureDet = new GestureDetector(this);
        multiplexer = new InputMultiplexer(stage, gestureDet);
        //processors = new Array<InputProcessor>();
        //processors.add(stage);
        //processors.add(gestureDet);
        //this.multiplexer.setProcessors(processors);
        Gdx.input.setInputProcessor(this.multiplexer); //All inputs are recorded on the stage.

        //viewport, batch
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        final TextButton button = new TextButton("Click me", skin, "default");


        button.setWidth(200f);
        button.setHeight(20f);
        button.setPosition(Gdx.graphics.getWidth() / 2 - 100f, Gdx.graphics.getHeight() / 2 - 10f);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                button.setText("you clicked");
            }
        });


        batch = new SpriteBatch();
        placeholder = new Texture(Gdx.files.internal("placeholder.png"));
        background = new Texture(Gdx.files.internal("2048.jpg"));
        background.setFilter(TextureFilter.Linear, TextureFilter.Linear);


        CustomActor back = new CustomActor(background, false);
        CustomActor placeholderChar = new CustomActor(placeholder, true);

        placeholderChar.setTouchable(Touchable.enabled); //Actor can be touched.
        stage.addActor(back);
        stage.addActor(placeholderChar);
        stage.addActor(button);


        font = new BitmapFont();
        font.setColor(Color.BLACK);
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();

        //Gdx.input.setInputProcessor(gd);
	}

    @Override
    public void dispose(){
        batch.dispose();
        //img.dispose();
        font.dispose();
    }
	@Override
	public void render () {
        //Event loop
		Gdx.gl.glClearColor(1, 1, 1, 1); //RGBA
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        batch.setProjectionMatrix(camera.combined);
		batch.begin();

        //sprite.draw(batch);
        TextBounds tb = font.getBounds(message);
        float x = Gdx.graphics.getWidth()/2 - tb.width/2;
        float y = Gdx.graphics.getHeight()/2 + tb.height/2;
        font.drawMultiLine(batch, message, x, y);

		//batch.draw(img, 0, 0);

		batch.end();
	}

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }





    //All the gesture methods
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        message = "Tap performed, finger" + Integer.toString(button);
        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
        message = "Long press performed";
        return true;
    }

    public boolean fling(float velocityX, float velocityY, int button){
        message = "Fling performed, velocity:" + Float.toString(velocityX) +
                "," + Float.toString(velocityY);
        return true;
    }
    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        message = "Pan performed, delta:" + Float.toString(deltaX) +
                "," + Float.toString(deltaY);
        camera.translate(-deltaX, 0);
        camera.update();
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        message = "Pan stopped at:" + Float.toString(x) +
                "," + Float.toString(y);
        return true;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        message = "Zoom performed, initial Distance:" + Float.toString(initialDistance) +
                " Distance: " + Float.toString(distance);
        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
                         Vector2 pointer1, Vector2 pointer2) {
        message = "Pinch performed";
        return true;
    }

}
