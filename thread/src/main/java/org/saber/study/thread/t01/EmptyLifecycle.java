package org.saber.study.thread.t01;

/**
 * description:
 *
 * @author: saber
 * @date: 2019/12/23 11:39
 **/
class EmptyLifecycle<T> implements TaskLifecycle {

    @Override
    public void onStart(Thread thread) {
        System.out.println(thread.getName() + "开始");
    }

    @Override
    public void onRunning(Thread thread) {
        System.out.println(thread.getName() + "运行");
    }

    @Override
    public void onFinish(Thread thread, Object result) {
        System.out.println(thread.getName() + "完成");
    }

    @Override
    public void onError(Thread thread, Exception e) {
        System.out.println(thread.getName() + "异常");
    }
}
