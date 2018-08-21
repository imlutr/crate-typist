package ro.luca1152.typing;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import org.codejargon.feather.Feather;

import ro.luca1152.typing.screens.LoadingScreen;

public class MyGame extends Game {
    public static Feather feather;
    public static String[] wordList;

    @Override
    public void create() {
        MyGame.wordList = new String[2261];
        MyGame.feather = Feather.with(new GameModule(this));
        setScreen(feather.instance(LoadingScreen.class));
    }
}
