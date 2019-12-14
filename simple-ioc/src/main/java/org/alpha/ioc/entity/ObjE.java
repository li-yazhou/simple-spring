package org.alpha.ioc.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * @author liyazhou1
 * @date 2019/12/14
 */

@Getter @Setter
public class ObjE {

    private String name;

    private ObjD d;


    public ObjE() {
        System.out.println("创建 E 对象，" + Instant.now());
    }


    @Override
    public String toString() {
        return "ObjE{" +
                "name='" + name + '\'' +
                ", d.name=" + d.getName() +
                '}';
    }
}
