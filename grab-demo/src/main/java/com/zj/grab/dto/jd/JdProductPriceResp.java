package com.zj.grab.dto.jd;

import lombok.Data;

import java.io.Serializable;

@Data
public class JdProductPriceResp implements Serializable {

    private static final long serialVersionUID = -1979006260085668045L;
    /**
     * 现价
     */
    private String price;
    /**
     * 原价
     */
    private String originPrice;


}
