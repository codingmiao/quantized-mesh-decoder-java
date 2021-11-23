package cesiumterrain.pojo;

import cesiumterrain.util.ByteBufferConverter;

import java.nio.ByteBuffer;

/**
 * https://github.com/CesiumGS/quantized-mesh
 *
 * @author liuyu
 * @date 2021/11/23
 */
public class QuantizedMeshHeader {

    // The center of the tile in Earth-centered Fixed coordinates.
    private final double centerX;
    private final double centerY;
    private final double centerZ;

    // The minimum and maximum heights in the area covered by this tile.
    // The minimum may be lower and the maximum may be higher than
    // the height of any vertex in this tile in the case that the min/max vertex
    // was removed during mesh simplification, but these are the appropriate
    // values to use for analysis or visualization.
    private final float minimumHeight;
    private final float maximumHeight;

    // The tile’s bounding sphere.  The X,Y,Z coordinates are again expressed
    // in Earth-centered Fixed coordinates, and the radius is in meters.
    private final double boundingSphereCenterX;
    private final double boundingSphereCenterY;
    private final double bBoundingSphereCenterZ;
    private final double boundingSphereRadius;

    // The horizon occlusion point, expressed in the ellipsoid-scaled Earth-centered Fixed frame.
    // If this point is below the horizon, the entire tile is below the horizon.
    // See http://cesiumjs.org/2013/04/25/Horizon-culling/ for more information.
    private final double horizonOcclusionPointX;
    private final double horizonOcclusionPointY;
    private final double horizonOcclusionPointZ;

    public QuantizedMeshHeader(ByteBuffer bb) {
        centerX = ByteBufferConverter.readDoubleLittle(bb);
        centerY = ByteBufferConverter.readDoubleLittle(bb);
        centerZ = ByteBufferConverter.readDoubleLittle(bb);

        minimumHeight = ByteBufferConverter.readFloatLittle(bb);
        maximumHeight = ByteBufferConverter.readFloatLittle(bb);

        boundingSphereCenterX = ByteBufferConverter.readDoubleLittle(bb);
        boundingSphereCenterY = ByteBufferConverter.readDoubleLittle(bb);
        bBoundingSphereCenterZ = ByteBufferConverter.readDoubleLittle(bb);
        boundingSphereRadius = ByteBufferConverter.readDoubleLittle(bb);

        horizonOcclusionPointX = ByteBufferConverter.readDoubleLittle(bb);
        horizonOcclusionPointY = ByteBufferConverter.readDoubleLittle(bb);
        horizonOcclusionPointZ = ByteBufferConverter.readDoubleLittle(bb);
    }


    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public double getCenterZ() {
        return centerZ;
    }

    public float getMinimumHeight() {
        return minimumHeight;
    }

    public float getMaximumHeight() {
        return maximumHeight;
    }

    public double getBoundingSphereCenterX() {
        return boundingSphereCenterX;
    }

    public double getBoundingSphereCenterY() {
        return boundingSphereCenterY;
    }

    public double getbBoundingSphereCenterZ() {
        return bBoundingSphereCenterZ;
    }

    public double getBoundingSphereRadius() {
        return boundingSphereRadius;
    }

    public double getHorizonOcclusionPointX() {
        return horizonOcclusionPointX;
    }

    public double getHorizonOcclusionPointY() {
        return horizonOcclusionPointY;
    }

    public double getHorizonOcclusionPointZ() {
        return horizonOcclusionPointZ;
    }
}
