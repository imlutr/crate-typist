package ro.luca1152.typing.actors;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.JsonValue;

import org.codejargon.feather.Key;

import java.util.ArrayList;

import ro.luca1152.typing.MyGame;
import ro.luca1152.typing.screens.GameOverScreen;
import ro.luca1152.typing.ui.BackgroundLabel;

public class Crate extends Group {
    private final Game game;
    private final GameOverScreen gameOverScreen;
    private final AssetManager manager;
    private final Skin skin;
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
    private LabelStyle labelStyle17bg;
    private LabelStyle labelStyle17bg2;
    private ArrayList<String> allCratesWords;
    private String[] wordList;
    private Rectangle collisionBox;

    private BackgroundLabel label;
    private Image crateImage;
    private String initialWord;
    private String word;
    private int lives;
    private int realLives;
    private int totalStepsToDo;
    private int stepsUntilFinish;

    private boolean labelRemoved = false;
    private boolean isTargetCrate = false;
    private float stepsTimer = 0f;
    private float speed;
    private GameMap gameMap;
    private float scoreMultiplier = 1f;
    private char lastFirstCharFromWord;

    Crate(ArrayList<String> allCratesWords,
          float mapX, float mapY, float speed,
          JsonValue currentMap) {
        this.game = MyGame.feather.instance(Game.class);
        this.gameOverScreen = MyGame.feather.instance(GameOverScreen.class);
        this.manager = MyGame.feather.instance(AssetManager.class);
        this.skin = manager.get("skin/skin.json", Skin.class);
        this.wordList = MyGame.feather.instance(Key.of(String[].class, "wordList"));
        this.setPosition(mapX * 64, mapY * 64);
        this.setSize(64, 64);
        this.speed = speed;
        this.allCratesWords = allCratesWords;
        this.setOrigin(getWidth() / 2f, getHeight() / 2f);
        this.collisionBox = new Rectangle();
        this.collisionBox.setSize(getWidth(), getHeight());
        this.totalStepsToDo = currentMap.getInt("totalSteps");
        this.stepsUntilFinish = this.totalStepsToDo;
        getRandomWord();
        addCrateImage();
        addLabel();
    }

    private void getRandomWord() {
        getRandomWord(0);
    }

    private void addCrateImage() {
        if (word.length() <= 3)
            crateImage = new Image(manager.get("textures/wooden_crate.png", Texture.class));
        else if (word.length() <= 5)
            crateImage = new Image(manager.get("textures/copper_crate.png", Texture.class));
        else if (word.length() <= 7)
            crateImage = new Image(manager.get("textures/golden_crate.png", Texture.class));
        else if (word.length() <= 9)
            crateImage = new Image(manager.get("textures/diamond_crate.png", Texture.class));
        else //if (word.length() <= 9)
            crateImage = new Image(manager.get("textures/amethyst_crate.png", Texture.class));
        crateImage.setPosition(getX(), getY());
        addActor(crateImage);
    }

    private void addLabel() {
        label = new BackgroundLabel(word, skin, "tiny", "white");
        label.setPosition(getX(), getY() + 70);
        label.setSize(label.getWidth() + 4, label.getHeight() + 4);
        label.setAlignment(Align.center, Align.center);
        addActor(label);
    }

    private void getRandomWord(int length) {
        while (true) {
            if (length == 0)
                word = MyGame.wordList[MathUtils.random(0, wordList.length - 1)];
            else
                word = MyGame.wordList[MathUtils.random((int) LENGTH_RANGE[length].x, (int) LENGTH_RANGE[length].y)];
            int numSameFirstLetter = 0;
            for (int i = 0; i < allCratesWords.size(); i++) {
                if (allCratesWords.get(i).charAt(0) == word.charAt(0))
                    numSameFirstLetter++;
            }
            if (numSameFirstLetter == 0)
                break;
        }
        initialWord = word;
        realLives = initialWord.length();
        lives = realLives;
        allCratesWords.add(initialWord);
    }

    @Override
    protected void setStage(Stage stage) {
        super.setStage(stage);
        if (stage != null) {
            gameMap = (GameMap) (getParent().getParent());
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        crateImage.setPosition(getX(), getY());
        label.setPosition(getX() + getWidth() / 2f - label.getPrefWidth() / 2f, getY() + 70);
        if (wordIsEmpty())
            removeCrate(false);
        updateStepsLeft(delta);
        updateColorBasedOnSteps();
        if (lives == 0) {
            remove();
        }
    }

    public boolean wordIsEmpty() {
        return lives == 0;
    }

    public void removeCrate(boolean reachedFinish) {
        updateScore();
        if (reachedFinish)
            manager.get("audio/shutdown.mp3", Sound.class).play(.7f);
        else
            manager.get("audio/crate_breaking.wav", Sound.class).play(.7f);
        remove();
        allCratesWords.remove(initialWord);
        gameMap.numberOfCratesRemaining--;
        if (reachedFinish) {
            gameOverScreen.score = (int) gameMap.getScore();
            gameOverScreen.wave = gameMap.getWave();
            game.setScreen(gameOverScreen);
        }
    }

    private void updateStepsLeft(float delta) {
        stepsTimer -= delta;
        if (stepsTimer <= 0f) {
            stepsTimer = speed;
            stepsUntilFinish--;
        }
    }

    private void updateColorBasedOnSteps() {
        if (stepsUntilFinish <= totalStepsToDo / 3f) {
            label.setColor(Color.RED);
            scoreMultiplier = 1.8f;
        } else if (stepsUntilFinish <= totalStepsToDo / 1.5f) {
            label.setColor(Color.YELLOW);
            scoreMultiplier = 1.3f;
        } else if (!isTargetCrate)
            scoreMultiplier = .8f;
        if (isTargetCrate)
            label.setColor(Color.WHITE);
    }

    private void updateScore() {
        float scoreToAdd = 2 * gameMap.getScoreMultiplier() * scoreMultiplier;
        gameMap.incrementScoreBy(scoreToAdd);
    }

    public boolean isOffScreen() {
        return getY() < 0 || getX() < 0 || getX() > Gdx.graphics.getWidth() || getY() > Gdx.graphics.getHeight();
    }

    public void keyPressed(String key) {
        lastFirstCharFromWord = firstCharFromWord();
        if (!word.contains("[ORANGE]"))
            word = word.replaceFirst(key, " [ORANGE]");
        else
            word = word.replaceFirst(key, " ");
        label.setText(word);
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

    public char getLastFirstCharFromWord() {
        return lastFirstCharFromWord;
    }

    public Rectangle getCollisionBox() {
        collisionBox.setPosition(getX(), getY());
        return collisionBox;
    }

    public void setIsTargetCrate(boolean targetCrate) {
        isTargetCrate = targetCrate;
        if (isTargetCrate) {
            label.setOpacity(1f);
        }
    }

    public void removeLabel() {
        labelRemoved = true;
    }

    public boolean isLabelRemoved() {
        return labelRemoved;
    }

    public BackgroundLabel getLabel() {
        return label;
    }

    public Image getCrateImage() {
        return crateImage;
    }

    public void collision() {
        lives--;
    }

    public boolean lastLife() {
        return realLives == 0;
    }

    public void shootAt() {
        realLives--;
    }

    public boolean isTargetCrate() {
        return isTargetCrate;
    }
}
