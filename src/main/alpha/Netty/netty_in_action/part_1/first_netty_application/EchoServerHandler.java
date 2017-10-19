package Netty.netty_in_action.part_1.first_netty_application;

import io.netty.buffer.Unpooled;
import io.netty.channel.*;

/**
 * Created by Administrator on 2017/10/16 0016.
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter{
    private static int num = 0;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        num++;
        System.out.println(num);
//        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();
//        int length1 = compositeByteBuf.readableBytes();
//        byte[] array1 = new byte[length1];
//        compositeByteBuf.getBytes(compositeByteBuf.readerIndex(), array1);

//        byte_level_operation in = (byte_level_operation)msg;
//        System.out.println("in.readerIndex: " + in.readerIndex());
//        System.out.println("in.writerIndex: " + in.writerIndex());
//        byte_level_operation buf = Unpooled.copiedBuffer("a",CharsetUtil.UTF_8);
//        System.out.println("buf.readerIndex: " + buf.readerIndex());
//        System.out.println("buf.writerIndex: " + buf.writerIndex());
//        in.writeBytes(buf);
//        System.out.println("in.readerIndex: " + in.readerIndex());
//        System.out.println("in.writerIndex: " + in.writerIndex());
//        System.out.println("buf.readerIndex: " + buf.readerIndex());
//        System.out.println("buf.writerIndex: " + buf.writerIndex());
//        compositeByteBuf.addComponent(in);
//        for (byte_level_operation byteBuf : compositeByteBuf){
//            System.out.println("CompositeByteBuf: " + byteBuf.toString());
//        }
//        if (in.hasArray()){ //消息在堆内存中
//            byte[] array = in.array();
//            int offset = in.arrayOffset() + in.readerIndex();
//            int length = in.readableBytes();
//            System.out.println("Heap buffer: " + array.toString());
//        } else { // 消息在磁盘上
//            int length = in.readableBytes();
//            byte[] array = new byte[length];
//            in.getBytes(in.readerIndex(), array);
//            System.out.println("Direct buffer: " + array.toString());
//        }
//        System.out.println("reveive message: " + in.toString(CharsetUtil.UTF_8));
//        System.out.println("buf: " + buf.toString(CharsetUtil.UTF_8));
//        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}