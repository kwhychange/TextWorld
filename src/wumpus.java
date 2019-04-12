import java.util.ArrayList;
import java.util.List;

public class wumpus extends Animal {
    public wumpus(String name, Graph.Node currentRoom){
        super(name, currentRoom);
    }

    public void move(Graph.Node playerRoom) {
        List<Graph.Node> neighbors = currentRoom.getNeighbors();
        List<Graph.Node> pNeighbors = playerRoom.getNeighbors();
        List<Graph.Node> notAdjacent = findNoneAdjacentRoom(neighbors,pNeighbors,playerRoom);
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
                if(!neighbors.get(i).equals(pNeighbors.get(j)) && !neighbors.get(i).equals(playerRoom)){
                    noAdjacent.add(neighbors.get(i));
                }
            }
        }
        return noAdjacent;
    }
}
