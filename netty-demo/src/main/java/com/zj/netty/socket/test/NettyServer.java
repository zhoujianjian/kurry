package com.zj.netty.socket.test;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @ClassName: NettyServer
 * @Description: 描述
 * @Author: zhoujian
 * @Date: 2020/7/10$ 14:22$
 * @Version: 1.0
 */

public class NettyServer {
    /**
     * boss 线程组，用于服务端接受客户端的连接
     */
    private EventLoopGroup bossGroup = new NioEventLoopGroup();
    /**
     * worker 线程组，用于服务端接受客户端的数据读写
     */
    private EventLoopGroup workerGroup = new NioEventLoopGroup();

}
