package edu.nju.rpc.utils;

import edu.nju.rpc.url.URL;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class LoadBalance {
    public static URL random(List<URL> list) {
        Random random = new Random();
        int x = random.nextInt(list.size());
        return list.get(x);
    }
}
