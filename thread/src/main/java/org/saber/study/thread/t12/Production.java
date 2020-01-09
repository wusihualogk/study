package org.saber.study.thread.t12;

/**
 * description:
 * 产品
 *
 * @author: saber
 * @date: 2020/1/7 10:13
 **/
public class Production extends InstructionBook {

    private final int proId;

    public Production(int proId) {
        this.proId = proId;
    }

    @Override
    protected void firstProcess() {
        System.out.println("产品 " + proId + " 执行第一道工序");
    }

    @Override
    protected void secondProcess() {
        System.out.println("产品 " + proId + " 执行第二道工序");
    }


    public int getProId() {
        return proId;
    }
}
