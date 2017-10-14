package au.edu.uq.itee.comp3506.assn2.entities.ADTs;



public interface AbstractLinkedList<T> {

    /**
     * Return true if more items in list.
     * @return
     *      True if next item is available.
     */
    boolean hasNext();


    /**
     * Moves to the first item in the list.
     *
     * @return
     *      First element in the list.
     */
    T getFirst();


    /**
     * Moves to the last item in the list.
     *
     * @return
     *      Last element in the list.
     */
    T getLast();


    /**
     * Checks to see whether the cursor is currently looking
     * at the last item in the list.
     *
     * @return
     *      True if the item is at the end of the list.
     */
    boolean isLast();

    /**
     * Checks if the list is empty.
     *
     * @return
     *      True if no items are in the list.
     */
    boolean isEmpty();


    /**
     * Moves to the next item in the list.
     *
     * @return
     *      Returns the next item in the list.
     *      Null If already the end of the list.
     */
    T getNext();


    /**
     * Remove item from the list.
     *
     */
    void remove();


    /**
     * Adds element to the end of the list.
     */
    void addToEnd(T element);


    /**
     * Returns the element of the given location.
     *
     * @return
     *      Element located at the curernt location.
     */
    T getElement();


    /**
     * Node structure holding next and before nodes and element to store.
     * @param <T>
     *      Type of object to store.
     */
    class Node<T> {

        private T element = null;
        private Node<T> next = null;
        private Node<T> before = null;

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public void setBefore(Node<T> before) {
            this.before = before;
        }

        /**
         * Returns the element saved.
         *
         * @return
         *      Element stored.
         */
        T getElement() {
            return element;
        }


        /**
         * Changes element stored in the current node.
         *
         * @param element
         *      Element to change node value to.
         */
        void setElement(T element) {
            this.element = element;
        }


        Node<T> getNext() {
            return next;
        }


        Node<T> getBefore() {
            return before;
        }
    }
}
