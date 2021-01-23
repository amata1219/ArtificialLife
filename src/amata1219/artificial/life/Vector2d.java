package amata1219.artificial.life;

public class Vector2d {

    public int x, z;

    public Vector2d(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public void zero() {
        x = z = 0;
    }

    public void add(int x, int z) {
        this.x += x;
        this.z += z;
    }

    public void add(Vector2d vector) {
        add(vector.x, vector.z);
    }

    public boolean equals(Vector2d vector) {
        return x == vector.x && z == vector.z;
    }

    @Override
    public String toString() {
        return "x = " + x + ", z = " + z;
    }
}
