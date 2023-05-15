import java.util.function.Predicate;

public class ILinkedList implements IList {
    private Node head;
    private int size;

    private class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }


    public void add(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
        }
        size++;
    }


    public void add(int idx, int value) {
        if (idx < 0 || idx > size) {
            throw new IndexOutOfBoundsException("Элемент не найден:");
        }
        Node newNode = new Node(value);
        if (idx == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node beforeNode = head;
            for (int i = 0; i < idx; i++) {
                beforeNode = beforeNode.next;
            }
            newNode.next = beforeNode.next;
            beforeNode.next = newNode;
        }
        size++;
    }


    public void remove(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IndexOutOfBoundsException("Элемент не найден:");
        }
        if (idx == 0) {
            head = head.next;
        } else {
            Node beforeNode = head;
            for (int i = 0; i < idx - 1; i++) {
                beforeNode = beforeNode.next;
            }
            beforeNode.next = beforeNode.next.next;
        }
        size--;
    }


    public void remove(Predicate<Integer> predicate) {
        Node currentNode = head;
        Node prevNode = null;

        while (currentNode != null) {
            if (predicate.test(currentNode.value)) {
                if (prevNode != null) {
                    prevNode.next = currentNode.next;
                } else {
                    head = currentNode.next;
                }
                size--;
            } else {
                prevNode = currentNode;
            }
            currentNode = currentNode.next;
        }
    }


    public int get(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IndexOutOfBoundsException("Элемент не найден:");
        }
        Node currentNode = head;
        for (int i = 0; i < idx; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.value;
    }


    public int size() {
        return size();
    }

}



