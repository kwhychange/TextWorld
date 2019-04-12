import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Graph {
    //    private List<Node> nodes;
    private HashMap<String, Node> nodes;
    private ArrayList<Animal> animals;

    public Graph() {
        nodes = new HashMap<>();
        animals = new ArrayList<>();
    }

    public void addAnimal(Animal animal){
        animal.getCurrentRoom().addAnimal(animal);
        animals.add(animal);
    }

    public void addNode(String name, String description) {
        nodes.put(name, (new Node(name, description)));
    }

    public void addDirectedEdge(String name1, String name2) {
        Node n1 = getNode(name1);
        Node n2 = getNode(name2);
        n1.addNeighbor(n2);
    }

    public void addUndirectedEdge(String name1, String name2) {
        Node n1 = getNode(name1);
        Node n2 = getNode(name2);
        n1.addNeighbor(n2);
        n2.addNeighbor(n1);
    }

    public Set<String> getNodes(){
        return nodes.keySet();
    }

    public Node getNode(String name) {
        return nodes.get(name);
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public void removeNode(String room) {
        nodes.remove(room);
    }

    public class Node {
        private String name;
        private HashMap<String, Node> neighbors;
        private ArrayList<Animal> animals;
        private String description;
        private ArrayList<Item> items;

        private Node(String name, String description) {
            this.animals = new ArrayList<>();
            this.neighbors = new HashMap<>();
            this.items = new ArrayList<>();
            this.name = name;
            this.description = description;
        }

        public void addAnimal(Animal animal) {
            animals.add(animal);
        }

        public Animal getAnimal(String name) {
            for (Animal animal : animals) {
                if (animal.getName().equals(name)) {
                    return animal;
                }
            }
            return null;
        }

        public Animal removeAnimal(String name) {
            for (int i = 0; i < animals.size(); i++) {
                if (animals.get(i).getName().equals(name)) {
                    return animals.remove(i);
                }
            }
            return null;
        }

        public ArrayList<Item> getItems() {
            return items;
        }

        public Item removeItem(String name) {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getName().equals(name)) {
                    return items.remove(i);
                }
            }
            return null;
        }

        public void addItem(Item item) {
            items.add(item);
        }

        public Item getItem(String name) {
            for (Item item : items) {
                if (item.getName().equals(name)) {
                    return item;
                }
            }
            return null;
        }

        public void addItem(String name, String description) {
            items.add(new Item(name, description));
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getName() {
            return name;
        }

        private void addNeighbor(Node n) {
            this.neighbors.put(n.getName(), n);
        }

        public String getAnimalNames() {
            String animalNames = "";
            for (Animal animal : animals) {
                animalNames += "\n" + animal.getName();
            }
            return animalNames;
        }

        public String getNeighborNames() {
            String neighborNames = "";
            for (String node : neighbors.keySet()) {
                neighborNames += "\n" + node + ": " + neighbors.get(node).getDescription();
            }
            return neighborNames;
        }

        public String getItemNames() {
            String itemNames = "";
            for (Item item : getItems()) {
                itemNames += "\n" + item.getName() + ": " + item.getDescription();
            }
            return itemNames;
        }

        public ArrayList<Node> getNeighbors() {
            ArrayList<Node> nodes = new ArrayList<>();
            for (String node : neighbors.keySet()) {
                nodes.add(neighbors.get(node));
            }
            return nodes;
        }

        public Node getNeighbor(String name) {
            return neighbors.get(name);
        }

        public ArrayList<Animal> getAnimals() {
            return animals;
        }
    }
}