import java.util.*;

public class LBR {
    int[][] cells;
    ArrayList<Node> path = new ArrayList<Node>();
    private boolean empty = false;

    public LBR(int[][] cells) {
        this.cells = cells;
    }

    static class Node {
        int x, y, dist;

        Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    private ArrayList<Node> reverseArrayList(ArrayList<Node> alist) {
        ArrayList<Node> revArrayList = new ArrayList<Node>();
        for (int i = alist.size() - 1; i >= 0; i--) {
            revArrayList.add(alist.get(i));
        }
        return revArrayList;
    }

    private int getPos(Node node) {
        int counter = 1;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (i == node.x && j == node.y)
                    return counter;
                counter++;
            }
        }
        return -1;
    }

    public void printPath(int from, int to) {
        Node src = null, dest = null;
        int counter = 1;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (from == counter) {
                    dest = new Node(i, j, 0);
//                    System.out.println("My beginning point is " + dest.x + ":" + dest.y);
                }
                if (to == counter) {
                    src = new Node(i, j, 0);
                }
                counter++;
            }
        }

        if (dest != null && src != null) {
            BFS(src.x, src.y, dest.x, dest.y);
//            compute(src, dest);
            if (!empty) {
                path = reverseArrayList(path);
                for (Node node : path) {
//                    System.out.println(node.x * cells.length + node.y + 1);
                    System.out.println(getPos(node));
                }
//                System.out.println(src.x * cells.length + src.y + 1);
                System.out.println(getPos(src));
            }
        }
    }

    private static final int[] row = {-1, 0, 0, 1};
    private static final int[] col = {0, -1, 1, 0};

    private boolean isValid(boolean[][] visited, int row, int col) {
        if ((row >= 0) && (row < cells[0].length) && (col >= 0) && (col < cells.length) && cells[row][col] == 0 && !visited[row][col]) {
            return true;
        }
        return false;
    }

    private void BFS(int i, int j, int x, int y) {
        int oldDist = 0;
        boolean[][] visited = new boolean[cells[0].length][cells.length];

        Queue<Node> q = new ArrayDeque<>();

        visited[i][j] = true;
        q.add(new Node(i, j, 0));

        int min_dist = Integer.MAX_VALUE;

        // loop till queue is empty
        while (!q.isEmpty()) {
            Node node = q.poll();

            i = node.x;
            j = node.y;
            int dist = node.dist;

            if (i == x && j == y) {
                min_dist = dist;
                break;
            }

            for (int k = 0; k < 4; k++) {
                if (isValid(visited, i + row[k], j + col[k])) {
                    visited[i + row[k]][j + col[k]] = true;

                    Node newNode = new Node(i + row[k], j + col[k], dist + 1);
//                    System.out.println(newNode.x + ":" + newNode.y + " " + newNode.dist);
                    q.add(newNode);
                    if (oldDist != newNode.dist) {
                        path.add(newNode);
                        oldDist = newNode.dist;
                    }
                }
            }
        }

        if (min_dist != Integer.MAX_VALUE) {
            System.out.println("Length " + min_dist + ":");
        } else {
            empty = true;
            System.out.println("None");
        }
    }
}
