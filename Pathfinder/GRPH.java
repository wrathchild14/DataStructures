public class GRPH {
    int count = 0;
    int verticesCount;

    int[][] graph;
    int edges;

    public GRPH(int verticesCount) {
        this.verticesCount = verticesCount;
        this.graph = new int[verticesCount * (verticesCount - 1) / 2][3];
    }

    public void addEdge(int from, int to, int cost) {
        graph[count] = new int[]{from, to, cost};
        count++;
        edges++;
    }

    public void printShortestDistsFrom(int from) {
        int[] dis = new int[verticesCount];
        for (int i = 0; i < verticesCount; i++)
            dis[i] = Integer.MAX_VALUE;

        dis[from] = 0;
        for (int i = 0; i < verticesCount - 1; i++) {
            for (int j = 0; j < edges; j++) {
                if (dis[graph[j][0]] != Integer.MAX_VALUE && dis[graph[j][0]] + graph[j][2] < dis[graph[j][1]])
                    dis[graph[j][1]] = dis[graph[j][0]] + graph[j][2];
            }
        }

        System.out.println("V .. Cena");
        for (int i = 0; i < verticesCount; i++)
            if (dis[i] == Integer.MAX_VALUE)
                System.out.println(i + " .. None");
            else
                System.out.println(i + " .. " + dis[i]);
    }

    public static void main(String[] args) {
        int cells[][] = new int[][] {
                { 0, 1, 0, 0, 0 },
                { 0, 0, 0, 1, 0 },
                { 0, 1, 0, 1, 0 },
                { 0, 1, 0, 1, 0 },
                { 0, 0, 0, 0, 0 }
        };

        LBR l = new LBR(cells);
        l.printPath(15, 1);
    }
}
