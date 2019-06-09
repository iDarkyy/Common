package me.idarkyy.common.paste;

import java.io.IOException;

public class Paste {
    private StringBuilder text = new StringBuilder();

    private Paste() {

    }

    public static Paste create() {
        return new Paste();
    }

    public Paste append(String string) {
        text.append(string);
        return this;
    }

    public Paste append(char c) {
        text.append(c);
        return this;
    }

    public Paste append(Number n) {
        text.append(n);
        return this;
    }

    public Paste clear() {
        text = new StringBuilder();
        return this;
    }

    public String paste(PasteService service) throws IOException {
        return service.paste(this);
    }

    public StringBuilder getStringBuilder() {
        return text;
    }

    public String getText() {
        return text.toString();
    }

    public Paste setText(CharSequence text) {
        this.text.append(text);
        return this;
    }
}
