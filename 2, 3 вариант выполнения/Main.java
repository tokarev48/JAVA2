import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        JFrame frame = new JFrame("Singly Linked List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);

        JTextField textField = new JTextField();
        JButton addButton = new JButton("Add to end");
        JButton addIndexButton = new JButton("Add at index");
        JButton removeButton = new JButton("Remove at index");
        JButton findButton = new JButton("Find at index");
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        textField.setColumns(10);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                list.add(textField.getText());
                textArea.setText(list.toString());
                textField.setText("");
            }
        });

        addIndexButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = Integer.parseInt(JOptionPane.showInputDialog("Enter index: "));
                if (index > list.size()) {
                    list.add(textField.getText());
                } else {
                    list.add(index, textField.getText());
                }
                textArea.setText(list.toString());
                textField.setText("");
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = Integer.parseInt(JOptionPane.showInputDialog("Enter index: "));
                if (index >= list.size() || index < 0) {
                    JOptionPane.showMessageDialog(frame, "Index is out of bounds");
                } else {
                    list.remove(index);
                    textArea.setText(list.toString());
                }
            }
        });

        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = Integer.parseInt(JOptionPane.showInputDialog("Enter index: "));
                if (index >= list.size() || index < 0) {
                    JOptionPane.showMessageDialog(frame, "Index is out of bounds");
                } else {
                    String element = list.get(index);
                    JOptionPane.showMessageDialog(frame, "Element at index " + index + ": " + element);
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(textField);
        panel.add(addButton);
        panel.add(addIndexButton);
        panel.add(removeButton);
        panel.add(findButton);

        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, scrollPane);

        frame.setVisible(true);
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

