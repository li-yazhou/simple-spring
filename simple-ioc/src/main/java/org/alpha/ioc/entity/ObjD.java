package org.alpha.ioc.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * @author liyazhou1
 * @date 2019/12/14
 */
@Getter @Setter
public class ObjD {

    private String name;

    private ObjE e;

    public ObjD() {
        super();
        System.out.println("创建 D 对象，" + Instant.now());
    }

    @Override
    public String toString() {
        return "ObjD{" +
                "name='" + name + '\'' +
                ", e.name=" + e.getName() +
                '}';
    }
}
