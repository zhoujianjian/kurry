package com.zj.spring.demo.BeanFactoryPostProcessTest;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 用于 spring boot 自定义装配
 */
public class ImportSelectortTest implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[0];
    }
}
