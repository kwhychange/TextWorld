public class inventory implements command{
    private Player player;

    public inventory(Player player) {
        this.player = player;
    }

    @Override
    public void init(String response) {

    }

    @Override
    public void execute() {
        System.out.println("inventory: " + player.getInventoryItemNames());
    }
}
