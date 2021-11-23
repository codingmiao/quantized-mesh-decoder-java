package cesiumterrain;

import cesiumterrain.pojo.IndexData;
import cesiumterrain.pojo.QuantizedMeshHeader;
import cesiumterrain.pojo.VertexData;
import org.wowtools.common.utils.ResourcesReader;

import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * @author liuyu
 * @date 2021/11/22
 */
public class CesiumHeightmapDecoder {

    /**
     * byte[]转long类型(小端)
     *
     * @param array
     * @return
     */
    public static long bytesToLongLittle(byte[] array) {
        return ((((long) array[0] & 0xff) << 0)
                | (((long) array[1] & 0xff) << 8)
                | (((long) array[2] & 0xff) << 16)
                | (((long) array[3] & 0xff) << 24)
                | (((long) array[4] & 0xff) << 32)
                | (((long) array[5] & 0xff) << 40)
                | (((long) array[6] & 0xff) << 48)
                | (((long) array[7] & 0xff) << 56));
    }

    public static double bytesToDoubleLittle(byte[] array) {
        long lb = bytesToLongLittle(array);
        return Double.longBitsToDouble(lb);
    }

    public static void main(String[] args) throws Exception {
        InputStream is = ResourcesReader.readStream("D:\\_test\\terrain\\10463.terrain");
        ByteBuffer bb = ByteBuffer.wrap(is.readAllBytes());
        QuantizedMeshHeader quantizedMeshHeader = new QuantizedMeshHeader(bb);
        VertexData vertexData = new VertexData(bb);
        IndexData triangleIndices = new IndexData(bb);
        System.out.println(quantizedMeshHeader);
    }
}
