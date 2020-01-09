package org.saber.study.thread.t12;

import java.util.stream.IntStream;

/**
 * description:
 * 产品传送带
 *
 * @author: saber
 * @date: 2020/1/7 10:16
 **/
public class ProductionChannel {

    /**
     * 产品传送带最多产品数量
     */
    private final static int MAX_PROD = 100;

    /**
     * 传送带上的所有待加工的产品
     */
    private final Production[] productionQueue;

    /**
     * 队尾
     */
    private int tail;

    /**
     * 队头
     */
    private int head;

    /**
     * 待加工产品数量
     */
    private int total;

    /**
     * 流水线工人
     */
    private final Worker[] worker;

    public ProductionChannel(int workerSize) {
        worker = new Worker[workerSize];
        productionQueue = new Production[MAX_PROD];
        IntStream.range(0, workerSize).forEach((i) -> {
            worker[i] = new Worker("Worker-" + i, this);
            worker[i].start();
        });
    }

    public void offerProduction(Production production) {
        synchronized (this) {
            while (total >= productionQueue.length) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            productionQueue[tail] = production;
            tail = (tail + 1) % MAX_PROD;
            total++;
            notifyAll();
        }
    }

    public Production takeProduction() {
        synchronized (this) {
            while (total <= 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Production production = productionQueue[head];
            head = (head + 1) % MAX_PROD;
            total--;
            notifyAll();
            return production;
        }
    }
}
