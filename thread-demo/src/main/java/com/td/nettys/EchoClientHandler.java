package com.td.nettys;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * Created with IntelliJ IDEA.
 * User:  wanghongjie
 * Date:  2019/4/27 - 09:42
 * <p>
 * Description:
 */
@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {

        System.out.println(byteBuf.toString(CharsetUtil.UTF_8));
        byte[] tempBuf = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(tempBuf);
        System.out.println("netty client send msg is : " + new String(tempBuf, "UTF-8"));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

            ctx.writeAndFlush(Unpooled.copiedBuffer( "Hello Netty !",
                    CharsetUtil.UTF_8));

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}