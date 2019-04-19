package ule.edi.SimpleList;

import java.util.Comparator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SingleLinkedListImpl<T> extends AbstractSingleLinkedListImpl<T> {

	public SingleLinkedListImpl(T... elements) {

		this.header = null;

		for (int i = 0; i < elements.length; i++) {

			addLast(elements[i]);
		}

	}

	@Override
	public void addLast(T element) {

		Node<T> aux = new Node<T>(element);

		if (isEmpty()) {

			this.header = aux;

		} else {

			addLast(element, header);

		}
	}

	private Node<T> addLast(T element, Node<T> nodo) {

		if (nodo.next == null) {

			Node<T> aux = new Node<T>(element);

			nodo.next = aux;

			return aux;

		} else {

			nodo = nodo.next;
			return addLast(element, nodo);
		}
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {

		if (this.header == null) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public int size() {

		return 0;
	}

	@Override
	public void addFirst(T element) {

		Node<T> aux = new Node<T>(element);

		if (isEmpty()) {

		} else {
			aux.next = header;
		}
		this.header = aux;
	}

	@Override
	public void addAtPos(T element, int p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addNTimes(T element, int n) {
		// TODO Auto-generated method stub

	}

	@Override
	public int indexOf(T elem) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public T removeLast() throws EmptyCollectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T removeLast(T elem) throws EmptyCollectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractSingleLinkedListImpl<T> reverse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int isSubList(AbstractSingleLinkedListImpl<T> part) {
		// TODO Auto-generated method stub
		return 0;
	}

}
