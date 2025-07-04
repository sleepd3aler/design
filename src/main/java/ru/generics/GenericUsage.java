package ru.generics;

import java.util.*;

public class GenericUsage {
    public void printResult(Collection<?> collection) {
        for (Iterator<?> iterator = collection.iterator(); iterator.hasNext(); ) {
            Object next = iterator.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printInfo(Collection<? extends Person> collection) {
        for (Iterator<? extends Person> iterator = collection.iterator(); iterator.hasNext(); ) {
            Person next = iterator.next();
            System.out.println(next);
        }
    }

    public void addAll(List<? super Integer> list) {
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }
        for (Object line : list) {
            System.out.println("Текущий элемент: " + line);
        }
    }

    public static void main(String[] args) {
        GenericUsage genericUsage = new GenericUsage();
        List list = new ArrayList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        list.add(new Person("name", 21, new Date(913716000000L)));
        List<Integer> list2 = List.of(1, 2, 3, 4, 5);
        List<? super Integer> testWild = new ArrayList<>();
        genericUsage.printResult(list2);
        genericUsage.addAll(testWild);
    }
}
