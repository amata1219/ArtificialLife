package amata1219.artificial.life;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ChunkMap<V> {

    private final HashMap<Long, ArrayList<V>> state = new HashMap<>();

    public void put(long x, long z, V value) {
        long hash = hash(x, z);
        ArrayList<V> list = state.computeIfAbsent(hash, k -> new ArrayList<>());
        list.add(value);
    }

    public void remove(long x, long z, V value) {
        long hash = hash(x, z);
        if (!state.containsKey(hash)) return;

        ArrayList<V> list = state.get(hash);
        list.remove(value);

        if (list.isEmpty()) state.remove(hash);
    }

    public List<V> get(long x, long z) {
        long hash = hash(x, z);
        return state.containsKey(hash) ? state.get(hash) : Collections.emptyList();
    }

    private static long hash(long x, long z) {
        return ((x >> 10) << 32) ^ (z >> 10);
    }

}
