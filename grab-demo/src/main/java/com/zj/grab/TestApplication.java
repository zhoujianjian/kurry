package com.zj.grab;

import com.zj.grab.constants.GrabGoodEnum;
import com.zj.grab.service.GrabGoodService;

/**
 * @author: zhoujian
 * @Date: 2019/10/8 11:55
 * @Company: youanmi.
 * @Desc:
 */
public class TestApplication {


    public static void main(String[] args) {
      GrabGoodService grabGoodService =  GrabGoodEnum.getGrabGoodService(GrabGoodEnum.BAR_CODE);
      grabGoodService.grabGoods("6922266454295");
    }
}
