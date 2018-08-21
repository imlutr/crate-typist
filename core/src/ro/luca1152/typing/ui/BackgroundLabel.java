package ro.luca1152.typing.ui;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class BackgroundLabel extends Label {
    public BackgroundLabel(CharSequence text, LabelStyle labelStyle) {
        super(text, labelStyle);
        setOpacity(.65f);
    }

    public BackgroundLabel(CharSequence text, Skin skin, String fontName, String color) {
        super(text, skin, fontName, color);
        setOpacity(.65f);
    }

    public void setOpacity(float opacity) {
        Pixmap labelBg = new Pixmap((int) getPrefWidth(), (int) getPrefHeight() + 100, Pixmap.Format.RGBA8888);
        labelBg.setColor(0f, 0f, 0f, opacity);
        labelBg.fill();
        getStyle().background = new Image(new Texture(labelBg)).getDrawable();
    }
}
