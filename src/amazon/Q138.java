package amazon;
// Copy List with Random Pointer
public class Q138 {

    /*method1 HashMap*/
    //TC & SC : O(n)
//	public RandomListNode copyRandomList(RandomListNode head) {
//		if (head == null)	return null;
//
//		Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
//
//		RandomListNode cur = head;
//        while(cur != null) {
//            map.put(cur, new RandomListNode(cur.label));
//            cur = cur.next;
//        }
//        for (Map.Entry<RandomListNode, RandomListNode> entry : map.entrySet()) {
//            final RandomListNode newNode = entry.getValue();
//            newNode.next = map.get(entry.getKey().next);
//            newNode.random = map.get(entry.getKey().random);
//        }
//
//        return map.get(head);
//    }
    /*method2 similar as method1, more concise*/
//	public RandomListNode copyRandomList(RandomListNode head) {
//		  if (head == null) return null;
//
//		  Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
//
//		  // loop 1. copy all the nodes
//		  RandomListNode node = head;
//		  while (node != null) {
//		    map.put(node, new RandomListNode(node.label));
//		    node = node.next;
//		  }
//
//		  // loop 2. assign next and random pointers
//		  node = head;
//		  while (node != null) {
//		    map.get(node).next = map.get(node.next);
//		    map.get(node).random = map.get(node.random);    // we don't need to check if node.random == null here, because map.get return null if not exist.
//		    node = node.next;
//		  }
//
//		  return map.get(head);
//		}
    /*method3*/
    //TC : O(n) SC : O(1)
    //Step 1: create a new node for each existing node and join them together
    //eg: A->B->C will be A->A'->B->B'->C->C'
    //Step2: copy the random links: for each new node n', n'.random = n.random.next
    //Step3: detach the list: basically n.next = n.next.next; n'.next = n'.next.next
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null)	return null;
        //step 1 copy and linked together
        RandomListNode old = head;
        while(old != null){
            RandomListNode copy = new RandomListNode(old.label);
            RandomListNode temp = old.next;
            old.next = copy;
            copy.next = temp;
            old =  temp;
        }
        //step 2: assign random pointer
        old = head;
        while(old != null){
            RandomListNode n = old.next;
            if(old.random != null)  // 不能少
                n.random = old.random.next;
            else
                n.random = null;
            old = old.next.next;
        }
        //step 3:detach list
        old = head;
        // RandomListNode nhead = old.next;
        // RandomListNode n = old.next;
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode n = dummy;
        while(old != null){
            // old.next = old.next.next;	//注释的跟没注释的是两种正确的方案：
            // if(n.next == null)			//注释的方案中的if判断是针对list中只有一个pointer的情况：
            // break;						//1 -> 1 -> #...
            // n.next = n.next.next;
            // old = old.next;
            // n = n.next;
            RandomListNode temp = old.next.next;
            n.next = old.next;
            n = n.next;
            old.next = temp;
            old = old.next;
        }
        // return nhead;
        return dummy.next;
    }

    class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) { this.label = x; }
    }

//    HashMap<Node, Node> visitedHash = new HashMap<Node, Node>();
//    public Node copyRandomList(Node head) {
//
//        if (head == null) {
//            return null;
//        }
//
//        if (this.visitedHash.containsKey(head)) {
//            return this.visitedHash.get(head);
//        }
//
//        Node node = new Node(head.val, null, null);
//        this.visitedHash.put(head, node);
//
//        node.next = this.copyRandomList(head.next);
//        node.random = this.copyRandomList(head.random);
//
//        return node;
//    }
}
