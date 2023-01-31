package DS_Projektas;

public interface ListADT<E> {

	/**
	 * Elemento įterpimo metodas
	 *
	 * @param e elemento duomenys
	 * @return true
	 */
	boolean add(E e);

	/**
	 * Grąžina listo talpą
	 */
	int size();

	/**
	 * @return true jei tuščias
	 */
	boolean isEmpty();

	/**
	 * Ištrina visus elementus iš listo
	 */
	void clear();

	/**
	 * Grąžina elementą nurodytoje vietoje
	 *
	 * @param k elemento indeksas
	 * @return tą elementą
	 * @throws IndexOutOfBoundsException {@inheritDoc}
	 */
	E get(int k);

	/**
	 * Atitinka iteratoriaus metodą next (List tokio metodo neturi)
	 * @return kitą reikšmę.
	 */
	E getNext();
        
        //E set(int index, E element);
        E remove(int index);
        boolean removeRange(int fromIndex, int toIndex);
        void add(int index, E element);
       
	/**
	 * Sukuria eleemntų masyvą
	 * @return masyvą
	 */
	Object[] toArray();

}

