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
     * @return
     *      Item that has been removed.
     */
    T remove();


    /**
     * Adds element to the end of the list.
     */
    void addToEnd(T element);


    /**
     * Node structure holding next and before nodes and element to store.
     * @param <T>
     *      Type of object to store.
     */
    class Node<T> {

        private T element;
        private Node<T> next;
        private Node<T> before;

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
    }
}
