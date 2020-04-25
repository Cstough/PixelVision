package PixelVision.Math;

public final class Mat4x4 {
	
	private float[][] components; //component 0,0 is the upper left corner, component 3,0 is upper right corner (just like screen coords)
	
	private Mat4x4(float[][] components) {
		this.components = components;
	}
	
	public static Mat4x4 GetIdentity() {
		
		float[][] comps = new float[4][4];
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(i == j)
					comps[i][j] = 1f;
				else
					comps[i][j] = 0f;
			}
		}
		
		return new Mat4x4(comps);
		
	}
	
	public static Mat4x4 GetProjection(float fov, float near, float far) {
		Mat4x4 proj = GetIdentity();
		
		float s = ((float)Math.tan((fov / 2f) * (Math.PI / 180f)));
		
		proj.components[0][0] = s;
		proj.components[1][1] = s;
		proj.components[2][2] = -1f * ((far + near)/(far - near));
		proj.components[2][3] = -1f * ((2f * far * near)/(far - near));
		proj.components[3][2] = -1f;
		proj.components[3][3] = 0f;
		
		return proj;
	}
	
	public static Mat4x4 GetOrthographic() {
		return null;
	}
	
	public static Mat4x4 GetTranslation(Vec3 trans) {
		Mat4x4 res = GetIdentity();
		
		res.components[0][3] = trans.x;
		res.components[1][3] = trans.y;
		res.components[2][3] = trans.z;
		
		return res;
	}
	
	public static Mat4x4 GetScale(Vec3 scale) {
		Mat4x4 s = GetIdentity();
		
		s.components[0][0] = scale.x;
		s.components[1][1] = scale.y;
		s.components[2][2] = scale.z;
		
		return s;
	}
	
	public static Mat4x4 GetRotation(Vec3 rotation) {
		Mat4x4 x, y, z;
		
		//X matrix
		x = GetIdentity();
		
		float sin = (float)Math.sin(rotation.x);
		float cos = (float)Math.cos(rotation.x);
		
		x.components[1][1] = cos;
		x.components[2][1] = -sin;
		x.components[1][2] = sin;
		x.components[2][2] = cos;
		
		//Y Matrix
		y = GetIdentity();
		
		sin = (float)Math.sin(rotation.y);
		cos = (float)Math.cos(rotation.y);
		
		y.components[0][0] = cos;
		y.components[2][0] = sin;
		y.components[0][2] = -sin;
		y.components[2][2] = cos;
		
		//Z Matrix
		z = GetIdentity();
		
		sin = (float)Math.sin(rotation.z);
		cos = (float)Math.cos(rotation.z);
		
		z.components[0][0] = cos;
		z.components[0][1] = -sin;
		z.components[1][0] = sin;
		z.components[1][1] = cos;
		
		return Mat4x4.Mul(Mat4x4.Mul(x, y), z);
	}
	
	public static Vec3 Mul(Mat4x4 m, Vec3 v) {
		float[][] mat = m.components;
		Vec3 res = new Vec3();
		
		res.x = v.x * mat[0][0] + v.y * mat[0][1] + v.z * mat[0][2] + mat[0][3];
		res.y = v.x * mat[1][0] + v.y * mat[1][1] + v.z * mat[1][2] + mat[1][3];
		res.z = v.x * mat[2][0] + v.y * mat[2][1] + v.z * mat[2][2] + mat[2][3];
		res.w = v.x * mat[3][0] + v.y * mat[3][1] + v.z * mat[3][2] + mat[3][3];

		return res;
	}

	public static Point3 Mul(Mat4x4 m, Point3 p) {
		float[][] mat = m.components;
		Point3 res = new Point3();

		res.x = p.x * mat[0][0] + p.y * mat[0][1] + p.z * mat[0][2] + mat[0][3];
		res.y = p.x * mat[1][0] + p.y * mat[1][1] + p.z * mat[1][2] + mat[1][3];
		res.z = p.x * mat[2][0] + p.y * mat[2][1] + p.z * mat[2][2] + mat[2][3];
		res.w = p.x * mat[3][0] + p.y * mat[3][1] + p.z * mat[3][2] + mat[3][3];

		return res;
	}
	
	public static Mat4x4 Mul(Mat4x4 m1, Mat4x4 m2) {
		
		float[][] mat1 = m1.components;
		float[][] mat2 = m2.components;
		
		float[][] res = new float[4][4];
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				res[i][j] = 0;
				for(int k = 0; k < 4; k++) {
					res[i][j] += mat1[i][k]*mat2[k][j];
				}
			}
		}
		
		return new Mat4x4(res);
	}
	
	public String toString() {
		String out = "";
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				out += String.valueOf(components[i][j]) + " ";
			}
			out += "\n";
		}
		
		return out;
	}
}
