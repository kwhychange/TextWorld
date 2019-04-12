public class goCommand implements command {
    private Graph graph;
    private Player player;
    private String goToRoom;

    public goCommand(Graph graph, Player player) {
        this.graph = graph;
        this.player = player;
    }

    @Override
    public void init(String response) {
        goToRoom = response.substring(3);
    }

    @Override
    public void execute() {
        if (!(player.getCurrentRoom().getNeighbor(goToRoom) == null)) {
            moveAnimals(graph);
            player.setCurrentRoom(player.getCurrentRoom().getNeighbor(goToRoom));
        } else {
            System.out.println("error; try again");
        }
    }

    private static void moveAnimals(Graph graph) {
        for (Animal animal : graph.getAnimals()) {
            animal.move();
        }
    }
}

//            if (animal instanceof chicken) ((chicken) animal).move();
//            if (animal instanceof wumpus) ((wumpus) animal).move(playerRoom);
//            if (animal instanceof popstar) ((popstar) animal).move(playerRoom);