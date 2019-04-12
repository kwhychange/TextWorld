public class lookCommand implements command {
    private Player player;

    public lookCommand(Player player) {
        this.player = player;
    }

    @Override
    public void init(String response) {

    }

    @Override
    public void execute() {
        System.out.println("you can go to: " + player.getCurrentRoom().getNeighborNames());
        System.out.println("there are these items in the room: " + player.getCurrentRoom().getItemNames());
        System.out.println("animals in the room: " + player.getCurrentRoom().getAnimalNames());
    }
}
