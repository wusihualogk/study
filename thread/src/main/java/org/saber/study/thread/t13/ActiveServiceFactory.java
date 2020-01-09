package org.saber.study.thread.t13;

import org.saber.study.thread.t05.Future;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/7 15:39
 **/
public class ActiveServiceFactory {

    private final static ActiveMessageQueue queue = new ActiveMessageQueue();

    public static <T> T active(T instance) {
        Object proxy = Proxy.newProxyInstance(instance.getClass().getClassLoader(),
                instance.getClass().getInterfaces(),
                new ActiveInvocationHandler<>(instance));
        return (T) proxy;
    }

    public static class ActiveInvocationHandler<T> implements InvocationHandler {

        public final T instance;

        public ActiveInvocationHandler(T instance) {
            this.instance = instance;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.isAnnotationPresent(ActiveMethod.class)) {
                checkMethod(method);
                ActiveMessage.Builder builder = new ActiveMessage.Builder();
                builder.useMethod(method).withObjectArr(args).forService(instance);
                Object result = null;
                if (this.isReturnFutureType(method)) {
                    result = new ActiveFuture<>();
                    builder.returnFuture((ActiveFuture) result);
                }
                queue.offer(builder.build());
                return result;
            }

            return method.invoke(instance, args);
        }

        private void checkMethod(Method method) throws IllegalActiveMethod {
            if (!isReturnVoidType(method) && !isReturnFutureType(method)) {
                throw new IllegalActiveMethod("该方法[" + method.getName() + "]返回值必须是void、Future");
            }
        }

        private boolean isReturnFutureType(Method method) {
            return method.getReturnType().equals(Future.class);
        }

        private boolean isReturnVoidType(Method method) {
            return method.getReturnType().equals(Void.TYPE);
        }
    }


    public static void main(String[] args) {
        OrderService orderService = active(new OrderServiceImpl());
        orderService.findOrderDetails(3223L);
        System.out.println("踩踩踩");
    }
}
