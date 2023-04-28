package commands;

/**
 * Abstract class of command
 */
public abstract class AbstractCommand {
    private final String name;
    private final int numberOfArgs;
    private final String commandDescription;
    private final String argsDescription;

    /**
     * Constructs Command with given number of args
     *
     * @param name name of command
     * @param numberOfArgs number of args
     * @param commandDescription description of command
     * @param argsDescription description of args
     */
    public AbstractCommand(String name, int numberOfArgs, String commandDescription, String argsDescription) {
        this.name = name;
        this.numberOfArgs = numberOfArgs;
        this.commandDescription = commandDescription;
        this.argsDescription = argsDescription;
    }

    /**
     * Constructs Command without args
     *
     * @param name name of command
     * @param commandDescription description of command
     */
    public AbstractCommand(String name, String commandDescription) {
        this.name = name;
        this.numberOfArgs = 0;
        this.commandDescription = commandDescription;
        this.argsDescription = "";
    }

    /**
     * @return name field
     */
    public String getName() {
        return name;
    }

    /**
     * @return number of args field
     */
    public int getNumberOfArgs() {
        return numberOfArgs;
    }

    /**
     * @param commandArgs list of string args
     */
    public abstract void executeCommand(String[] commandArgs);

    /**
     * Overrides {@code toString} of {@code Object}
     *
     * @return string representation of command
     */
    @Override
    public String toString() {
        return "Command name: " + name + ", description: " + commandDescription
                + ((numberOfArgs == 0) ? "" : ", args: " + argsDescription);
    }
}
