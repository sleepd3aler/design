package ru.generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        Generics generics = new Generics();
        List<Animal> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();
        first.add(new Animal("Animal", 12));
        second.add(new Predator("Predator", 15));
        third.add(new Tiger("Tiger", 12));

        generics.printObject(first);
        generics.printObject(second);
        generics.printObject(third);
        System.out.println();

        /*
        Не скомпилируется, т.к. first хранит объекты типа Animal, которые являются выше в иерархии
        Метод принимает только наследников Predator
        generics.printBoundedWildCard(first);
        */

        generics.printBoundedWildCard(second);
        generics.printBoundedWildCard(third);
        System.out.println();

        generics.printLowerBoundedWildCard(first);
        generics.printLowerBoundedWildCard(second);

        /* Не скомпилируется т.к. third хранит объекты типа Tiger, которые являются ниже в иерархии
        Метод принимает Predator и его родителей
        generics.printLowerBoundedWildCard(third);
        */
    }

    public void printObject(List<?> list) {
        for (Iterator<?> iterator = list.iterator(); iterator.hasNext(); ) {
            Object next = iterator.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printBoundedWildCard(List<? extends Predator> list) {
        for (Iterator<? extends Predator> iterator = list.iterator(); iterator.hasNext(); ) {
            Object next = iterator.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printLowerBoundedWildCard(List<? super Predator> list) {
        for (Iterator<? super Predator> iterator = list.iterator(); iterator.hasNext(); ) {
            Object next = iterator.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

}
