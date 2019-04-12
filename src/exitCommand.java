public class exitCommand implements command{
    @Override
    public void init(String response) {

    }

    public exitCommand() {
    }

    @Override
    public void execute() {
        System.out.println("game over/ you died");
    }
}
