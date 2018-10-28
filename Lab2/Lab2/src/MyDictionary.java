import java.util.Arrays;

public class MyDictionary implements Dictionary {
    private List[] list; // Bucket for holding all the nodes
    private int capacity; // maximum capacity of the list
    private int currentSize; // current size of the list

    MyDictionary(int n) {
        capacity = nextPrime(n);
        list = new List[capacity];
        Arrays.fill(list, null); // fill all the arrays with null

    }

    private int hash(String key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    @Override
    public Object get(String key) {
        int indexInChain = hash(key);

        if (list[indexInChain] != null) {
            return list[indexInChain].get(key);
        }
        return null;


    }

    // put the object to the buckets

    @Override
    public int put(Object item, String key) {
        int indexInChain = hash(key);
        List nodeList;

        if (list[indexInChain] == null) {
            list[indexInChain] = new List();
        }

        nodeList = list[indexInChain];
        boolean isUnique = nodeList.add(item, key);

        if (isUnique) {
            currentSize++;
            /* if the load factor is more than 0.7 , double the size of array*/
            if ((1.0 * currentSize) / capacity >= 0.7) {
                List[] temp = list;
                capacity = nextPrime(capacity * 2);
                list = new List[nextPrime(capacity)]; // create a new array with double the size
                currentSize = 0;
                Arrays.fill(list, null);

                for (List prevNodes : temp) { // fill all those elements from previous array to a new array
                    if (prevNodes != null) {
                        List.Node tmp = prevNodes.getHead();
                        while (tmp != null) {
                            put(tmp.getValue(), tmp.getKey());
                            tmp = tmp.getNext();
                            currentSize++;
                        }

                    }


                }
            }
        }


        return indexInChain;
    }

    @Override
    public int del(String key) {  // deletes from the buckets
        int indexInChain = hash(key);
        if (list[indexInChain] != null) {
            List nodeList = list[indexInChain];
            boolean result = nodeList.delete(key);

            if (result) {
                if (nodeList.getCounter() == 0) {
                    list[indexInChain] = null;
                }
            }


        }
        return indexInChain;
    }

    /* returns back the next nearest prime number*/
    private static int nextPrime(int n) {
        int prime = 0, i, nextPrime;
        /* check first if this is a prime number */
        for (i = 2; i < n / 2; i++) {
            if (n % i == 0) {
                prime = 1;
                break;
            }
        }
        if (prime == 1) {
            /* no, try to find next one */
            nextPrime = n;
            prime = 1;
            while (prime != 0) {
                nextPrime++;
                prime = 0;
                for (i = 2; i < nextPrime / 2; i++) {
                    if (nextPrime % i == 0) {
                        prime = 1;
                        break;
                    }
                }
            }
            return (nextPrime);
        } else
            /* yes, return this as is */
            return (n);
    }

    @Override
    public void printDictionary() {

        for (int counter = 0; counter< capacity; counter++) {
            if (list[counter] != null){
                System.out.println(counter+": "+list[counter]);

            } else {
                System.out.println(counter+": [empty] ");
            }

        }
    }
}

