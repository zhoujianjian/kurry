package com.zj.netty.channel;

/**
 * @ClassName: TestChannelnitializer
 * @Description: 描述
 * @Author: zhoujian
 * @Date: 2020/6/4$ 20:48$
 * @Version: 1.0
 */

import com.zj.netty.handler.TestHttpServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 初始化一个通道，主要用于设置各种Handler
 */
public class TestChannelnitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        /**
         * Handler就相当于Servlet中的过滤器, 请求和响应都会走Handler
         * HttpServerCodec: http的编解码器，用于Http请求和相应
         */
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        pipeline.addLast("testHttpServerHandler", new TestHttpServerHandler());
    }
}