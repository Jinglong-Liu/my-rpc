package edu.nju.rpc.protocal.http;

import edu.nju.rpc.invocation.Invocation;
import edu.nju.rpc.register.LocalRegister;
import org.apache.commons.io.IOUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HttpServerHandler {
    /**
     * 处理请求 多个handler
     * @param req
     * @param resp
     */
    public void handle(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Invocation invocation = (Invocation) new ObjectInputStream(req.getInputStream()).readObject();
            String interfaceName = invocation.getInterfaceName();
            Class clazz = LocalRegister.get(interfaceName);
            Method method = clazz.getMethod(invocation.getMethodName(), invocation.getParamType());
            String result = (String) method.invoke(clazz.newInstance(),invocation.getParams());
            IOUtils.write(result, resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
