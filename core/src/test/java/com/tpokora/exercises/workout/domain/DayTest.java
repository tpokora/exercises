package com.tpokora.exercises.workout.domain;

import com.tpokora.exercises.workout.model.Day;
import org.junit.Assert;
import org.junit.Test;

public class DayTest {

    private static final int ID = 1;
    private static final String NAME = "Day";
    private static final Integer INDEX = 1;

    @Test
    public void test_day() {
        Day day = new Day();
        day.setId(ID);
        day.setName(NAME);
        day.setIndex(INDEX);

        Assert.assertTrue(day.getId() == ID);
        Assert.assertTrue(day.getName() == NAME);
        Assert.assertTrue(day.getIndex() == INDEX);
    }
}
