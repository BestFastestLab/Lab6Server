public class Identifier {
    public static Commands Identify(Commands command) {
        switch (command.getName()) {
            case "help":
                command = CommandExecution.help();
                break;
            case "info":
                command = CommandExecution.info();
                break;
            case "show":
                command = CommandExecution.show();
                break;
            case "add":
                CommandExecution.add(command);
                break;
            case "update":
                command = CommandExecution.updateId(command.getId());
                break;
            case "remove_by_id":
                command = CommandExecution.removeById(command.getId());
                break;
            case "clear":
                command = CommandExecution.clear();
                break;
            case "add_if_max":
                CommandExecution.addIfMax(command);
                break;
            case "remove_greater":
                CommandExecution.removeGreater(command);
                break;

            case "execute_script":
                command = CommandExecution.executeScript(command.getFileName());
                break;
            case "history":
                command = CommandExecution.history();
                break;
            case "min_by_albums_count":
                command = CommandExecution.minByAlbumsCount();
                break;
            case "count_greater_than_genre":
                command = CommandExecution.countGreaterThanGenre(command.getGenre());
                break;
            case "print_ascending":
                command = CommandExecution.printAscending();
                break;
            case "exit":
                command.setResult("exit");
        }
        return command;
    }
}
