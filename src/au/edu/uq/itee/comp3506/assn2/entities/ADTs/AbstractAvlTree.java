package au.edu.uq.itee.comp3506.assn2.entities.ADTs;

public interface AbstractAvlTree<E> extends AbstractBinaryTree<E> {

    int getDepth(Node<E> node);


    int getHeight(Node<E> node);


    Node<E> balance(Node<E> node);


    Node<E> rotateLeft(Node<E> root);


    Node<E> rotateRight(Node<E> root);


    int updateHeight(Node<E> node);


    Node<E> insert(Node<E> node);


}
