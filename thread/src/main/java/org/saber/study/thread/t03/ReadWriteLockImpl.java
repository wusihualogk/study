package org.saber.study.thread.t03;

/**
 * description:
 *
 * @author: saber
 * @date: 2019/12/23 15:13
 **/
public class ReadWriteLockImpl implements ReadWriteLock {

    private final Object MUTEX = new Object();

    private int writingWrites = 0;

    private int waitingWrites = 0;

    private int readingReaders = 0;

    private boolean preferWriter;

    public ReadWriteLockImpl() {
        this(true);
    }

    public ReadWriteLockImpl(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    @Override
    public Lock readLock() {
        return new ReadLock(this);
    }

    @Override
    public Lock writeLock() {
        return new WriteLock(this);
    }

    @Override
    public int getWritingWriters() {
        return writingWrites;
    }

    @Override
    public int getWaitingWriters() {
        return waitingWrites;
    }

    @Override
    public int getReadingReaders() {
        return readingReaders;
    }

    void incrementWritingWriters() {
        writingWrites++;
    }

    void incrementWaitingWriters() {
        waitingWrites++;
    }

    void incrementReadingReaders() {
        readingReaders++;
    }

    void decrementWritingWriters() {
        writingWrites--;
    }

    void decrementWaitingWriters() {
        waitingWrites--;
    }

    void decrementReadingReaders() {
        readingReaders--;
    }

    public Object getMUTEX() {
        return MUTEX;
    }

    public boolean isPreferWriter() {
        return preferWriter;
    }

    public void setPreferWriter(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }
}
