package com.mops.registrar.entities;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

public class ChildTest {

    @Test
    public void testAge_UnderTwo() {
        Child child = new Child();
        LocalDate now = new LocalDate();
        LocalDate dateOfBirth = new LocalDate(now.minusYears(1));
        child.setDateOfBirth(dateOfBirth);

        Assert.assertEquals("child must be 12 months old", "12 " + Child.MONTHS_OLD, child.getAge());
    }

    @Test
    public void testAge_OverTwo() {
        Child child = new Child();
        LocalDate now = new LocalDate();
        LocalDate dateOfBirth = new LocalDate(now.minusYears(3));
        child.setDateOfBirth(dateOfBirth);

        Assert.assertEquals("child must be 3 years old", "3 " + Child.YEARS_OLD, child.getAge());
    }

    @Test
    public void testCompareTo() {
        Child childOne = new Child();
        Child childTwo = new Child();

        LocalDate now = new LocalDate();
        childOne.setDateOfBirth(new LocalDate(now.minusYears(4)));
        childTwo.setDateOfBirth(new LocalDate(now.minusYears(3)));

        Assert.assertTrue("childOne is older than childTwo, so the dateOfBirth is less than the other",
                childOne.compareTo(childTwo) < 0);
        Assert.assertTrue("childTwo is younger than childOne, so the dateOfBirth is greater than the other",
                childTwo.compareTo(childOne) > 0);
    }
}
