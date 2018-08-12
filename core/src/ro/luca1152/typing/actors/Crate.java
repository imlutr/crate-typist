package ro.luca1152.typing.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;

import ro.luca1152.typing.TypingGame;

public class Crate extends Group {
    private final TypingGame game;
    private final Vector2[] LENGTH_RANGE = new Vector2[]{
            new Vector2(), // 0 letters
            new Vector2(), // 1 letter
            new Vector2(0, 25), // 2 letters
            new Vector2(26, 182), // 3 letters
            new Vector2(183, 613), // 4 letters
            new Vector2(614, 1027), // 5 letters
            new Vector2(1028, 1399), // 6 letters
            new Vector2(1400, 1705), // 7 letters
            new Vector2(1706, 1908), // 8 letters
            new Vector2(1909, 2054), // 9 letters
            new Vector2(2055, 2161), // 10 letters
            new Vector2(2162, 2221), // 11 letters
            new Vector2(2222, 2241), // 12 letters
            new Vector2(2242, 2254), // 13 letters
            new Vector2(2255, 2260) // 14 letters
    };
    private ArrayList<String> allCratesWords;
    private String[] wordList;
    private Rectangle collisionBox;

    private BackgroundLabel label;
    private Image crateImage;
    private String initialWord;
    private String word;

    private boolean reachedFinish = false;

    Crate(TypingGame game, ArrayList<String> allCratesWords, float mapX, float mapY) {
        this.game = game;
        this.allCratesWords = allCratesWords;
        this.wordList = game.getWordList();
        this.collisionBox = new Rectangle();
        setPosition(mapX * 64, mapY * 64);
        setSize(64, 64);
        collisionBox.setSize(getWidth(), getHeight());
        setOrigin(getWidth() / 2f, getHeight() / 2f);
        getRandomWord();
        addCrateImage();
        addLabel();
    }

    private void getRandomWord() {
        getRandomWord(MathUtils.random(2, 14));
    }

    private void addCrateImage() {
        if (word.length() <= 3)
            crateImage = new Image(game.getManager().get("textures/wooden_crate.png", Texture.class));
        else if (word.length() <= 5)
            crateImage = new Image(game.getManager().get("textures/golden_crate.png", Texture.class));
        else
            crateImage = new Image(game.getManager().get("textures/diamond_crate.png", Texture.class));
        crateImage.setPosition(getX(), getY());
        addActor(crateImage);
    }

    private void addLabel() {
        label = new BackgroundLabel(word, game.getLabelStyle());
        label.setPosition(getX(), getY() + 70);
        label.setSize(label.getWidth() + 4, label.getHeight() + 4);
        label.setAlignment(Align.center, Align.center);
        addActor(label);
    }

    private void getRandomWord(int length) {
        // Has to have the first letter different to all the other crates' word
        while (true) {
            word = wordList[MathUtils.random(0, wordList.length - 1)];
            int numSameFirstLetter = 0;
            for (int i = 0; i < allCratesWords.size(); i++) {
                if (allCratesWords.get(i).charAt(0) == word.charAt(0))
                    numSameFirstLetter++;
            }
            if (numSameFirstLetter == 0)
                break;
        }
        initialWord = word;
        allCratesWords.add(initialWord);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        crateImage.setPosition(getX(), getY());
        label.setPosition(getX() + getWidth() / 2f - label.getPrefWidth() / 2f, getY() + 70);
        if (wordIsEmpty())
            removeCrate(false);
    }

    public boolean wordIsEmpty() {
        return firstCharFromWord() == ' ';
    }

    public void removeCrate(boolean reachedFinish) {
        this.reachedFinish = reachedFinish;
        remove();
        allCratesWords.remove(initialWord);
    }

    public char firstCharFromWord() {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == '[') {
                while (word.charAt(i - 1) != ']') {
                    if (i == word.length() - 1)
                        return ' ';
                    i++;
                }
            }
            if (word.charAt(i) != ' ')
                return word.charAt(i);
        }
        return ' ';
    }

    public void keyPressed(String key) {
        if (!word.contains("[ORANGE]"))
            word = word.replaceFirst(key, " [ORANGE]");
        else
            word = word.replaceFirst(key, " ");
        label.setText(word);
    }

    public Rectangle getCollisionBox() {
        collisionBox.setPosition(getX(), getY());
        return collisionBox;
    }

    public BackgroundLabel getLabel() {
        return label;
    }

    public Image getCrateImage() {
        return crateImage;
    }

    public boolean isReachedFinish() {
        return reachedFinish;
    }
}
