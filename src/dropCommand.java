public class dropCommand implements command {
    private Player player;
    private String itemName;

    public dropCommand(Player player) {
        this.player = player;
    }

    @Override
    public void init(String response) {
        itemName = response.substring(5);
    }

    @Override
    public void execute() {
        if (!(player.getItem(itemName) == null)) {
            System.out.println("dropped " + player.getItem(itemName).getName() + " to " + player.getCurrentRoom().getName());
            player.getCurrentRoom().addItem(player.removeItem(itemName));
        } else {
            System.out.println("error; item does not exist");
        }
    }
}
