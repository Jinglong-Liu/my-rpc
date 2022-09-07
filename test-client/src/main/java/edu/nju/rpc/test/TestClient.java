package edu.nju.rpc.test;

import edu.nju.rpc.api.HelloService;
import edu.nju.rpc.invocation.Invocation;

public class TestClient {
    public static void main(String[] args) {
        HttpClient client = new HttpClient();
        Invocation invocation = new Invocation(HelloService.class.getName(),"hello",new Class[]{String.class},new Object[]{"user A"});

        String result = client.send("localhost",8080, invocation);
        System.out.println(result);
    }
}
