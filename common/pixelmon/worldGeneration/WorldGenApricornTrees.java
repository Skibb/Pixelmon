package pixelmon.worldGeneration;

import java.util.Random;

import pixelmon.WorldHelper;
import pixelmon.config.ApricornTreeBlocks;
import pixelmon.config.PixelmonBlocks;
import pixelmon.enums.EnumApricornTrees;
import pixelmon.enums.EnumBiomes;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenApricornTrees implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.worldType == -1)  // if nether
			return;
		BiomeGenBase biome = world.getBiomeGenForCoords(chunkX * 16, chunkZ * 16);
		if (biome == EnumBiomes.Forest.getBiome() || biome == EnumBiomes.ForestHills.getBiome() || biome == EnumBiomes.Taiga.getBiome() || biome == EnumBiomes.TaigaHills.getBiome()) {
			for (int i = 0; i < 2; i++) {
				int x = random.nextInt(16) + chunkX * 16;
				int z = random.nextInt(16) + chunkZ * 16;
				int y = world.getHeightValue(x, z);

				int blockId = world.getBlockId(x, y-1, z);
				if (blockId == Block.grass.blockID || blockId == Block.dirt.blockID) {
					world.setBlockWithNotify(x, y, z, ApricornTreeBlocks.apricornTreeRed.blockID);
					world.setBlockMetadataWithNotify(x, y, z, EnumApricornTrees.RedTree.modelList.length - 1);
				}
			}
		}

	}
}