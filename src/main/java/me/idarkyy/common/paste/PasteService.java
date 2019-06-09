package me.idarkyy.common.paste;

import java.io.IOException;

public abstract class PasteService {
    public abstract /*url*/String paste(Paste paste) throws IOException;
}
