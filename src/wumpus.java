import java.util.ArrayList;
import java.util.List;

public class wumpus extends Animal {
    private Player player;
    public wumpus(String name, Graph.Node currentRoom, Player player) {
        super(name, currentRoom);
        this.player = player;
    }

    public void move() {
        List<Graph.Node> neighbors = currentRoom.getNeighbors();
        List<Graph.Node> pNeighbors = player.getCurrentRoom().getNeighbors();
        List<Graph.Node> notAdjacent = findNoneAdjacentRoom(neighbors, pNeighbors, player.getCurrentRoom());
        int size = notAdjacent.size();
        int random = (int) (size * Math.random());
        if (neighbors.size() > 0) {
            Graph.Node n = notAdjacent.get(random);
            System.out.println(getName() + " moving from " + currentRoom.getName() + " to " + n.getName());
            n.addAnimal(currentRoom.removeAnimal(getName()));
            setCurrentRoom(n);
        }

    }

    private ArrayList<Graph.Node> findNoneAdjacentRoom(List<Graph.Node> neighbors, List<Graph.Node> pNeighbors, Graph.Node playerRoom) {
        ArrayList<Graph.Node> noAdjacent = new ArrayList<>();
        for (int i = 0; i < neighbors.size(); i++) {
            for (int j = 0; j < pNeighbors.size(); j++) {
                if (!neighbors.get(i).equals(pNeighbors.get(j)) && !neighbors.get(i).equals(playerRoom)) {
                    noAdjacent.add(neighbors.get(i));
                }
            }
        }
        return noAdjacent;
    }
}
