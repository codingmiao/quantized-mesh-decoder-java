package cesiumterrain.util;

import java.nio.ByteBuffer;

/**
 * 从ByteBuffer当前位置读取java基本类型数据
 * https://www.cnblogs.com/hopeofthevillage/p/12917113.html
 *
 * @author liuyu
 * @date 2021/11/23
 */
public class ByteBufferConverter {

    /**
     * int的转换(小端)
     *
     * @param bb
     * @return
     */
    public static int readIntLittle(ByteBuffer bb) {
        int int1 = bb.get() & 0xff;
        int int2 = (bb.get() & 0xff) << 8;
        int int3 = (bb.get() & 0xff) << 16;
        int int4 = (bb.get() & 0xff) << 24;

        return int1 | int2 | int3 | int4;
    }

    /**
     * byte数组到int的转换(大端)
     *
     * @param bb
     * @return
     */
    public static int readIntBig(ByteBuffer bb) {
        int int4 = (bb.get() & 0xff) << 24;
        int int3 = (bb.get() & 0xff) << 16;
        int int2 = (bb.get() & 0xff) << 8;
        int int1 = bb.get() & 0xff;

        return int1 | int2 | int3 | int4;
    }


    /**
     * 读取小端byte数组为short
     *
     * @param bb
     * @return
     */
    public static short readShortLittle(ByteBuffer bb) {
        byte b0 = bb.get();
        byte b1 = bb.get();
        return (short) (((b1 << 8) | b0 & 0xff));
    }

    /**
     * 读取大端byte数组为short
     *
     * @param bb
     * @return
     */
    public static short readShortBig(ByteBuffer bb) {
        byte b0 = bb.get();
        byte b1 = bb.get();
        return (short) (((b0 << 8) | b1 & 0xff));
    }

    /**
     * 读long类型(大端)
     *
     * @param bb
     * @return
     */
    public static long readLongBig(ByteBuffer bb) {
        return ((((long) bb.get() & 0xff) << 56)
                | (((long) bb.get() & 0xff) << 48)
                | (((long) bb.get() & 0xff) << 40)
                | (((long) bb.get() & 0xff) << 32)
                | (((long) bb.get() & 0xff) << 24)
                | (((long) bb.get() & 0xff) << 16)
                | (((long) bb.get() & 0xff) << 8)
                | (((long) bb.get() & 0xff) << 0));
    }

    /**
     * 读long类型(小端)
     *
     * @param bb
     * @return
     */
    public static long readLongLittle(ByteBuffer bb) {
        return ((((long) bb.get() & 0xff) << 0)
                | (((long) bb.get() & 0xff) << 8)
                | (((long) bb.get() & 0xff) << 16)
                | (((long) bb.get() & 0xff) << 24)
                | (((long) bb.get() & 0xff) << 32)
                | (((long) bb.get() & 0xff) << 40)
                | (((long) bb.get() & 0xff) << 48)
                | (((long) bb.get() & 0xff) << 56));
    }

    /**
     * 读double类型(小端)
     *
     * @param bb
     * @return
     */
    public static double readDoubleLittle(ByteBuffer bb) {
        long l = readLongLittle(bb);
        return Double.longBitsToDouble(l);
    }


    /**
     * 读float类型(小端)
     *
     * @param bb
     * @return
     */
    public static float readFloatLittle(ByteBuffer bb) {
        int i = readIntLittle(bb);
        return Float.intBitsToFloat(i);
    }

    /**
     * 把int转Uint16
     *
     * @param bb
     * @return
     */
    public static int readUint16Little(ByteBuffer bb) {
        byte b0 = bb.get();
        byte b1 = bb.get();
        int i = (b0 & 0xff) | ((b1 << 8) & 0xff00);
        i = (i & 0x0000ffff);
        return i;
    }

    public static void main(String[] args) {
        byte b0 = -2;
        byte b1 = -1;
        int st = (b0 & 0xff) | ((b1 << 8) & 0xff00) - Short.MAX_VALUE;
        System.out.println(st);
    }
}
