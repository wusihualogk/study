package org.saber.study.thread.t01;

/**
 * description:
 *
 * @author: saber
 * @date: 2019/12/23 11:41
 **/
public class ObservableThread<T> extends Thread implements Observable {

    private Cycle cycle;

    private final TaskLifecycle<T> lifecycle;

    private final Task<T> task;

    public ObservableThread(Task<T> task) {
        this(new EmptyLifecycle<T>(), task);
    }

    public ObservableThread(TaskLifecycle<T> taskLifecycle, Task<T> task) {
        super();
        this.lifecycle = taskLifecycle;
        this.task = task;
    }

    @Override
    public Cycle getCycle() {
        return cycle;
    }

    @Override
    public void run() {
        update(Cycle.STARTED, null, null);
        try {
            update(Cycle.RUNNING, null, null);
            T result = task.call();
            update(Cycle.DONE, result, null);
        } catch (Exception e) {
            update(Cycle.ERROR, null, e);
        }
    }

    private void update(Cycle cycle, T result, Exception e) {
        this.cycle = cycle;
        if (this.lifecycle == null)
            return;

        try {
            switch (cycle) {
                case STARTED:
                    this.lifecycle.onStart(currentThread());
                    break;
                case RUNNING:
                    this.lifecycle.onRunning(currentThread());
                    break;
                case ERROR:
                    this.lifecycle.onError(currentThread(), e);
                    break;
                case DONE:
                    this.lifecycle.onFinish(currentThread(), result);
                    break;
            }
        } catch (Exception ex) {
            if (cycle == Cycle.ERROR) {
                throw ex;
            }
        }
    }

}