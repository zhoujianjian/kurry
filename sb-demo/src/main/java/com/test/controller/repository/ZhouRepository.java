package com.test.controller.repository;

import com.test.controller.po.Zhou;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ZhouRepository
 * @Description: 描述
 * @Author: zhoujian
 * @Date: 2020/7/31$ 17:39$
 * @Version: 1.0
 */
@Component
public interface ZhouRepository extends ElasticsearchRepository<Zhou,Long> {
}
