package org.saber.study.thread.t03;

/**
 * description:
 *
 * @author: saber
 * @date: 2019/12/23 15:06
 **/
public interface ReadWriteLock {

    Lock readLock();

    Lock writeLock();

    /**
     * 获取当前有多少线程正在执行写操作
     *
     * @return
     */
    int getWritingWriters();

    /**
     * 获取当前有多少线程正在等待获取写入锁
     *
     * @return
     */
    int getWaitingWriters();

    /**
     * 获取当前有多少线程正在等待获取读取锁
     *
     * @return
     */
    int getReadingReaders();

    /**
     * 工厂方法，创建ReadWriteLock
     *
     * @return
     */
    static ReadWriteLock readWriteLock() {
        return new ReadWriteLockImpl();
    }

    static ReadWriteLock readWriteLock(boolean preferWriter) {
        return new ReadWriteLockImpl(preferWriter);
    }
}
