package amata1219.artificial.life;

import java.util.Random;

public class GeneOperations {

    private static final Random RANDOM = new Random();

    public static byte[] generateRandomGene(int length) {
        byte[] gene = new byte[length];
        for (int i = 0; i < gene.length; i++) gene[i] = (byte) RANDOM.nextInt(5);
        return gene;
    }

    public static byte[] compose(byte[] g1, byte[] g2) {
        byte[] gene = new byte[Math.round((g1.length + g2.length) / 2.0f) + 1];
        for (int i = 0; i < gene.length; i++) {
            if (Math.random() < 0.0001) gene[i] = 5;
            else {
                if (RANDOM.nextBoolean()) {
                    if (g1.length <= i) {
                        if (g2.length <= i) gene[i] = (byte) RANDOM.nextInt(5);
                        else gene[i] = g2[i];
                    } else gene[i] = g1[i];
                } else {
                    if (g2.length <= i) {
                        if (g1.length <= i) gene[i] = (byte) RANDOM.nextInt(5);
                        else gene[i] = g1[i];
                    } else gene[i] = g2[i];
                }
            }
        }
        return gene;
    }

}
