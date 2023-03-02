package commands;

public abstract class AbstractCommand {
    private final String name;
    private final int numberOfArgs;
    private final String commandDescription;
    private final String argsDescription;

    public AbstractCommand(String name, int numberOfArgs, String commandDescription, String argsDescription) {
        this.name = name;
        this.numberOfArgs = numberOfArgs;
        this.commandDescription = commandDescription;
        this.argsDescription = argsDescription;
    }

    public AbstractCommand(String name, String commandDescription) {
        this.name = name;
        this.numberOfArgs = 0;
        this.commandDescription = commandDescription;
        this.argsDescription = "";
    }

    public String getName() {
        return name;
    }

    public String getCommandDescription() {
        return commandDescription;
    }

    public abstract void executeCommand(String[] commandArgs);

    @Override
    public String toString() {
        return "Название команды: " + name + ", " + "аргументы: "
                + ((numberOfArgs == 0) ? "команда не требует аргументов" : argsDescription)
                + ", описание: " + commandDescription;
    }
}
