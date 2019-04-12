import java.util.List;

public class popstar extends Animal {
    private Player player;
    public popstar(String name, Graph.Node currentRoom, Player player) {
        super(name, currentRoom);
        this.player = player;
    }

    public void move() {
        List<Graph.Node> neighbors = currentRoom.getNeighbors();
        List<Graph.Node> pNeighbors = player.getCurrentRoom().getNeighbors();
        Graph.Node goTo = findSharesAdjacentRoom(neighbors, pNeighbors);
        Graph.Node nextTo = findAdjacentRoom(neighbors, player.getCurrentRoom());
        if (neighbors.size() > 0) {
            if (goTo != null) {
                System.out.println(getName() + " moving closer from " + currentRoom.getName() + " to " + goTo.getName());
                goTo.addAnimal(currentRoom.removeAnimal(getName()));
                setCurrentRoom(goTo);
            } else if (nextTo != null) {
                System.out.println(getName() + " moving closer from " + currentRoom.getName() + " to " + player.getCurrentRoom().getName());
                player.getCurrentRoom().addAnimal(currentRoom.removeAnimal(getName()));
                setCurrentRoom(player.getCurrentRoom());
            } else {
                int size = currentRoom.getNeighbors().size();
                int random = (int) (size * Math.random());
                Graph.Node n = neighbors.get(random);
                System.out.println(getName() + " moving from " + currentRoom.getName() + " to " + n.getName());
                n.addAnimal(currentRoom.removeAnimal(getName()));
                setCurrentRoom(currentRoom.getNeighbors().get(random));
            }
        }
    }

    private Graph.Node findAdjacentRoom(List<Graph.Node> neighbors, Graph.Node playerRoom) {
        for (int i = 0; i < neighbors.size(); i++) {
            if (neighbors.get(i).equals(playerRoom)) {
                return neighbors.get(i);
            }
        }
        return null;
    }

    private Graph.Node findSharesAdjacentRoom(List<Graph.Node> neighbors, List<Graph.Node> pNeighbors) {
        for (int i = 0; i < neighbors.size(); i++) {
            for (int j = 0; j < pNeighbors.size(); j++) {
                if (neighbors.get(i).equals(pNeighbors.get(j))) {
                    return neighbors.get(i);
                }
            }
        }
        return null;
    }
}
