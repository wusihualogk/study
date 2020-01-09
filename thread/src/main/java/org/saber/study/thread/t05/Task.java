package org.saber.study.thread.t05;

/**
 * description:
 *
 * @author: saber
 * @date: 2019/12/24 11:46
 **/
public interface Task<IN, OUT> {

    OUT get(IN in);

}
