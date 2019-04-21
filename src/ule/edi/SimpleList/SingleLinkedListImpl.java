package ule.edi.SimpleList;

import java.util.Comparator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SingleLinkedListImpl<T> extends AbstractSingleLinkedListImpl<T> {

	public SingleLinkedListImpl(T... elements) {

		this.header = null;

		for (int i = 0; i < elements.length; i++) { // TODO esta bine asi o se necesita otro metodo

			addLast(elements[i]);
		}
	}

	@Override
	public void addLast(T element) {

		Node<T> aux = new Node<T>(element);
		if (isEmpty()) {

			this.header = aux;

		} else {

			Node<T> aux2 = ultimoNodo(header);
			aux2.next = aux; // insertamos el nuevo nodo
		}
	}

	/**
	 * Recorremos todos los nodos de manera recursiva
	 * 
	 * @param element
	 * @param nodo    nodo que vamos moviendo
	 * @return el ultimo nodo de la lista
	 */
	private Node<T> ultimoNodo(Node<T> nodo) {

		if (nodo.next == null) { // Llegamos al ultimo nodo

			return nodo;

		} else {

			nodo = nodo.next;
			return ultimoNodo(nodo); // nos movemos en los nodos
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
	 * @param i    Contador de nodos
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
	 * @param contador  //Contador de la posion donde hay que meter el nodo
	 * @param anterior  llevamos dos referencias, dos nodos consecutivos entre los
	 *                  cuales se metera el nodo indicado
	 * @param posterior
	 * @param element
	 * @return
	 */
	private Node<T> buscarNodo(int pos, int contador, Node<T> anterior, Node<T> posterior, T element) {

		if (pos - 1 == contador - 1) {

			Node<T> ret = new Node<T>(element);

			anterior.next = ret;
			ret.next = posterior;
			return ret;
		} else {

			anterior = anterior.next; // Movemos las referencia
			posterior = posterior.next;

			return buscarNodo(pos, contador + 1, anterior, posterior, element);
		}

	}

	@Override
	public void addNTimes(T element, int n) {

		if (isEmpty()) {
			addFirst(element);
			nTimes(n - 1, 1, element, ultimoNodo(this.header));
		} else {
			nTimes(n, 1, element, ultimoNodo(this.header));
		}

	}

	/**
	 * Aniadimos de manera recursiva los n elementos
	 * @param vueltas
	 * @param contador
	 * @param elemento
	 * @param nodo
	 * @return
	 */
	private Node<T> nTimes(int vueltas, int contador, T elemento, Node<T> nodo) {
		Node<T> add = new Node<T>(elemento);

		if (vueltas == contador - 1) {

			return nodo;
		} else {

			nodo.next = add;

			nodo = nodo.next;

			return nTimes(vueltas, contador + 1, elemento, nodo);
		}

	}

	@Override
	public int indexOf(T elem) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public T removeLast() throws EmptyCollectionException {
		
		
		Node<T> delete = penultimoNodo(this.header,1);
		T ret = delete.content;
		delete.next = null; //Borramos el ultimo nodo borrando su referencia
		
		return ret;
	}
	/**
	 * Metodo que nos devuelve el penultimo nodo de una lista de nodos
	 * 
	 * @param nodo
	 * @param vueltas
	 * @return
	 */
	private Node<T> penultimoNodo(Node<T> nodo, int vueltas) {
		
		System.out.println(size() + "    " + vueltas);
		
		if (size()-2 == vueltas) { // Llegamos al penultimo
			
			return nodo;

		} else {

			nodo = nodo.next;
			return penultimoNodo(nodo, vueltas+1); // nos movemos en los nodos
		}
		
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
