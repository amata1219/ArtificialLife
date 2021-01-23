package amata1219.artificial.life;

import java.util.HashSet;
import java.util.UUID;

public class ArtificialLife {

    public final UUID uniqueId = UUID.randomUUID();
    public final long creationTimeStamp = System.currentTimeMillis();
    public final byte[] gene;
    public final Vector2d position;
    public final HashSet<ArtificialLife> ignored = new HashSet<>();

    public ArtificialLife(byte[] gene, Vector2d position) {
        this.gene = gene;
        this.position = position;
    }

    @Override
    public int hashCode() {
        return uniqueId.hashCode();
    }

}
