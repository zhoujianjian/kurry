package com.zj.netty;

import com.zj.netty.channel.TestChannelnitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @ClassName: ServerTest
 * @Description: 描述
 * @Author: zhoujian
 * @Date: 2020/6/4$ 20:47$
 * @Version: 1.0
 */
public class ServerTest {

    public static void main(String[] args) throws InterruptedException {
        /**
         * bossGroup, 父类的事件循环组只是负责连接，获取到连接后交给 workergroup子的事件循环组，
         * 参数的获取，业务的处理等工作均是由workergroup这个子事件循环组来完成，一个事件循环组一样
         * 可以完成所有的工作，但是Netty推荐的方式是使用两个事件循环组。
         *  //创建父事件循环组
         *  //创建子类的事件循环组
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            //创建启动服务器的对象
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            /**
             * group方法接收两个参数， 第一个为父时间循环组，第二个参数为子事件循环组
             *  //workerGroup的处理器，
             * //bossGroup的通道，只是负责连接
             */
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new TestChannelnitializer());
            //绑定端口
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();

        }finally{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
