package com.mo.guard.util;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ArrayUtil {


    public static List<Integer> findNoDuplicateInArrays(
            List<Integer> targetList,
            List<Integer> originList) {
        return targetList.stream().filter((a) -> {
            return originList.indexOf(a) == -1;
        }).collect(Collectors.toList());
    }
}
