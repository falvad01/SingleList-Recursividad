package ule.edi.SimpleList;

import java.util.ArrayList;
import java.util.Comparator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SingleLinkedListImpl<T> extends AbstractSingleLinkedListImpl<T> {

	public SingleLinkedListImpl(T... elements) {

		this.header = null;

		if (elements.length > 0) {
			lanzarElementos(0, elements);
		}
	}

//////////////////////
///// ITERADORES
//////////////////////
	public class ForwardIterator implements Iterator<T> {

		private Node<T> at = header;

		@Override
		public boolean hasNext() {

			if (at == null) {
				return false;
			} else {
				return true;
			}

		}

		@Override
		public T next() {

			if (!hasNext()) {
				throw new NoSuchElementException();
			} else {

				T ret = at.content;
				at = at.next;
				return ret;
			}

		}

		@Override
		public void remove() {

			throw new UnsupportedOperationException();
		}
	};

	private T lanzarElementos(int i, T... elements) {

		if (i == elements.length - 1) {
			addLast(elements[i]);
			return elements[i];
		}

		addLast(elements[i]);
		return lanzarElementos(i + 1, elements);

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

		return new ForwardIterator();
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
			throw new IllegalArgumentException();

		} else if (p == 1) {
			addFirst(element);

		} else if (p >= size()) {

			addLast(element);

		} else {
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
	 * 
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
		
		return localizarNodo(this.header, elem, 1);
	}

	private int localizarNodo(Node<T> nodo, T elem, int pos) {

		if (nodo == null) {
			throw new NoSuchElementException();
		} else {

			if (nodo.content.equals(elem)) {
				return pos;
			} else {
				nodo = nodo.next;
				return localizarNodo(nodo, elem, pos + 1);
			}
		}

	}

	@Override
	public T removeLast() throws EmptyCollectionException {

		if (isEmpty()) {
			throw new EmptyCollectionException("");
		} else {

			Node<T> delete = penultimoNodo(this.header, 1);
			T ret = delete.content;
			delete.next = null; // Borramos el ultimo nodo borrando su referencia

			return ret;
		}
	}

	/**
	 * Metodo que nos devuelve el penultimo nodo de una lista de nodos
	 * 
	 * @param nodo
	 * @param vueltas
	 * @return
	 */
	private Node<T> penultimoNodo(Node<T> nodo, int vueltas) {

		if (size() - 2 == vueltas) { // Llegamos al penultimo

			return nodo;

		} else {

			nodo = nodo.next;
			return penultimoNodo(nodo, vueltas + 1); // nos movemos en los nodos
		}

	}

	@Override
	public T removeLast(T elem) throws EmptyCollectionException {

		if (isEmpty()) {
			throw new EmptyCollectionException("");
		} else {

			ArrayList<Integer> posiciones = new ArrayList<>();
			posiciones = buscarPos(elem, 1, posiciones, this.header);

			if (posiciones.get(posiciones.size() - 1) == 1) { // caso cuando el elemento a eliminar es la cabeza
				T ret = header.content;
				this.header = header.next;
				return ret;

			} else {
				// la posicion a elimianr es la ultima guardada en el arrayList
				return eliminarPos(this.header, this.header.next, posiciones.get(posiciones.size() - 1), 1);
			}
		}

	}

	/**
	 * Eliminamos el nodo de la posciokn pasada por argumento
	 * 
	 * @param anterior  Nodos
	 * @param posterior
	 * @param pos       Posicion a elimonar
	 * @param vueltas   numero de vueltas dada recursivamente
	 * @return
	 */
	private T eliminarPos(Node<T> anterior, Node<T> posterior, int pos, int vueltas) {

		if (pos - 1 == vueltas) {

			T ret = posterior.content;

			if (posterior.next == null) { // caso de que el elemento a eliminar sea el ultimo de la lista
				anterior.next = null;

			} else {

				anterior.next = posterior.next;
			}

			return ret;

		} else {

			anterior = anterior.next; // movemos los nodos
			posterior = posterior.next;

			return eliminarPos(anterior, posterior, pos, vueltas + 1);

		}
	}

	/**
	 * 
	 * Obtenemos la ultima posicion del elemento deseado
	 * 
	 * @param elem
	 * @param vuelta
	 * @param guardar
	 * @param nodo
	 * @return
	 */
	private ArrayList<Integer> buscarPos(T elem, int vuelta, ArrayList<Integer> guardar, Node<T> nodo) {

		if (nodo.next == null) {

			if (nodo.content == elem) {

				guardar.add(vuelta);
			}

			return guardar;

		} else {

			if (nodo.content == elem) { // en caso de que en la lista haya un elemento como el quequeremos borrar los
										// añadimos al array de posiciones
				guardar.add(vuelta);
			}

			nodo = nodo.next;
			return buscarPos(elem, vuelta + 1, guardar, nodo);
		}
	}

	@Override
	public AbstractSingleLinkedListImpl<T> reverse() {

		SingleLinkedListImpl<T> nuevaLista = new SingleLinkedListImpl<T>();

		return girar(this.header, nuevaLista);
	}

	/**
	 * 
	 * @param nodo
	 * @param listaInvertida
	 * @return
	 */
	private AbstractSingleLinkedListImpl<T> girar(Node<T> nodo, SingleLinkedListImpl<T> listaInvertida) {

		if (nodo.next == null) {
			listaInvertida.addFirst(nodo.content); // Añadimos el ultimo nodo a la lista
			return listaInvertida;
		} else {

			listaInvertida.addFirst(nodo.content);
			System.out.println(listaInvertida.toString());
			nodo = nodo.next;
			return girar(nodo, listaInvertida);
		}

	}

	@Override
	public int isSubList(AbstractSingleLinkedListImpl<T> part) {

		if (isEmpty()) {
			return -1;
		} else if (part.isEmpty()) {
			return 1;
		} else {

			int ret = localizarSublista(this.header, part.header, 1, 1, false);
			System.out.println(ret);
			return ret;
		}
	}

	/**
	 * 
	 * @param nodo
	 * @param buscar
	 * @param pos
	 * @param count
	 * @param flag
	 * @return
	 */
	private int localizarSublista(Node<T> nodo, Node<T> buscar, int pos, int count, boolean flag) {

		if (nodo.next == null && !flag) { // No se ha encontrado ninguna posicion de la sublista
			System.out.println("No se ha encontrado ninguna posicion de la sublista");
			return -1;
		} else if (nodo.content == buscar.content) { // Encontrado el nodo de la sublista
			flag = true;
			System.out.println("Encontrado el nodo de la sublista en pos: " + pos);
			count = pos;
			if (buscar.next == null) {
				return count;
			} else { // recoremos la sublista

				System.out.println("recoremos la sublista");

				buscar = buscar.next;
				nodo = nodo.next;
				return localizarSublista(nodo, buscar, pos, count, flag);

			}

		} else {// Aun no se ha encontrado la cabecera de la sublista

			System.out.println("Aun no se ha encontrado la cabecera de la sublista en la pos: " + pos);
			nodo = nodo.next;

			return localizarSublista(nodo, buscar, pos + 1, count, flag);
		}

	}

}
