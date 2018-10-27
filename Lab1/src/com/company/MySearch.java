package com.company;

import java.util.Arrays;
import java.util.Random;

final class MySearch {
    private static Comparable[] array;
    private static Comparable[] searchItem;


    void setIntArray(int n) {

        array = new Comparable[n];
        for (int x = 0; x < array.length; x++)
            array[x] = new Random().nextInt(Integer.MAX_VALUE);

        Arrays.sort(array);
    }

    void setStringArray(int n) {

        array = new Comparable[n];
        for (int x = 0; x < array.length; x++)
            array[x] = generateRandomString(32);


        Arrays.sort(array);
    }


    void getSearchItems(int size) {
        searchItem = new Comparable[size];
        for (int x = 0; x < searchItem.length; x++) {
            searchItem[x] = array[new Random().nextInt(array.length - 1)];
        }


    }

    String generateRandomString(int length) {
        Random random = new Random();
        int leftLimit = 97;
        int rightLimit = 122;
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomInt);
        }

        return buffer.toString();

    }


    public static class LinearSearch implements Stopwatch.Test {


        int linearSearch(Comparable element) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].compareTo(element) == 0) {
                    return i;
                }
            }
            return -1;

        }

        @Override
        public void test() {

            for (Comparable e : searchItem) {
                if (linearSearch(e) < 0) {
                    throw new IllegalStateException("Incorrect function to be tested operation");

                }
            }


        }
    }

    public static class BinarySearch implements Stopwatch.Test {
        int binarySearch(Comparable element) {

            int min = 0;
            int max = array.length - 1;
            int middle;
            while (min <= max) {
                middle = (int) Math.floor((min + max) / 2);
                if (element.compareTo(array[middle]) < 0) {
                    max = middle - 1;
                } else if (element.compareTo(array[middle]) > 0) {
                    min = middle + 1;
                } else {
                    return middle;
                }
            }

            return -1;
        }

        @Override
        public void test() {

            for (Comparable e : searchItem) {
                if (binarySearch(e) < 0) {
                    throw new IllegalStateException("Incorrect function to be tested operation");

                }
            }


        }
    }


}

