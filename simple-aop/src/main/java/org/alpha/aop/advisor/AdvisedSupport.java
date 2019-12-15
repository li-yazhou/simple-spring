package org.alpha.aop.advisor;

import lombok.Data;
import org.alpha.aop.interceptor.AopMethodInterceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 描述目标对象和拦截器
 *
 * @author liyazhou1
 * @date 2019/12/15
 */
@Data
public class AdvisedSupport extends Advisor {

    private Object target;

    private List<AopMethodInterceptor> interceptorList = new ArrayList<>();


    public void addAopMethodInterceptor(AopMethodInterceptor... interceptors) {
        interceptorList.addAll(Arrays.asList(interceptors));
    }

}
