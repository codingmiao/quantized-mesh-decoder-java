package cesiumterrain.pojo;

import cesiumterrain.util.ByteBufferConverter;

import java.nio.ByteBuffer;

/**
 * @author liuyu
 * @date 2021/11/23
 */
public class VertexData {

    private final int vertexCount;
    private final int[] u;//Uint16
    private final int[] v;//Uint16
    private final int[] height;//Uint16

    public VertexData(ByteBuffer bb) {
        vertexCount = ByteBufferConverter.readIntLittle(bb);
        u = new int[vertexCount];
        v = new int[vertexCount];
        height = new int[vertexCount];
        int u = 0;
        int v = 0;
        int height = 0;


        for (int i = 0; i < vertexCount; ++i) {
            u += decodeZigZag(ByteBufferConverter.readUint16Little(bb));
            this.u[i] = u;
        }
        for (int i = 0; i < vertexCount; ++i) {
            v += decodeZigZag(ByteBufferConverter.readUint16Little(bb));
            this.v[i] = v;
        }

        for (int i = 0; i < vertexCount; ++i) {
            height += decodeZigZag(ByteBufferConverter.readUint16Little(bb));
            this.height[i] = height;
        }
    }

    private int decodeZigZag(int value) {
        return (short) ((value >> 1) ^ (-(value & 1)));
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int[] getU() {
        return u;
    }

    public int[] getV() {
        return v;
    }

    public int[] getHeight() {
        return height;
    }
}
