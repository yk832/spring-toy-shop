package com.insung.core.findbean;

import com.insung.core.common.config.MyBatisConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class findAllBean {

    @Test
    void findAllBean() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MyBatisConfig.class);
        MyBatisConfig bean = ac.getBean(MyBatisConfig.class);

        Assertions.assertThat(bean).isInstanceOf(MyBatisConfig.class);
    }
}
