package DS_Projektas;

import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

import java.util.ConcurrentModificationException;

/**
 * Unrolled Linked List realizavimo klasė
 * @param <E>
 */
public class UnrolledLinkedList<E> extends AbstractList<E> implements List<E>, Serializable, Cloneable {

    /**
     * Mazgo talpa
     */
    private int nodeCapacity;

    /**
     * Eleementų kiekis liste
     */
    private int size = 0;

    /**
     * Pradinis mazgo kreipinys
     */
    private Node firstNode;

    /**
     * Pabaigos mazgo kreipinys
     */
    private Node lastNode;

    /**
     * Konstruktorius, kuriame nurodoma talpa
     * @param nodeCapacity talpa
     * @throws IllegalArgumentException jei talpa mažesnė už 8
     */
    public UnrolledLinkedList(int nodeCapacity) throws IllegalArgumentException {

        if (nodeCapacity < 8) {
            throw new IllegalArgumentException("nodeCapacity < 8");
        }
        this.nodeCapacity = nodeCapacity;
        firstNode = new Node();
        lastNode = firstNode;

    }
    

    /**
     * Tuščias konstruktorius su talpa 16
     * {@link UnrolledLinkedList#nodeCapacity nodeCapacity} of 16.
     */
    public UnrolledLinkedList() {

        this(16);

    }

    /**
     * Grąžinama talpa List
     *
     * @return talpa
     */
    @Override
    public int size() {

        return size;

    }

    /**
     * Tikrina ar tuščias listas
     *
     * @return true/false
     */
    @Override
    public boolean isEmpty() {

        return (size == 0);

    }
    
    /**
	 * Sukuria sąrašo kopiją.
	 * @return sąrašo kopiją
	 */
//	@Override
//	public UnrolledLinkedList<E> clone() {
//		UnrolledLinkedList<E> cl = null;
//		try {
//			cl = (UnrolledLinkedList<E>) super.clone();
//		} catch (CloneNotSupportedException e) {
//			Ks.ern("Blogai veikia ListKTU klasės protėvio metodas clone()");
//			System.exit(1);
//		}
//		if (firstNode == null) {
//			return cl;
//		}
//		cl.firstNode = new Node<>(this.firstNode.element, null);
//		Node<E> e2 = cl.firstNode;
//		for (Node<E> e1 = firstNode.next; e1 != null; e1 = e1.next) {
//			e2.next = new Node<>(e1.element, null);
//			e2 = e2.next;
//		}
//		cl.lastNode = e2;
//		cl.size = this.size;
//		return cl;
//	}


    /**
     *Tikrinama ar nurodytas elementas yra liste
     *
     * @param o element elementas
     * @return true/false
     */
    @Override
    public boolean contains(Object o) {

        return (indexOf(o) != -1);

    }

    /**
     * Grąžina iteratorių
     *
     * @return iteratorių
     */
    @Override
    public Iterator<E> iterator() {

        return new ULLIterator(firstNode, 0, 0);

    }


    /**
     * Prideda nurodytą elementą listo gale
     *
     * @param e pridedamas elementas
     * @return true (as specified by {@link Collection#add})
     */
    @Override
    public boolean add(E e) {

        insertIntoNode(lastNode, lastNode.numElements, e);
        return true;

    }

    /**
     * Šalina nurodytą elementą
     * @param o šalinamas elementas
     * @return true jeigu buvo toks elementas liste
     */
    @Override
    public boolean remove(Object o) {

        int index = 0;
        Node node = firstNode;
        if (o == null) {
            while (node != null) {
                for (int ptr = 0; ptr < node.numElements; ptr++) {
                    if (node.elements[ptr] == null) {
                        removeFromNode(node, ptr);
                        return true;
                    }
                }
                index += node.numElements;
                node = node.next;
            }
        } else {
            while (node != null) {
                for (int ptr = 0; ptr < node.numElements; ptr++) {
                    if (o.equals(node.elements[ptr])) {
                        removeFromNode(node, ptr);
                        return true;
                    }
                }
                index += node.numElements;
                node = node.next;
            }
        }
        return false;
    }
    
     /**
     * Rikiavimo metodas, pagal pasirinktą comparatorių
     * @param comparator - nurodoma rkiavimo tipas
     */
    public void sortJava(Comparator<E> comparator){
        Object[] array = this.toArray();
        Arrays.sort(array, (Comparator) comparator);
	int index = 0;
        Node n = firstNode;
        while(n != null){
            for(int i = 0; i < n.numElements-1; i++){
                n.elements[i] = array[index++];
            }
            n = n.next;
        }
    }

    /**
     * Grąžinamas nurodytas elementas
     *
     * @param index indeksas
     * @return elemento reikšmę
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node node;
        int p = 0;
        if (size - index > index) {
            node = firstNode;
            while (p <= index - node.numElements) {
                p += node.numElements;
                node = node.next;
            }
        } else {
            node = lastNode;
            p = size;
            while ((p -= node.numElements) > index) {
                node = node.previous;
            }
        }
        return (E) node.elements[index - p];

    }

    /**
     * Įterpia nurodytą elementą į nurodytą vietą
     * @param index vietos indeksas kurioje elementas bus šterptas
     * @param element pridedamas elementas
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public void add(int index, E element) throws IndexOutOfBoundsException {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node node;
        int p = 0;
        if (size - index > index) {
            node = firstNode;
            while (p <= index - node.numElements) {
                p += node.numElements;
                node = node.next;
            }
        } else {
            node = lastNode;
            p = size;
            while ((p -= node.numElements) > index) {
                node = node.previous;
            }
        }
        insertIntoNode(node, index - p, element);

    }

    /**
     * Pašalina elementą nurodytoje vietoje
     * @param index šalinamo elemento indeksas
     * @return elementas anksčiau nurodytoje vietoje
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public E remove(int index) throws IndexOutOfBoundsException {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E element = null;
        Node node;
        int p = 0;
        if (size - index > index) {
            node = firstNode;
            while (p <= index - node.numElements) {
                p += node.numElements;
                node = node.next;
            }
        } else {
            node = lastNode;
            p = size;
            while ((p -= node.numElements) > index) {
                node = node.previous;
            }
        }
        element = (E) node.elements[index - p];
        removeFromNode(node, index - p);
        return element;

    }
    private static final long serialVersionUID = -674052309103045211L;

    private class Node {

        /**
         * Rodyklė į sekantį mazgą
         */
        Node next;

        /**
         * Rodyklį į ankstesnį mazgą
         */
        Node previous;

        /**
         * Elementų skaičius šiame mazge
         */
        int numElements = 0;

        /**
         * Masyvas elementų esančių šiame mazge
         */
        Object[] elements;

        /**
         * Konstruktorius su talpa
         */
        Node() {

            elements = new Object[nodeCapacity];
        }
    }

    private class ULLIterator implements ListIterator<E> {

        Node currentNode;
        int ptr;
        int index;

        private int expectedModCount = modCount;

        ULLIterator(Node node, int ptr, int index) {

            this.currentNode = node;
            this.ptr = ptr;
            this.index = index;

        }

        @Override
        public boolean hasNext() {

            return (index < size - 1);

        }

        @Override
        public E next() {

            ptr++;
            if (ptr >= currentNode.numElements) {
                if (currentNode.next != null) {
                    currentNode = currentNode.next;
                    ptr = 0;
                } else {
                    throw new NoSuchElementException();
                }
            }
            index++;
            checkForModification();
            return (E) currentNode.elements[ptr];

        }

        @Override
        public boolean hasPrevious() {

            return (index > 0);

        }

        @Override
        public E previous() {

            ptr--;
            if (ptr < 0) {
                if (currentNode.previous != null) {
                    currentNode = currentNode.previous;
                    ptr = currentNode.numElements - 1;
                } else {
                    throw new NoSuchElementException();
                }
            }
            index--;
            checkForModification();
            return (E) currentNode.elements[ptr];

        }

        @Override
        public int nextIndex() {

            return (index + 1);

        }

        @Override
        public int previousIndex() {

            return (index - 1);

        }

        @Override
        public void remove() {

            checkForModification();
            removeFromNode(currentNode, ptr);

        }

        @Override
        public void set(E e) {

            checkForModification();
            currentNode.elements[ptr] = e;

        }

        @Override
        public void add(E e) {

            checkForModification();
            insertIntoNode(currentNode, ptr + 1, e);

        }

        private void checkForModification() {

            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }

        }

    }

    /**
     * Įterpia elementą nurodytame mazge
     * @param node mazgas
     * @param ptr vueta, kurioje turėtų būti įterptas elementas
     * @param element įterpiamas elementas
     */
    private void insertIntoNode(Node node, int ptr, E element) {

        // jei mazgas pilnas
        if (node.numElements == nodeCapacity) {
            // create a new node
            Node newNode = new Node();
            // perkelia pusę mazgo elementų į kitą mazgą
            int elementsToMove = nodeCapacity / 2;
            int startIndex = nodeCapacity - elementsToMove;
            int i;
            for (i = 0; i < elementsToMove; i++) {
                newNode.elements[i] = node.elements[startIndex + i];
                node.elements[startIndex + i] = null;
            }
            node.numElements -= elementsToMove;
            newNode.numElements = elementsToMove;
            // įterpia naują mazgą į listą
            newNode.next = node.next;
            newNode.previous = node;
            if (node.next != null) {
                node.next.previous = newNode;
            }
            node.next = newNode;

            if (node == lastNode) {
                lastNode = newNode;
            }
            if (ptr > node.numElements) {
                node = newNode;
                ptr -= node.numElements;
            }
        }
        for (int i = node.numElements; i > ptr; i--) {
            node.elements[i] = node.elements[i - 1];
        }
        node.elements[ptr] = element;
        node.numElements++;
        size++;
        modCount++;
    }

    /**
     *Pašalina elementą nurodytame mazge
     * 
     * @param node mazgas iš kurio bus šalinama
     * @param ptr šalinamo elemento indeksas
     * the <tt>node.elements<tt> array
     */
    private void removeFromNode(Node node, int ptr) {

        node.numElements--;
        for (int i = ptr; i < node.numElements; i++) {
            node.elements[i] = node.elements[i + 1];
        }
        node.elements[node.numElements] = null;
        if (node.next != null && node.next.numElements + node.numElements <= nodeCapacity) {
            mergeWithNextNode(node);
        } else if (node.previous != null && node.previous.numElements + node.numElements <= nodeCapacity) {
            mergeWithNextNode(node.previous);
        }
        size--;
        modCount++;

    }

    /**
     * Sujungia du mazgus
     *
     * @param node mazgas kuris bus sujungiamas su kitu mazgu
     */
    private void mergeWithNextNode(Node node) {

        Node next = node.next;
        for (int i = 0; i < next.numElements; i++) {
            node.elements[node.numElements + i] = next.elements[i];
            next.elements[i] = null;
        }
        node.numElements += next.numElements;
        if (next.next != null) {
            next.next.previous = node;
        }
        node.next = next.next;
        if (next == lastNode) {
            lastNode = node;
        }
    }
}