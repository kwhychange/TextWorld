public class addRoomCommand implements command {
    private Graph graph;
    private Player player;
    private String room, description;

    public addRoomCommand(Graph graph, Player player) {
        this.graph = graph;
        this.player = player;
    }

    @Override
    public void init(String response) {
        try {
            String[] split = response.split(",");
            room = split[0].substring(4);
            description = split[1].substring(1);
        } catch (Exception e) {
            System.out.println("error, follow the directions please");
        }
    }

    @Override
    public void execute() {
        if(description != null && room != null && player.getCurrentRoom().getNeighbor(room) == null) {
            try {
                graph.addNode(room, description);
                graph.addDirectedEdge(player.getCurrentRoom().getName(), room);
            } catch (Exception e) {
                System.out.println("error, follow the directions plaase");
                graph.removeNode(room);
            }
        }
    }
}
