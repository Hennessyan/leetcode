package amazon;
// Clone Graph
public class Q133 {
    /* O(V+E) O(V)
    public Node cloneGraph1(Node node) {
        if(node == null) {
            return node;
        }
        Map<Node, Node> visited = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        visited.put(node, new Node(node.val, new ArrayList<>()));
        while(!queue.isEmpty()) {
            Node tmp = queue.poll();
            Node mirror = visited.get(tmp);
            for(Node v : tmp.neighbors) {
                if(!visited.containsKey(v)) {
                    visited.put(v, new Node(v.val, new ArrayList<>()));
                    queue.add(v);
                }
                mirror.neighbors.add(visited.get(v))
            }
        }
        return visited.get(node);
    }

    public Node cloneGraph(Node node) {
        if(node == null) {
            return node;
        }
        return dfs(node, new HashMap<>());
    }
    private Node dfs(Node node, Map<Node, Node> map) {
        if(node == null) {
            return node;
        }
        if(map.containsKey(node)) {
            return map.get(node);
        }
        Node mirror = new Node(node.val, new ArrayList<>());
        map.put(node, mirror);
        for(Node v : node.neighbors) {
            mirror.neighbors.add(dfs(v, map));
        }
        return mirror;
    }
    */
}
