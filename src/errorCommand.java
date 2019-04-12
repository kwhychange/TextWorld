public class errorCommand implements command {
    @Override
    public void init(String response) {

    }

    public errorCommand() {
    }

    @Override
    public void execute() {
        System.out.println("error; try again");
        System.out.println("Commands:\ngo \"room name\" : go to the room\nlook : displays all neighbor\nadd room \"room name\", \"description\" : adds a new neighbor to player room\nquit : quits");
    }
}
