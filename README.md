# linked-lists

The LinkedList class is a singly linked list with reference to the `next` linked list (think of this as a node), and contains a generic type value held as the variable `data`.

## Runtime

Adding and removing elements from the beginning of the list is in constant time.

However, it is not the case when accessing an index within the list. It is necessary to iterate through K elements when getting the Kth element.

To avoid having to iterate through all N elements, or the entire list when appending an element at the end/tail. Having a variable/reference to the tail would facilitate adding/removing the element (at the end) in constant time. However a reference to the tail was not used.

Reversing the LinkedList, is done using two pointers via counters in a nested for-loop. One pointing at the head of the list, and the other pointing at the end. These pointers eventually meet at the same index/position or surpass each other. At this point iteration stops and the list is done being reversed. This each loop has a runtime of O(N/2). Hence the total average runtime for this reverse function is 2 * (N/2) = O(N).