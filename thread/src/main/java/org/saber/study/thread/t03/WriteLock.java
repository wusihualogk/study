package org.saber.study.thread.t03;

/**
 * description:
 *
 * @author: saber
 * @date: 2019/12/23 15:34
 **/
public class WriteLock implements Lock {

    ReadWriteLockImpl readWriteLock;

    WriteLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriteLock.getMUTEX()) {
            try {
                readWriteLock.incrementWaitingWriters();
                while (readWriteLock.getReadingReaders() > 0 || readWriteLock.getWritingWriters() > 0) {
                    readWriteLock.getMUTEX().wait();
                }
            } finally {
                readWriteLock.decrementWaitingWriters();
            }
            readWriteLock.incrementWritingWriters();
        }
    }

    @Override
    public void unlock() {
        synchronized (readWriteLock.getMUTEX()) {
            readWriteLock.decrementWritingWriters();
            //使读锁快速获取
            readWriteLock.setPreferWriter(false);
            readWriteLock.getMUTEX().notifyAll();
        }
    }
}
