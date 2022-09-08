package edu.nju.rpc.factory;

import edu.nju.rpc.api.HelloService;
import edu.nju.rpc.client.HttpClient;
import edu.nju.rpc.invocation.Invocation;
import edu.nju.rpc.register.RemoteMapRegister;
import edu.nju.rpc.url.URL;
import edu.nju.rpc.utils.LoadBalance;
import lombok.val;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Inet4Address;
import java.util.List;

public class ProxyFactory<T> {
    public static <T> T getProxy(final Class interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                HttpClient client = new HttpClient();
                Invocation invocation = new Invocation(interfaceClass.getName(),
                        method.getName(), method.getParameterTypes(), args);
                //注册中心
                List<URL> urls = RemoteMapRegister.get(interfaceClass.getName());
                //负载均衡
                String hostAddr = Inet4Address.getLocalHost().getHostAddress();
                //URL url = LoadBalance.random(urls);
                URL url = LoadBalance.consistentHash(hostAddr,urls);

                String result = client.send(url.getHostName(),url.getPort(), invocation);
                return result;
            }
        });
    }
}
