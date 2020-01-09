package org.saber.study.thread.t12;

/**
 * description:
 * 组装指导书
 *
 * @author: saber
 * @date: 2020/1/7 10:11
 **/
public abstract class InstructionBook {

    public final void create() {
        firstProcess();
        secondProcess();
    }

    protected abstract void firstProcess();

    protected abstract void secondProcess();

}
