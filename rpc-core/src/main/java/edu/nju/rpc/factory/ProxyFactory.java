package edu.nju.rpc.factory;

import edu.nju.rpc.api.HelloService;
import edu.nju.rpc.client.HttpClient;
import edu.nju.rpc.invocation.Invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory<T> {
    public static <T> T getProxy(final Class interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                HttpClient client = new HttpClient();
                Invocation invocation = new Invocation(interfaceClass.getName(),
                        method.getName(), method.getParameterTypes(), args);

                String result = client.send("localhost",8080, invocation);
                return result;
            }
        });
    }
}
