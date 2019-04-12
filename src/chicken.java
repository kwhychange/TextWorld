import java.util.ArrayList;
import java.util.List;

public class chicken extends Animal {
    public chicken(String name, Graph.Node currentRoom) {
        super(name, currentRoom);
    }

    public void move() {
        int size = currentRoom.getNeighbors().size();
        int random = (int) (size * Math.random());
        List<Graph.Node> neighbors = currentRoom.getNeighbors();
        if (neighbors.size() > 0) {
            Graph.Node n = neighbors.get(random);
            System.out.println(getName() + " moving from " + currentRoom.getName() + " to " + n.getName());
            n.addAnimal(currentRoom.removeAnimal(getName()));
            setCurrentRoom(currentRoom.getNeighbors().get(random));
        }
    }
}