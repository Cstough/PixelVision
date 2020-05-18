package PixelVision.Animation;

import PixelVision.Math.Point3;

public class Bone {
    //0 is the root node
    //1 is at length _length from 0 in direction _direction
    SkeletalNode[] _nodes;
    float _length;
    Point3 _direction;

    Bone _parent;

    public Bone() {
        _nodes = new SkeletalNode[2];
        _length = 0.0f;
        _direction = new Point3(0, 1, 0);
        _parent = null;
    }

    public Bone(SkeletalNode[] nodes, float length, Point3 direction, Bone parent) {
        _nodes = nodes;
        _length = length;
        _direction = direction;
        _parent = parent;
    }

    public SkeletalNode[] GetNodes() {
        return _nodes;
    }

    public float GetLength() {
        return _length;
    }

    public Point3 GetDirection() {
        return _direction;
    }

    public Bone GetParent() {
        return _parent;
    }
}
