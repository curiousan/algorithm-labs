package com.company;

public class QuickSort {

    public static Comparable[] sort( Comparable[]array){

        Comparable[] arrayToSort  = array;
            int low = 0;
            int high = arrayToSort.length - 1;
            quickSort(arrayToSort, low, high);
            return arrayToSort;

    }

    private static void quickSort(Comparable[] array, int low, int high) {

        if (array == null || array.length == 0) {
            return;
        }

        if (low >= high) {
            return;
        }

        int middle = low + (high - low) / 2, i = low, j = high;
        ;
        Comparable pivot = array[middle];

        while (i <= j) {

            while (array[i].compareTo(pivot) < 0) {
                i++;
            }

            while (array[j].compareTo(pivot) > 0) {
                j--;
            }

            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }

        if (low < j) {
            quickSort(array, low, j);
        }

        if (high > i) {
            quickSort(array, i, high);
        }

    }


    private static void swap(Comparable[] array, int x, int y) {
        Comparable temp = array[x];
        array[x] = array[y];
        array[y] = temp;

    }

}
