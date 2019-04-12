import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Graph g = new Graph();
        g.addNode("hall", "long shallow hallway");
        g.getNode("hall").addItem("knife", "sharp, can use to stab");
        g.addNode("closet", "closet full of comics");
        g.addNode("sofa", "relaxing place");
        g.addNode("classroom", "Mr.D's classroom");

        g.getNode("closet").addItem("suit", "good looking suit");
        g.addNode("dungeon", "dark scary underworld");
        g.getNode("dungeon").addItem("dragonball", "balls that makes your dream come true");

        g.addAnimal(new chicken("cuckoo", g.getNode("hall")));
        g.addAnimal(new popstar("Oz", g.getNode("classroom")));
        g.addAnimal(new wumpus("nabbit", g.getNode("classroom")));

        g.addUndirectedEdge("sofa", "classroom");
        g.addUndirectedEdge("closet", "sofa");
        g.addDirectedEdge("hall", "dungeon");
        g.addUndirectedEdge("hall", "closet");

        Player player = new Player("dora", "dora the explorer", g.getNode("hall"));

        String response = "";
        Scanner input = new Scanner(System.in);

        System.out.println("Commands:\ngo \"room name\" : go to the room\nlook : displays all neighbor\ncheck inventory : display inventory items\nadd room \"room name\", \"description\" : adds a new neighbor to player room\n" +
                "take \"item name\" : take an item from the room\ndrop \"item name\" : drop an item to the room\nquit/suicide : quits/die");
        do {
            System.out.println("you are currently in the " + player.getCurrentRoom().getName());
            System.out.print("what do you want to do? > ");
            response = input.nextLine();
            System.out.println("---");

            if (response.indexOf("go") >= 0 && !(player.getCurrentRoom().getNeighbor(response.substring(3)) == null)) { // go room
                moveAnimals(g, player.getCurrentRoom());
                player.setCurrentRoom(player.getCurrentRoom().getNeighbor(response.substring(3)));
            } else if (response.indexOf("look") >= 0) { // look display neighbors
                System.out.println("you can go to: " + player.getCurrentRoom().getNeighborNames());
                System.out.println("there are these items in the room: " + player.getCurrentRoom().getItemNames());
                System.out.println("animals in the room: " + player.getCurrentRoom().getAnimalNames());
            } else if (response.indexOf("add room") >= 0 && (player.getCurrentRoom().getNeighbor(response.substring(8)) == null)) {
                try {
                    String[] split = response.split(",");
                    g.addNode(split[0].substring(9), split[1].trim());
                    g.addDirectedEdge(player.getCurrentRoom().getName(), split[0].substring(9));
                } catch (Exception e) {
                    System.out.println("error, follow the directions plaase, retry again");
                }
            } else if (response.indexOf("check inventory") >= 0) {
                System.out.println("inventory: " + player.getInventoryItemNames());
            } else if (response.indexOf("take") >= 0 && !(player.getCurrentRoom().getItem(response.substring(5)) == null)) {
                System.out.println("took " + player.getCurrentRoom().getItem(response.substring(5)).getName() + " into inventory");
                player.addItem(player.getCurrentRoom().removeItem(response.substring(5)));
            } else if (response.indexOf("drop") >= 0 && !(player.getItem(response.substring(5)) == null)) {
                System.out.println("dropped " + player.getItem(response.substring(5)).getName() + " to " + player.getCurrentRoom().getName());
                player.getCurrentRoom().addItem(player.removeItem(response.substring(5)));
            } else if (response.equals("quit") || response.equals("suicide")) {
                System.out.println("game over/ you died");
                break;
            } else {
                System.out.println("error; try again");
                System.out.println("Commands:\ngo \"room name\" : go to the room\nlook : displays all neighbor\nadd room \"room name\", \"description\" : adds a new neighbor to player room\nquit : quits");
            }
        } while (!response.equals("quit") || !response.equals("suicide"));
    }

    private static void moveAnimals(Graph graph, Graph.Node playerRoom) {
        for (Animal animal : graph.getAnimals()) {
            if (animal instanceof chicken) ((chicken) animal).move();
            if (animal instanceof wumpus) ((wumpus) animal).move(playerRoom);
            if (animal instanceof popstar) ((popstar) animal).move(playerRoom);
        }
    }
}
