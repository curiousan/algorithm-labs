/**
 * Simple interface to provide methods for Dictionary operations
 */

public interface Dictionary {

    Object get(String key);

    int put(Object item, String key);

    int del(String key);

    void printDictionary();
}
