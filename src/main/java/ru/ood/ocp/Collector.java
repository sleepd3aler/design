package ru.ood.ocp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collector {
    public ArrayList<Integer> collectNums(Integer... nums) {
        return new ArrayList<>(Arrays.stream(nums).toList());
    }

    public ArrayList<String> collectStrings(String... strings) {
        return new ArrayList<>(Arrays.stream(strings).toList());
    }

    public <T> List<T> collect(T... items) {
        return Arrays.stream(items).toList();
    }
}
