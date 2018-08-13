package ro.luca1152.typing.screens;

import ro.luca1152.typing.actors.BackgroundLabel;

public abstract class Button extends BackgroundLabel {
    public Button(CharSequence text, LabelStyle labelStyle) {
        super(text, labelStyle);
    }
    public abstract void doSomething();
}
