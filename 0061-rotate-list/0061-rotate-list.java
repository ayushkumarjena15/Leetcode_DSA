class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        // Step 1: Find length
        ListNode curr = head;
        int len = 1;
        while (curr.next != null) {
            curr = curr.next;
            len++;
        }

        // Step 2: Adjust k
        k = k % len;
        if (k == 0) return head;

        // Step 3: Make circular
        curr.next = head;

        // Step 4: Find new tail
        int steps = len - k;
        ListNode newTail = head;
        for (int i = 1; i < steps; i++) {
            newTail = newTail.next;
        }

        // Step 5: Break circle
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}