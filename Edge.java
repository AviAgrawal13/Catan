public class Edge {
    private Road road = null;
    public Edge() {
    }

    public Road getRoad() {
        return this.road;
    }
    public void addRoad(Road road) {
        this.road = road;
    }
    public boolean hasRoad() { return road != null; }
}
