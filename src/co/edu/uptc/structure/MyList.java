package co.edu.uptc.structure;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyList<E> implements List<E>{
	private Node<E> head;
	
	private void verifyNullity(Object o){
		if(o==null){
			throw new NullPointerException("Los datos no pueden ser nulos");
		}
	}
	
	private void verifyCasting(Object o){
		try{
			E temp = (E)o;
		}catch (ClassCastException d) {
			throw new ClassCastException("La clase del objeto ingresado no corresponde al de la lista");
		}
	}
	
	private void indexOutOfRange(int index){
		if(index<0||index>=size()){
			throw new IndexOutOfBoundsException("El indice esta fuera del rango de la lista");
		}
	}
	
	@Override
	public int size() {
		Node<E> auxNode=head;
		int counter=0;
		while(auxNode!=null) {
			counter++;
			auxNode=auxNode.getNext();
		}
		return counter;
	}

	@Override
	public boolean isEmpty() {
		return head==null;
	}

	@Override
	public boolean contains(Object o){
		verifyNullity(o);
		verifyCasting(o);
		Node<E> auxNode=head;
		while(auxNode!=null) {
			if(auxNode.getData().equals(o)){
				return true;
			}
			auxNode=auxNode.getNext();
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		Iterator<E> iterator;
		iterator=new Iterator<E>() {

			Node<E> auxNode=head;
			public boolean hasNext() {
				return auxNode!=null;
			}

			public E next() {
				E data=auxNode.getData();
				auxNode=auxNode.getNext();
				return data;
			}
		};
		return iterator;
	}

	@Override
	public Object[] toArray() {
		int size=size();
		Object[] rta=new Object[size];
		Node<E> auxNode=head;
		for(int i=0;i<size;i++) {
			rta[i] = auxNode.getData();
			auxNode=auxNode.getNext();
		}
		return rta;
	}
	
	//Implementacion del metodo toArray
	@Override
	public <T> T[] toArray(T[] a) {
	    if (a.length < size()) {
	        a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size());
	    }
	    
	    int index = 0;
	    Node<E> auxNode = head;
	    
	    while (auxNode != null) {
	        a[index++] = (T) auxNode.getData();
	        auxNode = auxNode.getNext();
	    }
	    if (a.length > size()) {
	        a[size()] = null;
	    }
	    
	    return a;
	}


	@Override
	public boolean add(E e){
		verifyNullity(e);
		verifyCasting(e);
		Node<E> newNode=new Node<E>(e);
		if(isEmpty()) {
			head=newNode;
		}else {
			Node<E> aux=head;
			while(aux.getNext()!=null) {
				aux=aux.getNext();
			}
			aux.setNext(newNode);
		}
		return true;
	}

	@Override
	public boolean remove(Object o){
		verifyNullity(o);
		verifyCasting(o);
		boolean rta;
		if(isEmpty()) {
			rta=false;
		}else if(head.getData().equals(o)){
			head = head.getNext();
			rta=true;
		}else{
			rta=removeDifferentHeadNodes(o);
		}
		return rta;
	}
	
	private boolean removeDifferentHeadNodes(Object o){
		Node<E> aux = head;
		while(aux.getNext()!=null && !aux.getNext().getData().equals(o)) {
			aux=aux.getNext();
		}
		if(!aux.getNext().equals(null)){
			aux.setNext(aux.getNext().getNext());
			return true;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c){
		verifyNullity(c);
		verifyCasting(c);
		for (Object element : c) {
			verifyNullity(element);
            if (!contains(element)) {
                return false;
            }
        }
        return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c){
		verifyCasting(c);
		if (c.isEmpty()) {
            return false;
        }else {
            for (E element : c) {
            	verifyNullity(element);
                add(element);
            }
            return true;
        }
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c){
		verifyCasting(c);
		indexOutOfRange(index);
		if (c.isEmpty()) {
            return false;
        }else{
        	int auxIndex=index;
    		for (E element : c) {
    			verifyNullity(element);
    			if(index==0) {
    				Node<E> newNode = new Node<E>(element);
    		        newNode.setNext(head);
    		        head = newNode;
    			}else{
    				add(auxIndex, element);
    				auxIndex++;
    			}
    		}
    		return true;
		}
	}
	
	//AJUSTE DEL METODO removeAll
	@Override
	public boolean removeAll(Collection<?> c) {
	    verifyNullity(c);
	    boolean modified = false;

	    for (Object element : c) {
	        if (remove(element)) {
	            modified = true;
	        }
	    }
	    
	    return modified;
	}

	//AJUSTE DEL METODO retainAll
	@Override
	public boolean retainAll(Collection<?> c) {
	    verifyNullity(c);
	    boolean modified = false;

	    Node<E> auxNode = head;
	    while (auxNode != null) {
	        if (!c.contains(auxNode.getData())) {
	            remove(auxNode.getData());
	            modified = true;
	        }
	        auxNode = auxNode.getNext();
	    }
	    
	    return modified;
	}


	@Override
	public E get(int index){
		indexOutOfRange(index);
		Node<E> auxNode = head;
		for(int i=0;i<index;i++) {
			auxNode=auxNode.getNext();
		}
		return auxNode.getData();
	}

	@Override
	public E set(int index, E element){
		verifyNullity(element);
		verifyCasting(element);
		indexOutOfRange(index);
		Node<E> auxNode = head;
		E oldData;
		for (int i=0;i<index;i++) {
			auxNode=auxNode.getNext();
		}
		oldData=auxNode.getData();
		auxNode.setData(element);
		return oldData;
	}

	@Override
	public void add(int index, E element){
		verifyNullity(element);
		verifyCasting(element);
		indexOutOfRange(index);
		Node<E> newNode = new Node<E>(element);
		if(index==0){
			newNode.setNext(head);
			head=newNode;
		}else{
			Node<E> auxNode=head;
			for (int i=0;i<index-1;i++) {
				auxNode=auxNode.getNext();
			}
			newNode.setNext(auxNode.getNext());
			auxNode.setNext(newNode);
		}
	}

	@Override
	public E remove(int index){
		indexOutOfRange(index);
		E oldData;
		if(isEmpty()) {
			return null;
		}else if(index==0){
			oldData=head.getData();
			head=head.getNext();
		}else{
			oldData=removeDifferentHeadNodes(index);
		}
		return oldData;
	}
	
	private E removeDifferentHeadNodes(int index){
		Node<E> auxNode = head;
		E oldData;
		for (int i=0;i<index-1;i++) {
			auxNode=auxNode.getNext();
		}
		oldData=auxNode.getNext().getData();
		auxNode.setNext(auxNode.getNext().getNext());
        return oldData;
	}

	@Override
	public int indexOf(Object o){
		verifyNullity(o);
		verifyCasting(o);
		Node<E> auxNode=head;
        int index=0;
        while(auxNode!=null){
            if(auxNode.getData().equals(o)) {
                return index;
            }
            auxNode=auxNode.getNext();
            index++;
        }
        return -1;
	}

	@Override
	public int lastIndexOf(Object o){
		verifyNullity(o);
		verifyCasting(o);
		Node<E> auxNode=head;
        int index=-1,counter=0;
        while(auxNode!=null){
            if(auxNode.getData().equals(o)) {
            	index=counter;
            }
            auxNode=auxNode.getNext();
            counter++;
        }
        return index;
	}
	
	//NUEVO METODO subList
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
	    indexOutOfRange(fromIndex);
	    indexOutOfRange(toIndex);
	    
	    if (fromIndex > toIndex) {
	        throw new IllegalArgumentException("El índice de inicio no puede ser mayor que el índice final.");
	    }
	    
	    List<E> sublist = new MyList<>();
	    Node<E> auxNode = head;
	    int currentIndex = 0;
	    
	    while (auxNode != null && currentIndex < toIndex) {
	        if (currentIndex >= fromIndex) {
	            sublist.add(auxNode.getData());
	        }
	        auxNode = auxNode.getNext();
	        currentIndex++;
	    }
	    
	    return sublist;
	}


	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
}