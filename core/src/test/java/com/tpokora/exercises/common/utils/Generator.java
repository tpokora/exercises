package com.tpokora.exercises.common.utils;

import java.util.List;

/**
 * Created by pokor on 13.06.2017.
 */
public interface Generator<T> {

    T generate();
    T generate(int id);
    List<T> generateList(int size);
}
