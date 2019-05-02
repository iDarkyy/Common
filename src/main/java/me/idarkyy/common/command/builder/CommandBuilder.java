package me.idarkyy.common.command.builder;

import me.idarkyy.common.command.CommandMap;
import me.idarkyy.common.command.interfaces.Command;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CommandBuilder {
    private CommandMap commandMap;
    private Command command;

    private String name;
    private List<String> aliases = Collections.emptyList();

    public CommandBuilder(CommandMap commandMap, Command command) {
        this.commandMap = commandMap;
    }

    public CommandBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CommandBuilder aliases(String... aliases) {
        this.aliases = Arrays.asList(aliases);
        return this;
    }

    public CommandBuilder aliases(List<String> aliases) {
        this.aliases = aliases;
        return this;
    }

    public CommandMap register() {
        Objects.requireNonNull(name, "The command name must not be null");

        commandMap.register(command, name, aliases);

        return commandMap;
    }
}
