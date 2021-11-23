package cesiumterrain.pojo;

import cesiumterrain.util.ByteBufferConverter;

import java.nio.ByteBuffer;

/**
 * @author liuyu
 * @date 2021/11/23
 */
public class IndexData {
    private final int triangleCount;
    private final int[] indices;

    public IndexData(ByteBuffer bb) {
        triangleCount = ByteBufferConverter.readIntLittle(bb);
        indices = new int[triangleCount * 3];
        /*
         If tile has more than 65536 vertices, the tile uses the IndexData32 structure to encode indices.
         Otherwise, it uses the IndexData16 structure.
        * */
        if (triangleCount > 65536) {
            for (int i = 0; i < indices.length; ++i) {
                indices[i] = ByteBufferConverter.readIntLittle(bb);
            }
        } else {
            for (int i = 0; i < indices.length; ++i) {
                indices[i] = ByteBufferConverter.readUint16Little(bb);
            }
        }
        //Indices are encoded using the high water mark encoding from webgl-loader. Indices are decoded as follows:
        int highest = 0;
        for (int i = 0; i < indices.length; ++i) {
            int code = indices[i];
            indices[i] = highest - code;
            if (code == 0) {
                ++highest;
            }
        }
    }

    public int getTriangleCount() {
        return triangleCount;
    }

    public int[] getIndices() {
        return indices;
    }
}
