package edu.nju.rpc.register;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import edu.nju.rpc.url.URL;
import redis.clients.jedis.Jedis;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class RemoteMapRegister {
    // static Map<String, List<URL>> REGISTER = new HashMap<>();
    private static Jedis jedis = new Jedis("127.0.0.1",6379);
    public static void register(String interfaceName, URL url) throws IOException {
        //List<URL>list = REGISTER.get(interfaceName);
        //if(list == null) {
        //    list = new ArrayList<>();
        //}
        //list.add(url);
        //REGISTER.put(interfaceName, list);
        Codec<URL> urlCodec = ProtobufProxy.create(URL.class);
        byte[] bb = urlCodec.encode(url);
        String str = new String(bb,StandardCharsets.ISO_8859_1);
        jedis.sadd(interfaceName,str);
    }
    public static List<URL> get(String interfaceName) {
        Set<String> set = jedis.smembers(interfaceName);
        List<URL> result = set.stream().map(str -> {
            Codec<URL> urlCodec = ProtobufProxy.create(URL.class);
            try {
                byte[] bb = str.getBytes(StandardCharsets.ISO_8859_1);
                URL url = urlCodec.decode(bb);
                return url;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new URL();
        }).collect(Collectors.toList());
        return result;
    }
}
