package au.edu.uq.itee.comp3506.assn2.entities.ADTs;

public class SinglyLinkedList<T> implements AbstractLinkedList<T> {

    private Node<T> cursor;
    private Node<T> first;
    private Node<T> last;

    public int length = 0;


    public SinglyLinkedList() {
        cursor = new Node<T>();
    }

    public SinglyLinkedList(T element) {
        Node<T> n = new Node<>();
        n.setElement(element);
        cursor = n;
    }


    @Override
    public boolean hasNext() {
        return cursor.getNext() != null;
    }

    @Override
    public T getFirst() {
        cursor = first;
        return first == null ? null : first.getElement();
    }

    @Override
    public T getLast() {
        cursor = last;
        return last.getElement();
    }


    @Override
    public boolean isLast() {
        return cursor == last;
    }
    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * Runtime: O(1)
     *
     * @return Next item in the list.
     */
    @Override
    public T getNext() {
        if (this.isEmpty()) {
            return null;
        } else if (isLast()) {
            /* Return null item if already at the tail. */
            return null;
        }
        /* Return next item in chain. */
        cursor = cursor.getNext();
        return cursor.getElement();
    }


    /**
     * Runtime: O(1)
     *
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void remove() throws IndexOutOfBoundsException {
        /* If last item in list, clear head, tail and cursor. */
        if (length == 1) { // If only one in list.
            Node<T> n = new Node<>();
            this.first = n;
            this.last = n;
            this.cursor = n;
        } else if (this.isEmpty()) {
            throw new IndexOutOfBoundsException();
        } else if (this.cursor == this.first) {
            /* Connect head pointer to next item. */
            this.first = this.cursor.getNext();
            this.first.setBefore(null);
            this.cursor = this.first;
        } else if (this.cursor == this.last) {
            /* Connect tail pointer to previous item. */
            this.last = cursor.getBefore();
            this.last.setNext(null);
            this.cursor = this.last;
        } else {
            /* Connect items either side of cursor to one another. */
            this.cursor.getBefore().setNext(this.cursor.getNext());
            this.cursor.getNext().setBefore(this.cursor.getBefore());
            this.cursor = this.cursor.getNext();
        }
        this.length--;
    }


    @Override
    public void addToEnd(T element) {
        Node<T> n = new Node<>();
        n.setElement(element);
        cursor = n;
        if (!isEmpty()) {
            last.setNext(n);
            n.setBefore(last);
        } else {
            first = n;
            last = n;
        }
        last = n;
        length++;
    }


    /**
     * Returns the element of the given location.
     *
     * @return
     *      Element located at the curernt location.
     */
    @Override
    public T getElement() {
        return cursor.getElement();
    }
}
