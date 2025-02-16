class Solution {

    /**
     * Check indrgreens for each node.
     * create adjency list.
     * reduce indegrees of each adjency.
     * BFS
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //edge
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        int[] indegrees = new int[numCourses];
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        for (int[] edge : prerequisites) {
            indegrees[edge[0]]++;
            if (!map.containsKey(edge[1])) {
                map.put(edge[1], new ArrayList());
            }
            map.get(edge[1]).add(edge[0]);
        }

        // BFS
        Queue<Integer> q = new LinkedList<>();
        // add all 0 indegree in queue
        int count = 0;
        for (int i = 0; i < indegrees.length; i++) {
            if (indegrees[i] == 0) {
                q.add(i);
                count++;
            }
        }
        if (q.isEmpty()) return false;
        while (!q.isEmpty()) { //O(V+E)
            int curr = q.poll();
            List<Integer> edges = map.get(curr);
            if (edges != null) {
                for (int edge : edges) {
                    indegrees[edge]--;
                    //if independat
                    if (indegrees[edge] == 0) {
                        q.add(edge);
                        count++;
                        if (count == numCourses) return true;
                    }
                }
            }
        }

        return false;
    }
}
