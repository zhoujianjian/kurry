package com.zj.spring.demo.spring.test.importsss.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * 用于 spring boot 自定义装配
 */
@Component
public class ImportSelectortTest implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        String[] strings = {SelectorTestServiceOne.class.getName(), SelectorTestServiceTwo.class.getName()};
        return strings;
    }

    public static void main(String[] args) {
        String[] temp = "xxx^XX;

    }
}
