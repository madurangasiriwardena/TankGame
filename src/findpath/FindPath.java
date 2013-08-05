package findpath;

public class FindPath {
    public static void main(String[] args) {
        GenPath genPath = new GenPath(1, 1, 1);
        genPath.generatePath();
        genPath.printMap();
        genPath.plotPath(8, 9);
    }
}
