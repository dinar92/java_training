package ru.job4j.collectionsPro;

import java.util.LinkedList;

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
        Node outer = node;
        Node inner = node.next;
        boolean result = false;
        LinkedList<Node> list = new LinkedList<>();
        while (inner != null) {
            for (Node nodeInList : list) {
                if (nodeInList.element.equals(inner.element)) {
                    result = true;
                    break;
                }
            }
            if (!result) {
                list.add(outer);
                outer = outer.next;
                inner = inner.next;
            } else {
                break;
            }
        }
        return result;
    }
}
