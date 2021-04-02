package google;

import common.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

// Merge k Sorted Lists
public class Q23 {

    /*method1 PriorityQueue*/
    //O(Nlgk)		k : num of lists
    //O(k)			N : total number of nodes
	public ListNode mergeKLists1(ListNode[] lists) {
        if(lists == null || lists.length == 0){
            return null;
        }
        // PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, (o1, o2) -> (o1.val - o2.val));
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, new Comparator<ListNode>(){
            @Override
            public int compare(ListNode o1, ListNode o2){
                return o1.val - o2.val;
            }
        });
        for(ListNode list : lists){
            if(list != null){		//注意：需要这个判断条件保证没有null pointer
                pq.offer(list);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while(!pq.isEmpty()){
            cur.next = pq.poll();
            cur = cur.next;
            if(cur.next != null){
                pq.add(cur.next);
            }
        }
        return dummy.next;
    }
    /*method2 merge sort*/
    //O(Nlgk)
    //O(1)
    public ListNode mergeKLists2(ListNode[] lists) {
        if(lists == null || lists.length == 0){
            return null;
        }
        return mergeSort(lists, 0, lists.length - 1);
    }

    private ListNode mergeSort(ListNode[] lists, int l, int r) {
        if(l == r) {
            return lists[l];
        } else if(l > r) {
            return null;
        } else {
            int m = l + (r - l) / 2;
            ListNode l1 = mergeSort(lists, l, m);
            ListNode l2 = mergeSort(lists, m + 1, r);
            return merge(l1, l2);
        }
    }
    private ListNode merge(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }
    /*method3 Merge with Divide And Conquer*/
    //O(Nlgk)
    //O(1)
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0){
            return null;
        }
        int interval = 1;   //注意:interval的使用 & 这个方法也用到了merge method
        while(interval < lists.length) {
            for(int i = 0; i < lists.length - interval; i += 2 * interval) {
                lists[i] = merge(lists[i], lists[i+interval]);
            }
            interval *= 2;
        }
        return lists[0];
    }

}
