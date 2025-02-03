package il.ac.telhai.ds.linkedlist;

public class DLinkedList<T> implements List<T> {

    // Fields
    private DNode head;
    private DNode tail;
    private DNode cursor;

    private class DNode {
        // Fields
        private T element;
        private DNode next;
        private DNode prev;
        // Constructor
        public DNode(T element) {
            this.element = element;
        }
        // Methods
        public T getElement() {
            return element;
        }
        public void setElement(T element) {
            this.element = element;
        }
        public void setNext(DNode next) { this.next = next; }
        public DNode getNext() { return next; }
        public void setPrev(DNode prev) { this.prev = prev; }
        public DNode getPrev() { return prev; }
    }

    // Constructor
    public DLinkedList() {
        this.head = null;
        this.tail = null;
        this.cursor = null;
    }

    // Methods
    @Override
    public void insert(T newElement) {
        if (newElement == null) {
            throw new NullPointerException("Element can't be null");
        }

        DNode newNode = new DNode(newElement);

        if (isEmpty()) { // Empty list
            head = newNode;
            tail = newNode;
            cursor = newNode;

        } else if (cursor == tail || cursor == null) { // Insert to the end of list
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
            cursor = newNode;

        } else { // Insert after cursor
            DNode nextNode = cursor.getNext();
            cursor.setNext(newNode);
            newNode.setPrev(cursor);
            newNode.setNext(nextNode);
            if (nextNode != null) {
                nextNode.setPrev(newNode);
            }
            cursor = newNode;
        }
    }

    @Override
    public T remove() {
        if (isEmpty()) { // Empty list
            return null;
        }
        if (cursor == null) {
            throw new IllegalStateException("Cursor isn't pointing to any element");
        }

        T removedE = cursor.getElement();

        if (cursor == head) { // Cursor at the head
            head = cursor.getNext();
            if (head != null) {
                head.setPrev(null);
            } else {
                tail = null; // list is empty
            }
            cursor = head;
        } else if (cursor == tail) { // Cursor at the tail
            tail = cursor.getPrev();
            if (tail != null) {
                tail.setNext(null);
            } else {
                head = null; // list is empty
            }
            cursor = null;
        } else {
            DNode prevNode = cursor.getPrev();
            DNode nextNode = cursor.getNext();
            prevNode.setNext(nextNode);
            nextNode.setPrev(prevNode);
            cursor = nextNode;
        }
        return removedE;
    }

    @Override
    public T remove(T element) {
        if (isEmpty()) {
            return null;
        }

        DNode current = head;

        while (current != null) { // Seek the element in the list
            if (current.getElement().equals(element)) {
                T removedE = current.getElement();

                if (current == head) { // Element is at the head
                    head = current.getNext();
                    if (head != null) {
                        head.setPrev(null);
                    } else {
                        tail = null; // List is empty
                    }
                } else if (current == tail) { // Element is at the tail
                    tail = current.getPrev();
                    if (tail != null) {
                        tail.setNext(null);
                    } else {
                        head = null; // List is empty
                    }
                } else { // Element is in the middle
                    DNode prevNode = current.getPrev();
                    DNode nextNode = current.getNext();
                    prevNode.setNext(nextNode);
                    nextNode.setPrev(prevNode);
                }
                cursor = head;
                return removedE;
            }
            current = current.getNext();
        }
        return null; // Element not found
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        cursor = null;
    }

    @Override
    public void replace(T newElement) {
        if (isEmpty()) {
            throw new NullPointerException("List is empty");
        }
        if (cursor == null) {
            throw new IllegalStateException("Cursor isn't pointing to any element");
        }
        if (newElement == null) {
            throw new NullPointerException("New element can't be null");
        }
        cursor.setElement(newElement);
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean goToBeginning() {
        if (isEmpty()) {
            cursor = null;
            return false;
        } else {
            cursor = head;
            return true;
        }
    }

    @Override
    public boolean goToEnd() {
        if (!isEmpty()) {
            cursor = tail;
            return true;
        }
        cursor = null;
        return false;
    }

    @Override
    public T getNext() {
        if (cursor == null || cursor == tail) {
            return null;
        }
        cursor = cursor.getNext();
        return cursor.getElement();
    }

    @Override
    public T getPrev() {
        if (cursor == null || cursor == head) {
            return null;
        } else {
            cursor = cursor.getPrev();
            return cursor.getElement();
        }
    }

    @Override
    public T getCursor() {
        return (isEmpty()) ? null : cursor.element;
    }

//    @Override
//    public boolean hasNext() {
//        if (isEmpty()) {
//            return false;
//        }
//        if (cursor == tail) {
//            return false;
//        }
//        return true;
//    }

    @Override
    public boolean hasNext() {
        return cursor != null && cursor.getNext() != null;
    }

    @Override
    public boolean hasPrev() {
        if (isEmpty()) {
            return false;
        }
        return cursor != head;
    }

    @Override
    public String toString() {
        StringBuilder ListToPrint = new StringBuilder("[");
        DNode current = head;

        while (current != null) {
            ListToPrint.append((current.getElement()));
            current = current.getNext();
            if (current != null) {
                ListToPrint.append(", ");
            }
        }
        ListToPrint.append("]");
        return ListToPrint.toString();
    }
}