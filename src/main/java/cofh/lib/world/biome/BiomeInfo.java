package cofh.lib.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.TempCategory;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

import java.util.Collection;
import java.util.Random;

public class BiomeInfo {

    private final Object data;
    private final boolean whitelist;
    private final int type;
    private final int hash;

    public BiomeInfo(String name) {

        data = name;
        hash = name.hashCode();
        whitelist = true;
        type = 0;
    }

    public BiomeInfo(Object d, int t, boolean wl) {

        data = d;
        hash = 0;
        whitelist = wl;
        type = t;
    }

    @SuppressWarnings("unchecked")
    public boolean isBiomeEqual(Biome biome, Random rand) {

        if (biome != null) {
            switch (type) {
                default:
                    break;
                case 0:
                    String name = biome.getBiomeName();
                    return name.hashCode() == hash && name.equals(data);
                case 1:
                    return biome.getTempCategory() == data == whitelist;
                case 2:
                    return BiomeDictionary.isBiomeOfType(biome, (Type) data) == whitelist;
                case 4:
                    return ((Collection<String>) data).contains(biome.getBiomeName());
                case 5:
                    return ((Collection<TempCategory>) data).contains(biome.getTempCategory()) == whitelist;
                case 6:
                    Type[] d = (Type[]) data;
                    int c = 0, e = d.length;
                    for (int i = 0; i < e; ++i) {
                        if (BiomeDictionary.isBiomeOfType(biome, d[i])) {
                            ++c;
                        }
                    }
                    return c == e == whitelist;
            }
        }
        return !whitelist;
    }

}
