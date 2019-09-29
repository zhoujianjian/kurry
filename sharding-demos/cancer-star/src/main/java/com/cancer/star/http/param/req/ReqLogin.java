package com.cancer.star.http.param.req;


import com.cancer.star.common.param.Insert;
import com.cancer.star.common.param.Update;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
/**
 * Created by zhoujian on 2018/7/5
 *
 * @Desc 类描述.
 */
@Data
public class ReqLogin implements Serializable {

    // 0 验证码 1 密码
    @NotNull  ( groups = Update.class )
    private Integer type = 0;

    @NotNull( groups = Insert.class)
    private String mobile;

    private String pwdOrCode;


}
