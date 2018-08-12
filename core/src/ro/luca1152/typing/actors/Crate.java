package ro.luca1152.typing.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import ro.luca1152.typing.TypingGame;

public class Crate extends Group {
    private final TypingGame game;
    private final String words[] = {
            "if", "ad", "by",
            "for", "max", "mix",
            "pair", "gift", "jump",
            "white", "alien", "board",
            "smooth", "strong", "barrel",
            "skateboard"};

    private BackgroundLabel label;
    private Image crateImage;
    private String word;

    Crate(TypingGame game, float mapX, float mapY) {
        this.game = game;
        setPosition(mapX * 64, mapY * 64);
        setSize(64, 64);
        getRandomWord();
        addCrateImage();
        addLabel();
    }

    private void getRandomWord() {
        word = words[MathUtils.random(0, words.length - 1)];
    }

    private void addCrateImage() {
        if (word.length() <= 3)
            crateImage = new Image(game.getManager().get("textures/wooden_crate.png", Texture.class));
        else
            crateImage = new Image(game.getManager().get("textures/golden_crate.png", Texture.class));
        crateImage.setPosition(getX(), getY());
        addActor(crateImage);
    }

    private void addLabel() {
        label = new BackgroundLabel(word, game.getLabelStyle());
        label.setPosition(getX(), getY() + 70);
        addActor(label);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        crateImage.setPosition(getX(), getY());
        label.setPosition(getX() + getWidth() / 2f - label.getPrefWidth() / 2f, getY() + 70);
    }

    public BackgroundLabel getLabel() {
        return label;
    }

    public Image getCrateImage() {
        return crateImage;
    }
}
