package edu.nju.rpc.utils;

import edu.nju.rpc.url.URL;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConsistentHashLoop {
    private static TreeMap<Integer,URL> nodes = new TreeMap<>();
    public static URL get(String hostAddr, List<URL>urls) {
        int hash = hostAddr.hashCode();
        nodes.clear();
        nodes.putAll(urls.stream().collect(Collectors.toMap(URL::hashCode, Function.identity(),(k1, k2)->k1)));
        SortedMap<Integer, URL> map = nodes.tailMap(hash);
        if(!map.isEmpty()) {
            return map.get(map.firstKey());
        }
        return nodes.get(nodes.firstKey());
    }
}
