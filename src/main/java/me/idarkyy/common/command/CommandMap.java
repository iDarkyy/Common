package me.idarkyy.common.command;

import me.idarkyy.common.annotation.Nullable;
import me.idarkyy.common.command.builder.CommandBuilder;
import me.idarkyy.common.command.interfaces.Command;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class CommandMap {
    private HashMap<String, Command> commands = new HashMap<>();
    private HashMap<String, String> aliases = new HashMap<>();

    private @Nullable
    Responder responder;

    public CommandMap() {
        this(null);
    }

    public CommandMap(Responder responder) {
        this.responder = responder;
    }

    public CommandBuilder newCommand(Command command) {
        return new CommandBuilder(this, command);
    }

    public void register(Command command, String name) {
        register(command, name, Collections.emptyList());
    }

    public void register(Command command, String name, List<String> aliases) {
        if (findCommandString(name) != null) {
            throw new IllegalArgumentException("Command " + name + " already exists");
        }

        for (String string : aliases) {
            if (findCommandString(name) != null) {
                throw new IllegalArgumentException("Sub-command " + name + " already exists");
            }
        }

        this.commands.put(name, command);
        aliases.forEach(a -> CommandMap.this.aliases.put(a, name));
    }

    public void execute(String name, String... args) {
        name = findCommandString(name);

        if (name == null || !commands.containsKey(name)) {
            throw new IllegalArgumentException("Invalid command " + name);
        }

        Command command = commands.get(name);
        command.onCommand(name, args, responder);
    }

    public Responder getResponder() {
        return responder;
    }

    public void setResponder(Responder responder) {
        this.responder = responder;
    }

    public void setResponder(Consumer<String> responder) {
        setResponder(new Responder(responder));
    }

    private String findCommandString(String name) {
        if (aliases.containsKey(name)) {
            return aliases.get(name);
        }

        if (commands.containsKey(name)) {
            return name;
        }

        return null;
    }
}
