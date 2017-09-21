package ru.job4j.collectionsPro;

/**
 * Created by pacman on 21.09.17.
 * Contains auxiliary methods.
 */
public class ListService {

    /**
     * Determines the cycle of the linked Nodes.
     *
     * @param node - a node.
     * @return true - if has cycle.
     */
    public boolean hasCycle(Node node) {
        Node current = node;
        boolean result = false;
        while (current.next != null) {
            if (!node.equals(current.next)) {
                current = current.next;
            } else {
                result = true;
                break;
            }
        }
        return result;
    }
}
