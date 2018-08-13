package ro.luca1152.typing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

import ro.luca1152.typing.TypingGame;

public class MainMenuScreen extends ScreenAdapter {
    private final TypingGame game;
    private final InputAdapter inputAdapter;

    public MainMenuScreen(final TypingGame game) {
        this.game = game;
        inputAdapter = new InputAdapter(){
            @Override
            public boolean keyUp(int keycode) {
                if (keycode == Input.Keys.SPACE) {
                    game.setScreen(game.playScreen);
                }
                return super.keyUp(keycode);
            }
        };
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputAdapter);
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
