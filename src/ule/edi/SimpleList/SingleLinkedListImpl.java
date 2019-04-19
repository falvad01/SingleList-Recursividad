package ule.edi.SimpleList;

import java.util.Comparator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SingleLinkedListImpl<T> extends AbstractSingleLinkedListImpl<T> {

	public SingleLinkedListImpl(T... elements) {

		this.header = null;

		for (int i = 0; i < elements.length; i++) {  //TODO esta bine asi o se necesita otro metodo

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

	/**
	 * Recorremos todos los nodos de manera recursiva
	 * @param element
	 * @param nodo nodo que vamos moviendo
	 * @return
	 */
	private Node<T> addLast(T element, Node<T> nodo) {

		if (nodo.next == null) { // Llegamos al ultimo nodo

			Node<T> aux = new Node<T>(element);
			nodo.next = aux; // insertamos el nuevo nodo
			return aux;

		} else {

			nodo = nodo.next;
			return addLast(element, nodo); // nos movemos en los nodos
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

		return contador(1, this.header);// Empieza en 1, ya que la cabezera cuanta como 1
	}

	/**
	 * 
	 * @param i Contador de nodos
	 * @param nodo referencia al nodo contado anterior
	 * @return
	 */
	private int contador(int i, Node<T> nodo) {

		if (nodo.next == null) {

			return i + 1; // Contamos el ultimo y retornamos
		} else {

			return contador(i + 1, nodo.next);
		}

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

		if (p <= 0) {
			System.out.println("caso 1");
			throw new IllegalArgumentException();

		} else if (p == 1) {
			System.out.println("caso 2");
			addFirst(element);

		} else if (p >= size()) {

			addLast(element);
			System.out.println("caso 3");

		} else {
			System.out.println("caso 4");
			Node<T> aux = buscarNodo(p, 2, this.header, this.header.next, element);// El contador empieza en 2 ya que no
																					// se introduce en la priumera
																					// posiscion en este caso

		}

	}
	/**
	 * 
	 * @param pos
	 * @param contador //Contador de la posion donde hay que meter el nodo
	 * @param anterior llevamos dos referencias, dos nodos consecutivos entre los cuales se metera el nodo indicado
	 * @param posterior
	 * @param element
	 * @return
	 */
	private Node<T> buscarNodo(int pos, int contador, Node<T> anterior, Node<T> posterior, T element) {
		System.out.println(contador - 1);
		System.out.println(pos);
		if (pos - 1 == contador - 1) {

			Node<T> ret = new Node<T>(element);
		
			anterior.next = ret;
			ret.next = posterior;
			return ret;
		} else {
		
			anterior = anterior.next; //Movemos las referencia
			posterior = posterior.next;

			return buscarNodo(pos, contador + 1, anterior, posterior, element);
		}

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
