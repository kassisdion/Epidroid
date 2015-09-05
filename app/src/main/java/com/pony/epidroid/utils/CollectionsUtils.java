package com.pony.epidroid.utils;

import java.util.ArrayList;
import java.util.List;

public class CollectionsUtils {
    public static <T> List<T> filter(List<T> list, Filter<T> filter) {
        List<T> newList = new ArrayList<>();

        for (T item : list) {
            if (filter.check(item)) {
                newList.add(item);
            }
        }

        return newList;
    }

    public interface Filter<T> {
        boolean check(T item);
    }
}
