package me.idarkyy.common.command.interfaces;

import me.idarkyy.common.annotation.Nullable;
import me.idarkyy.common.command.Responder;

public interface Command {
    void onCommand(String command, String[] args, @Nullable Responder responder);
}
