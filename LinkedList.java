
/**
 * Generic LinkedList for type E - element
 * @param <E> the type of the value stored in the node
 *
 * https://docs.oracle.com/javase/tutorial/java/generics/types.html
 *
 * The tail of very LinkedList points to NULL !
 * e.g. 1,2,3,NULL
 *
 * The starting index of an element in the LinkedList is 0.
 *
 * In essence, this Singly LinkedList class should be called Node.
 *
 */


public class LinkedList<E>{

    private E data;
    private LinkedList<E> next;

    // constructor
    public LinkedList(){

        this.next = null;
        this.data = null;

    }

    // get the current head data
    public E get(){
        return this.data;
    }

    // return the element at the position
    // get position 2 from 1,2,3,4,5: 3
    // get position 5 from 1,2,3,4,5: NULL
    public E get(int position){

        LinkedList<E> curr = this;

        int index = 0;

        while(curr != null ){

            if(index == position){
                return curr.data;
            }

            index++;
            curr = curr.next;
        }

        return null;
    }


    // return a reference to LinkedList from given data (the first encountered match)
    // e.g. 1,2,3,4,5,6,4,6 to get a reference that has data 4, this function returns 4,5,6,4,6
    public LinkedList<E> getFromData(E data){
        LinkedList<E> curr = this;

        while(curr.next != null){

            if(curr.data == data){
                return curr;
            }

            curr = curr.next;
        }
        return null;
    }

    // appends to the end of the linkedlist
    // add 4 to 1,2,3 becomes: 1,2,3,4
    public void add(E e){

        // get reference to the current linkedlist in context
        LinkedList<E> curr = this;


        // keep going until the tail
        while(curr.next != null){

            curr = curr.next;

        }

        LinkedList<E> end = new LinkedList<>();

        // set the new tail
        curr.data = e;
        curr.next = end;

    }

    // add to a given position on the linkedlist
    // add 6 on position 0 to 1,2,3 becomes: 6,1,2,3
    // add 4 on position 1 to 1,2,3 becomes: 1,4,2,3
    //
    // if position is beyond the index at the end e.g. 5 in 1,2,3
    // then append to the end
    public void add(E e, int position){

        LinkedList<E> curr = this;

        // adding to the head/front of list
        if(position == 0){

            E temp = curr.data; // head's data

            E temp2 = null; // another temporary container

            curr.data = e; // becomes the head

            // keep going until the tail
            while(curr.next != null ){

                curr = curr.next;
                temp2 = curr.data;
                curr.data = temp;
                temp = temp2;
            }

            LinkedList<E> end = new LinkedList<E>();

            end.data = temp;
            end.next = null;

            // update the new tail
            curr.next = end;


            return;
        }

        // adding to the middle
        int index = 0;
        LinkedList<E> prev = null;
        LinkedList<E> elem = new LinkedList<E>();

        while(curr.next != null){

            if(index == position){

                // insert the new element
                prev.next = elem;

                elem.data = e;
                elem.next = curr;
                return;

            }

            index++;
            prev = curr;
            curr = curr.next;

        }

        // adding to the end
        // Yes, I can reuse elem. For convention/style sake.
        LinkedList<E> end = new LinkedList<>();

        // set the new tail
        curr.data = e;
        curr.next = end;

    }

    // removes an element at a given position from the linkedlist
    // remove position 4 from 1,2,3,4,5 becomes: 1,2,3,4
    // return 5 (i.e the removed element)
    // if position does not exist then return null
    public E remove(int position){

        LinkedList<E> curr = this;

        // to keep track of position
        int index = 0;

        E result ;

        // at the head
        if(index == position){
            result = curr.data;

            removeFirst(this);
            return result;
        }

        while( curr.next != null ){

            if(index == position -1){

                result = curr.next.data;

                // if position is actually removing a tail's pointed to null element
                // then skip removal
                if(result == null){
                    return null;
                }

                curr.next = curr.next.next;

                return result;
            }
            index++;
            curr = curr.next;
        }

        return null;
    }


    // remove the head or first element in the linkedlist
    public void removeFirst(LinkedList<E> ll){

        LinkedList<E> curr = ll;

        LinkedList<E> prev = curr;

        while( curr.next != null ){

            // copy over the existing data with data from next
            curr.data = curr.next.data;
            prev = curr;
            curr = curr.next;
        }

        // set the last element null since its data has been copied over
        prev.next = curr.next;

    }

    // returns the linkedlist with elements reversed
    // returns the element of the head of the reversed list
    // reverse 1,2,3 becomes: 3,2,1; returns 3
    // The tail still points to NULL

    /**
     * 
     * I would have simply reversed the pointers. But this method would have required a class/struct to represent a Node object.
     *
     * The requirements of this function was to return type E.
     *
     * */
    public E reverse( E head ){

        // get linkedlist reference to head from head's data (i.e. @param head)
        LinkedList<E> headLL = getFromData(head);

        print(headLL);

        // keyword `this` is also the head if headLL happens to be the first element
        LinkedList<E> curr = headLL;
        LinkedList<E> tail = headLL;

        int count = 0;
        int size = 0;

        int j;

        // get size of list
        while(curr.next != null){
            curr = curr.next;
            size++;
        }

        // System.out.println("SIZE = " + size);

        // get the end element data
        curr = headLL;

        for(int i = 0; i < size - 1 ; i++){

            tail = headLL;

            for(j = 0; j < size - count - 1; j++){

                // System.out.println("t = " + tail.data);

                tail = tail.next;


            }

            // to avoid overlapping
            // i.e. to avoid swapping elements that have already been swapped
            if( j == i || i > j){
                break;
            }

            E temp = tail.data;

            // swap
            tail.data = curr.data;
            curr.data = temp;

            curr = curr.next;

            count++;


        }
        System.out.print("reversed: ");
        print(headLL);
        return headLL.data;

    }


    // prints the contents of the linkedlist
    public void print(LinkedList<E> ll){

        LinkedList<E> curr = ll;


        while(curr != null){

            System.out.print(curr.data);

            curr = curr.next;

            if(curr != null){
                System.out.print(" -> ");
            }

        }
        System.out.println();

    }

    public static void main(String[] args) {

        LinkedList<Integer> ll = new LinkedList<Integer>();

        LinkedList<Integer> sl = new LinkedList<Integer>();

        // test: 0 to 9 elements
        for(int i = 0; i < 10; i++){
            ll.add(i);
        }

        ll.print(ll);

        System.out.println( ll.remove(5) ); // 5 (remove middle)
        ll.print(ll);

        System.out.println( ll.remove(10) ); //null (remove non-existent)
        System.out.println( ll.remove(0) ); // 0 (remove front)
        ll.print(ll);

        System.out.println( ll.remove(7) ); // 9 (remove end)
        ll.print(ll);

        System.out.println( ll.remove(7) ); // do nothing (remove null element)
        ll.print(ll);

        ll.add(12,0); // (add front)
        ll.print(ll);

        ll.add(9,1); // (add middle)
        ll.print(ll);

        ll.add(14,10); // append to the end (add at the end)
        ll.print(ll);

        ll.add(14,15); // append to the end (add outside of tail)
        ll.print(ll);
        // 12 -> 9 -> 1 -> 2 -> 3 -> 4 -> 6 -> 7 -> 8 -> 14 -> 14 -> null

        System.out.println(ll.get()); // 12 (get head)

        System.out.println(ll.get(9)); // 14 (get 9th index)

        ll.print(ll.getFromData(4)); //4 -> 6 -> 7 -> 8 -> 14 -> 14 -> null

        // test: 0 to 9 elements
        for(int i = 0; i < 9; i++){
            sl.add(i);
        }
        sl.print(sl);  // 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> null


        System.out.println(sl.reverse(0)); // 8 (once the tail now the head)
        sl.print(sl); // 8 -> 7 -> 6 -> 5 -> 4 -> 3 -> 2 -> 1 -> 0 -> null

        System.out.println("==============================================");

        System.out.println(ll.reverse(4));
        // 4 -> 6 -> 7 -> 8 -> 14 -> 14 -> null
        ll.print(ll);

        // original:
        // 12 -> 9 -> 1 -> 2 -> 3 -> 4 -> 6 -> 7 -> 8 -> 14 -> 14 -> null

        // with reversed segment:
        // 12 -> 9 -> 1 -> 2 -> 3 -> | 14 -> 14 -> 8 -> 7 -> 6 -> 4 -> null |
    }

}