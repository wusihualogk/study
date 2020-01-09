package org.saber.study.thread.t03;

/**
 * description:
 *
 * @author: saber
 * @date: 2019/12/23 15:23
 **/
public class ReadLock implements Lock {

    private ReadWriteLockImpl readWriteLock;

    ReadLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriteLock.getMUTEX()) {
            //有写入线程正在执行，或偏向写标识为true且写线程等待数>0，无法获取读锁
            while (readWriteLock.getWritingWriters() > 0
                    || (readWriteLock.isPreferWriter() && readWriteLock.getWaitingWriters() > 0)) {
                readWriteLock.getMUTEX().wait();
            }
            readWriteLock.incrementReadingReaders();
        }
    }

    @Override
    public void unlock() {
        synchronized (readWriteLock.getMUTEX()) {
            readWriteLock.decrementReadingReaders();
            //使写锁快速获取
            readWriteLock.setPreferWriter(true);
            readWriteLock.getMUTEX().notifyAll();
        }
    }
}
