public class takeCommand implements command {
    private String itemName;
    private Player player;

    public takeCommand(Player player) {
        this.player = player;
    }

    public void init(String response) {
        itemName = response.substring(5);
    }

    public void execute() {
        if (!(player.getCurrentRoom().getItem(itemName) == null)) {
            System.out.println("took " + player.getCurrentRoom().getItem(itemName).getName() + " into inventory");
            player.addItem(player.getCurrentRoom().removeItem(itemName));
        } else {
            System.out.println("error; item does not exist");
        }
    }
}
