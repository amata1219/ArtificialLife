package amata1219.artificial.life;

import java.util.*;

public class Main {

    private static final Random RANDOM = new Random();
    private static int step;
    private static final ArrayList<ArtificialLife> LIVES = new ArrayList<>();
    private static final Region WORLD_BORDER = new Region(new Vector2d(-4096, -4096), new Vector2d(4096, 4096));
    private static final ChunkMap<ArtificialLife> CHUNK_MAP = new ChunkMap<>();

    public static void main(String[] args) {
        addArtificialLife(0, 0);
        addArtificialLife(2048, 2048);
        addArtificialLife(4096, 4096);
        addArtificialLife(-2048, -2048);
        addArtificialLife(-4096, -4096);

        visualize();

        while (step <= 30000000) {
            if (step % 100000 == 0) System.out.println(step + ": " + LIVES.size());
            if (LIVES.size() > 10000) break;

            for (ArtificialLife life : LIVES) {
                removeArtificialLife(life);
                life.position.add(
                        RANDOM.nextInt(100) - RANDOM.nextInt(100),
                        RANDOM.nextInt(100) - RANDOM.nextInt(100)
                );
                WORLD_BORDER.saturateInRegion(life.position);
                putArtificialLife(life);
            }

            HashSet<ArtificialLife> alreadyComposed = new HashSet<>();
            for (ArtificialLife life : new ArrayList<>(LIVES)) if(!alreadyComposed.contains(life)) {
                for (ArtificialLife nearbyLife : CHUNK_MAP.get(life.position.x, life.position.z)) {
                    if (life == nearbyLife || !life.position.equals(nearbyLife.position) || life.ignored.contains(nearbyLife) || nearbyLife.ignored.contains(life)) continue;

                    ArtificialLife newLife = composeArtificialLife(life, nearbyLife);
                    putArtificialLife(newLife);

                    life.ignored.add(nearbyLife);
                    nearbyLife.ignored.add(life);

                    alreadyComposed.addAll(Arrays.asList(life, nearbyLife));
                    break;
                }
            }
            step++;
        }

        System.out.println(step + ": " + LIVES.size());
        LIVES.stream()
                .max(Comparator.comparingInt(l -> l.gene.length))
                .ifPresent(life -> System.out.println(life.uniqueId + ": " + Arrays.toString(life.gene) + ", length = " + life.gene.length));

        long[] sum = new long[6];
        for (ArtificialLife life : LIVES) {
            for (byte value : life.gene) sum[value]++;
        }

        for (int i = 0; i < sum.length; i++) System.out.println(i + " -> " + sum[i]);

        visualize();
    }

    private static void visualize() {
        for (int z = 4096; z >= -4096; z -= 1024) {
            for (int x = -4096; x <= 4096; x += 1024) {
                int size = CHUNK_MAP.get(x, z).size();
                System.out.print(size + ", ");
            }
            System.out.println();
        }
    }

    private static void addArtificialLife(int x, int z) {
        ArtificialLife life = new ArtificialLife(GeneOperations.generateRandomGene(2), new Vector2d(x, z));
        LIVES.add(life);
        putArtificialLife(life);
    }

    private static void putArtificialLife(ArtificialLife life) {
        CHUNK_MAP.put(life.position.x, life.position.z, life);
    }

    private static void removeArtificialLife(ArtificialLife life) {
        CHUNK_MAP.remove(life.position.x, life.position.z, life);
    }

    private static ArtificialLife composeArtificialLife(ArtificialLife l1, ArtificialLife l2) {
        byte[] geneComposed = GeneOperations.compose(l1.gene, l2.gene);
        ArtificialLife newLife = new ArtificialLife(geneComposed, new Vector2d(l1.position.x, l1.position.z));
        l1.ignored.add(newLife);
        l2.ignored.add(newLife);
        newLife.ignored.addAll(Arrays.asList(l1, l2));
        LIVES.add(newLife);
        return newLife;
    }

}
