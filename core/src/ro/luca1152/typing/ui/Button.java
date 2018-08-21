package ro.luca1152.typing.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public abstract class Button extends BackgroundLabel {
    protected Button(CharSequence text, LabelStyle labelStyle) {
        super(text, labelStyle);
    }

    protected Button(CharSequence text, Skin skin, String fontName, String color) {
        super(text, skin, fontName, color);
    }

    public abstract void doSomething();
}
