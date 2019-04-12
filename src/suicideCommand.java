public class suicideCommand implements command {
    @Override
    public void init(String response) {

    }

    public suicideCommand() {

    }

    @Override
    public void execute() {
        System.out.println("you tried to die a horrible death but were revived to suffer more");
    }
}
