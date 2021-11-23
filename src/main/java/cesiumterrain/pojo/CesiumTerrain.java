package cesiumterrain.pojo;

import java.nio.ByteBuffer;

/**
 * @author liuyu
 * @date 2021/11/23
 */
public class CesiumTerrain {

    /**
     * 瓦片被切割成了(n+1) * (n+1)个单元格
     */
    private static final int n = 32767;

    private final QuantizedMeshHeader quantizedMeshHeader;
    private final VertexData vertexData;
    private final IndexData triangleIndices;

    public CesiumTerrain(ByteBuffer bb) {
        quantizedMeshHeader = new QuantizedMeshHeader(bb);
        vertexData = new VertexData(bb);
        triangleIndices = new IndexData(bb);
        //TODO  三角面构建rtree
        int[] indices = triangleIndices.getIndices();
        for (int i = 0; i < indices.length; i += 3) {
            int i1 = i, i2 = i1 + 1, i3 = i1 + 2;//三角面三个顶点序号
            if (i1 == i2 || i2 == i3 || i3 == i1) {//三角形上有相同的点则认为是垃圾数据,跳过
                continue;
            }
        }
    }

    /**
     * 获取此瓦片上指定坐标的高程
     *
     * @param relativelyX 相对x坐标 [0,1] 左上角开始
     * @param relativelyY 相对y坐标 [0,1] 左上角开始
     * @return
     */
    public double getElevation(double relativelyX, double relativelyY) {


        double min = quantizedMeshHeader.getMinimumHeight();
        double max = quantizedMeshHeader.getMaximumHeight();

    }
}
