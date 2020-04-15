package PixelVision.Rendering;

import PixelVision.Math.Vec3;

public final class Frustum {

    public enum Plane {TOP, BOTTOM, LEFT, RIGHT, NEAR, FAR};

    public int ScreenSize;
    public float NearPlane, FarPlane;

    public Frustum() {

    }

    public Vec3 GetNormalToPlane(Plane p) {
        switch(p) {
            case TOP:
                return new Vec3(0, -1f, 0);
            case BOTTOM:
                return new Vec3(0, 1f, 0);
            case LEFT:
                return new Vec3(1f, 0, 0);
            case RIGHT:
                return new Vec3(-1f, 0, 0);
            case NEAR:
                return new Vec3(0, 0, -1f);
            case FAR:
                return new Vec3(0, 0, 1f);
            default:
                return null;
        }
    }

}
