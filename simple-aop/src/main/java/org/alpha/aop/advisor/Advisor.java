package org.alpha.aop.advisor;


import lombok.Data;

/**
 * 通知者，包括Advice和Pointcut，用来描述在哪里和干什么
 *
 * @author liyazhou1
 * @date 2019/12/15
 */
@Data
public class Advisor {

    private Advice advice;

    private Pointcut pointcut;

}
