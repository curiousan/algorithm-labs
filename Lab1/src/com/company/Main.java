package com.company;

import java.io.PrintWriter;

public class Main {
    private static final int N = 10;        // number of test points
    private static final int STARTN = 10000;
    private static final int INCN = 5000;
    private static final String OUTPUTFILE = "result.csv";

    public static void main(String[] args) {
        int arrayN;

        try {
            PrintWriter writer = new PrintWriter(OUTPUTFILE, "UTF-8");

            System.out.println("Number of CPU cores " + Runtime.getRuntime().availableProcessors());
            writer.println("Array Size;Linear Integer Search;Binary Integer Search");
//            writer.println("Array Size;Linear String Search;Binary String Search");

            Stopwatch sw = new Stopwatch();
            arrayN = STARTN;/**/


            for (int i = 0; i < N; i++) {
                System.out.println("N: " + arrayN);
                MySearch newSearch = new MySearch();
                newSearch.setIntArray(arrayN);
                newSearch.getSearchItems(500);

                StringBuilder result = new StringBuilder();

                MySearch.BinarySearch binarySearch = new MySearch.BinarySearch();
                MySearch.LinearSearch linearSearch = new MySearch.LinearSearch();

                result.append(arrayN);

                sw.measure(linearSearch); result.append(";"); sw.toValue(result);
                sw.measure(binarySearch); result.append(";"); sw.toValue(result);
                result.append("\n");

                writer.append(result.toString().replace(".", ","));



                arrayN += INCN;
            }


            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}


