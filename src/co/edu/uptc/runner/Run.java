package co.edu.uptc.runner;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.edu.uptc.structure.MyList;


public class Run {
    public static void main(String[] args) {
        MyList<Integer> list = new MyList<>();
        System.out.println("Agregando elementos...");
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);
        list.add(70);
        list.add(100);
        

        System.out.println("Lista inicial: " + Arrays.toString(list.toArray()));

        System.out.println("Tamaño de la lista: " + list.size());

        System.out.println("¿lista vacía? " + list.isEmpty());

        System.out.println("¿Contiene 30? " + list.contains(30));
        System.out.println("¿Contiene 100? " + list.contains(100));
        System.out.println("¿Contiene 101? " + list.contains(101));

        System.out.println("Elemento en el índice 2: " + list.get(2));

        System.out.println("Elemento reemplazado: " + list.set(1, 99));
        System.out.println("Lista después de la modificación: " + Arrays.toString(list.toArray()));

        System.out.println("Eliminando el elemento 30...");
        list.remove(Integer.valueOf(30));
        System.out.println("Lista después de la eliminación: " + Arrays.toString(list.toArray()));

        System.out.println("Sublista de índice 1 a 3: " + Arrays.toString(list.subList(1, 3).toArray()));

        Integer[] array = new Integer[list.size()];
        list.toArray(array);
        System.out.println("Lista convertida a arreglo: " + Arrays.toString(array));

        List<Integer> collectionToRemove = new ArrayList<>();
        collectionToRemove.add(40);
        collectionToRemove.add(99); 
        System.out.println("Eliminando todos los elementos en la colección " + collectionToRemove + "...");
        list.removeAll(collectionToRemove);
        System.out.println("Lista después de removeAll: " + Arrays.toString(list.toArray()));

        List<Integer> collectionToRetain = new ArrayList<>();
        collectionToRetain.add(20);
        collectionToRetain.add(50);
        System.out.println("Reteniendo solo los elementos en la colección " + collectionToRetain + "...");
        list.retainAll(collectionToRetain);
        System.out.println("Lista después de retainAll: " + Arrays.toString(list.toArray()));

        System.out.println("Limpiando la lista...");
        list.clear();
        System.out.println("¿Está la lista vacía después de probar  clear? " + list.isEmpty());
    }
}
