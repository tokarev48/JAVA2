import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1: Add to end");
            System.out.println("2: Add at index");
            System.out.println("3: Remove at index");
            System.out.println("4: Find at index");
            System.out.println("5: Print list");
            System.out.println("6: Exit");

            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter element to add: ");
                    scanner.nextLine();  // consume newline left-over
                    String element = scanner.nextLine();
                    list.add(element);
                    break;
                case 2:
                    System.out.print("Enter index: ");
                    int index = scanner.nextInt();
                    System.out.print("Enter element to add: ");
                    scanner.nextLine();  // consume newline left-over
                    String elem = scanner.nextLine();
                    if (index > list.size()) {
                        list.add(elem);
                    } else {
                        list.add(index, elem);
                    }
                    break;
                case 3:
                    System.out.print("Enter index: ");
                    int removeIndex = scanner.nextInt();
                    if (removeIndex >= list.size() || removeIndex < 0) {
                        System.out.println("Index is out of bounds");
                    } else {
                        list.remove(removeIndex);
                    }
                    break;
                case 4:
                    System.out.print("Enter index: ");
                    int findIndex = scanner.nextInt();
                    if (findIndex >= list.size() || findIndex < 0) {
                        System.out.println("Index is out of bounds");
                    } else {
                        String foundElement = list.get(findIndex);
                        System.out.println("Element at index " + findIndex + ": " + foundElement);
                    }
                    break;
                case 5:
                    System.out.println(list.toString());
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static class SinglyLinkedList<T> {

        private Node<T> head;
        private int size;

        public SinglyLinkedList() {
            head = null;
            size = 0;
        }

        private static class Node<T> {
            private T data;
            private Node<T> next;

            public Node(T data, Node<T> next) {
                this.data = data;
                this.next = next;
            }
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void add(T element) {
            if (head == null) {
                head = new Node<>(element, null);
            } else {
                Node<T> currentNode = head;
                while (currentNode.next != null) {
                    currentNode = currentNode.next;
                }
                currentNode.next = new Node<>(element, null);
            }
            size++;
        }

        public void add(int index, T element) {
            if (index >            size || index < 0) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
            }
            if (index == 0) {
                Node<T> newNode = new Node<>(element, head);
                head = newNode;
            } else {
                Node<T> currentNode = head;
                for (int i = 0; i < index - 1; i++) {
                    currentNode = currentNode.next;
                }
                Node<T> newNode = new Node<>(element, currentNode.next);
                currentNode.next = newNode;
            }
            size++;
        }

        public T remove(int index) {
            if (index >= size || index < 0) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
            }
            Node<T> currentNode = head;
            if (index == 0) {
                head = currentNode.next;
                size--;
                return currentNode.data;
            } else {
                for (int i = 0; i < index - 1; i++) {
                    currentNode = currentNode.next;
                }
                T removedData = currentNode.next.data;
                currentNode.next = currentNode.next.next;
                size--;
                return removedData;
            }
        }

        public T get(int index) {
            if (index >= size || index < 0) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
            }
            Node<T> currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
            return currentNode.data;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node<T> current = head;
            while (current != null) {
                sb.append(current.data).append(" -> ");
                current = current.next;
            }
            sb.append("null");  // end of the list
            return sb.toString();
        }
    }
}