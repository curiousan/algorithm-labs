/*'
    Linked list implementation
 */
public class List {
    private Node head;
    private int counter;

    List() {
        counter = 0;
    }


    boolean add(Object node, String key) { // returns true if addition is successful

        if (head == null) {
            head = new Node(node, key);
            incrementCounter();
            return true;
        }

        Node temp = new Node(node, key);
        Node current = head;

        if (current != null) {
            while (current.getNext() != null) {
                if (current.getNext().getKey().equals(key)) { // just to make sure there is no duplication
                    return false;
                }
                current = current.getNext();
            }

            current.setNext(temp);
            incrementCounter();
        }

        return true;


    }

    Node get(String key) {

        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                return currentNode;
            }
            currentNode = currentNode.getNext();
        }


        return null;
    }

    boolean delete(String key) {

        if (head != null) {
            Node currentNode = head;

            if (currentNode.getKey().equals(key)) {
                head = currentNode.getNext();
                decrementCounter();
                return true;
            }

            while (currentNode.getNext() != null) {

                if (currentNode.getNext().getKey().equals(key)) {
                    Node temp = currentNode.getNext().getNext();
                    currentNode.setNext(temp);
                    decrementCounter();
                    return true;
                }
            }
        }

        return false;

    }


    private void incrementCounter() {
        counter++;
    }

    private void decrementCounter() {
        counter--;
    }


    int getCounter() {
        return counter;
    }

    Node getHead() {
        return head;
    }

    class Node {                // single node class -> nested inner class for more clarity
        private Object value;
        private Node next;
        private String key;


        Node(Object value, String key) {
            this.value = value;
            this.key = key;


        }


        public Node getNext() {
            return next;
        }

        void setNext(Node next) {
            this.next = next;
        }

        String getKey() {
            return key;
        }

        Object getValue() {
            return value;
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Node current = head;
        while (current != null) {
            str.append(current.getKey() + ", ");
            current = current.getNext();
        }

        return str.toString();
    }
}
