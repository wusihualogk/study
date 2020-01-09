package org.saber.study.thread.t13;

import org.saber.study.thread.t05.Future;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/7 15:18
 **/
public class ActiveMessage {

    /**
     * 接口的方法参数
     */
    private final Object[] objectArr;

    /**
     * 接口方法
     */
    private final Method method;

    /**
     * 有返回值的方法，会返回ActiveFuture<?>类型
     */
    private final ActiveFuture<Object> activeFuture;

    /**
     * 具体的Service接口
     */
    private final Object service;

    public ActiveMessage(Builder builder) {
        objectArr = builder.objectArr;
        method = builder.method;
        activeFuture = builder.activeFuture;
        service = builder.service;
    }

    public void execute() {
        try {
            Object result = method.invoke(service, objectArr);
            if (activeFuture != null) {
                Future<?> realFuture = (Future<?>) result;
                Object realResult = realFuture.get();
                activeFuture.finish(realResult);
            }
        } catch (Exception e) {
            if (activeFuture != null) {
                activeFuture.finish(null);
            }
        }
    }

    static class Builder {
        private Object[] objectArr;

        private Method method;

        private ActiveFuture<Object> activeFuture;

        private Object service;

        public Builder useMethod(Method method) {
            this.method = method;
            return this;
        }

        public Builder returnFuture(ActiveFuture<Object> activeFuture) {
            this.activeFuture = activeFuture;
            return this;
        }

        public Builder withObjectArr(Object[] objectArr) {
            this.objectArr = objectArr;
            return this;
        }

        public Builder forService(Object service) {
            this.service = service;
            return this;
        }

        public ActiveMessage build() {
            return new ActiveMessage(this);
        }
    }
}
