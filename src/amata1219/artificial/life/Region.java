package amata1219.artificial.life;

public class Region {

    private final Vector2d lesserBoundaryCorner, greaterBoundaryCorner;

    public Region(Vector2d lesserBoundaryCorner, Vector2d greaterBoundaryCorner) {
        this.lesserBoundaryCorner = lesserBoundaryCorner;
        this.greaterBoundaryCorner = greaterBoundaryCorner;
    }

    public boolean contains(int x, int z) {
        return lesserBoundaryCorner.x <= x && x <= greaterBoundaryCorner.x
                && lesserBoundaryCorner.z <= z && z <= greaterBoundaryCorner.z;
    }

    public void saturateInRegion(Vector2d vector) {
        vector.x = Math.max(Math.min(vector.x, greaterBoundaryCorner.x), lesserBoundaryCorner.x);
        vector.z = Math.max(Math.min(vector.z, greaterBoundaryCorner.z), lesserBoundaryCorner.z);
    }

}
