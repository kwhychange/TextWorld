import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static HashMap<String, command> commands = new HashMap<>();
    public static Graph g = new Graph();
    public static Player player;

    public static void main(String[] args) {
        addNodes();
        player = new Player("dora", "dora the explorer", g.getNode("hall"));
        addCommands();
        addItems();
        addAnimals();

        String response = "";
        Scanner input = new Scanner(System.in);

        displayCommands();

        do {
            System.out.println("you are currently in the " + player.getCurrentRoom().getName());
            System.out.print("what do you want to do? > ");
            response = input.nextLine();
            System.out.println("---");

            command command = parseCommand(response);
            command.execute();
        } while (!response.equals("quit"));
    }

    private static void displayCommands() {
        System.out.println("Commands:\ngo \"room name\" : go to the room\nlook : displays all neighbor\ncheck inventory : display inventory items\nadd" +
                " \"room name\", \"description\" : adds a new neighbor to player room\n" +
                "take \"item name\" : take an item from the room\ndrop \"item name\" : drop an item to the room\nquit/suicide : quits/die");
    }

    private static void addCommands() {
        commands.put("go", new goCommand(g, player));
        commands.put("take", new takeCommand(player));
        commands.put("look", new lookCommand(player));
        commands.put("drop", new dropCommand(player));
        commands.put("quit", new exitCommand());
        commands.put("suicide", new suicideCommand());
        commands.put("check", new inventory(player));
        commands.put("add", new addRoomCommand(g, player));
    }

    private static command parseCommand(String response) {
        String commandWord = getFirstWord(response);
        command newCommand = commands.get(commandWord);
        if (newCommand == null) return new errorCommand();
        newCommand.init(response);
        return newCommand;
    }

    private static String getFirstWord(String response) {
        if(!response.contains(" ")) {
            return response;
        } else{
            return response.substring(0, response.indexOf(" "));
        }
    }

    private static void addAnimals() {
        g.addAnimal(new chicken("cuckoo", g.getNode("hall")));
        g.addAnimal(new popstar("Oz", g.getNode("classroom"), player));
        g.addAnimal(new wumpus("nabbit", g.getNode("classroom"), player));
    }

    private static void addItems() {
        g.getNode("closet").addItem("suit", "good looking suit");
        g.getNode("hall").addItem("knife", "sharp, can use to stab");
        g.getNode("dungeon").addItem("dragonball", "balls that makes your dream come true");
    }

    private static void addNodes() {
        g.addNode("hall", "long shallow hallway");
        g.addNode("dungeon", "dark scary underworld");
        g.addNode("closet", "closet full of comics");
        g.addNode("sofa", "relaxing place");
        g.addNode("classroom", "Mr.D's classroom");
        g.addUndirectedEdge("sofa", "classroom");
        g.addUndirectedEdge("closet", "sofa");
        g.addDirectedEdge("hall", "dungeon");
        g.addUndirectedEdge("hall", "closet");
    }
}

//            if (response.indexOf("go") >= 0 && !(player.getCurrentRoom().getNeighbor(response.substring(3)) == null)) { // go room
//                moveAnimals(g, player.getCurrentRoom());
//                player.setCurrentRoom(player.getCurrentRoom().getNeighbor(response.substring(3)));
//            } else if (response.indexOf("look") >= 0) { // look display neighbors
//                System.out.println("you can go to: " + player.getCurrentRoom().getNeighborNames());
//                System.out.println("there are these items in the room: " + player.getCurrentRoom().getItemNames());
//                System.out.println("animals in the room: " + player.getCurrentRoom().getAnimalNames());
//            } else if (response.indexOf("add room") >= 0 && (player.getCurrentRoom().getNeighbor(response.substring(8)) == null)) {
//                try {
//                    String[] split = response.split(",");
//                    g.addNode(split[0].substring(9), split[1]);
//                    g.addDirectedEdge(player.getCurrentRoom().getName(), split[0].substring(9));
//                } catch (Exception e) {
//                    System.out.println("error, follow the directions plaase, retry again");
//                }
//            } else if (response.indexOf("check inventory") >= 0) {
//                System.out.println("inventory: " + player.getInventoryItemNames());
//            } else if (response.indexOf("take") >= 0 && !(player.getCurrentRoom().getItem(response.substring(5)) == null)) {
//                System.out.println("took " + player.getCurrentRoom().getItem(response.substring(5)).getName() + " into inventory");
//                player.addItem(player.getCurrentRoom().removeItem(response.substring(5)));
//            } else if (response.indexOf("drop") >= 0 && !(player.getItem(response.substring(5)) == null)) {
//                System.out.println("dropped " + player.getItem(response.substring(5)).getName() + " to " + player.getCurrentRoom().getName());
//                player.getCurrentRoom().addItem(player.removeItem(response.substring(5)));
//            } else if (response.equals("quit") || response.equals("suicide")) {
//                System.out.println("game over/ you died");
//                break;
//            } else {
//                System.out.println("error; try again");
//                System.out.println("Commands:\ngo \"room name\" : go to the room\nlook : displays all neighbor\nadd room \"room name\", \"description\" : adds a new neighbor to player room\nquit : quits");
//            }