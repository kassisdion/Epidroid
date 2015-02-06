package com.pony.epidroid.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hervie_g on 2/3/15.
 */
public class CollectionsUtils
{
    public static <T> List<T> filter(List<T> list, Filter<T> filter)
    {
        List<T> newList = new ArrayList<>();

        for (T item : list)
        {
            if (filter.check(item))
            {
                newList.add(item);
            }
        }

        return newList;
    }

    public static interface Filter<T>
    {
        public boolean check(T item);
    }
}
