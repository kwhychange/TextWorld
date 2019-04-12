import java.util.ArrayList;

public class Player {
    private String name, description;
    private ArrayList<Item> inventory;
    Graph.Node currentRoom;

    public Player(String name, String description, Graph.Node node) {
        this.name = name;
        this.inventory = new ArrayList<>();
        this.description = description;
        this.currentRoom = node;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public Item removeItem(String name) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName().equals(name)) {
                return inventory.remove(i);
            }
        }
        return null;
    }

    public Item getItem(String name) {
        for (Item item : inventory) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public Graph.Node getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Graph.Node currentRoom) {
        this.currentRoom = currentRoom;
    }

    public String getInventoryItemNames() {
        String itemNames = "";
        for (Item item : getInventory()) {
            itemNames += "\n" + item.getName() + ": " + item.getDescription();
        }
        return itemNames;
    }
}
