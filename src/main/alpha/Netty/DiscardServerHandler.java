package Netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import rabbitmq.Producer;
import threadPool.ThreadPool;

import java.io.IOException;
import java.util.concurrent.Executor;


/**
 * Created by Administrator on 2017/10/14 0014.
 */
@ChannelHandler.Sharable
public class DiscardServerHandler extends ChannelInboundHandlerAdapter{
    private Producer producer;
    private int channelNum;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // Discard the received data silently.
        //((byte_level_operation)msg).release();
        ByteBuf in = (ByteBuf)msg;
        String str = ByteBufUtil.hexDump(in);
        if (str.length() == 98) {
//            System.out.println(str);
            System.out.println(str.substring(26, 94));
//            str = str.substring(26,94);
//            System.out.println(str.substring(26, 94).length());

        }
        try {
            //do something
//            while (in.isReadable()){
//                System.out.println((char)in.readByte());
//                System.out.flush();
//            }
//            ctx.writeAndFlush(msg);
            producer.sendMessage(str);
        }finally {
            //ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    public DiscardServerHandler() {
        try {
            this.producer = new Producer("test-queue");
            System.out.println("DiscardServerHandler");
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        System.out.println("channel registered!");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        channelNum--;
        System.out.println("channel unregistered!");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        channelNum++;
        System.out.println("channel active!");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("channel inactive!");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        System.out.println("channel readComplete! channelNum is " + channelNum);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        System.out.println("user event triggered");
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
        System.out.println("channel writability changed");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        System.out.println("handler added");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        System.out.println("handler removed");
    }
}
