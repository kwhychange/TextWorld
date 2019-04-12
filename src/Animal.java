public abstract class Animal {
    protected Graph.Node currentRoom;
    protected String name;

    public Graph.Node getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Graph.Node currentRoom) {
        this.currentRoom = currentRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Animal(String name, Graph.Node currentRoom) {
        this.name = name;
        this.currentRoom = currentRoom;
    }

    public abstract void move();
}
