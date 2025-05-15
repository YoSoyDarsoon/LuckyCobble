package com.darsoon.luckycobble.datagen;

import com.darsoon.luckycobble.blocks.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

public class DatagenModelProvider extends FabricModelProvider {

    public DatagenModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
         blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LUCKYCOBBLE_BLOCK.getLeft());
         blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LUCKYCOBBLE_BLOCK_LEGENDARY.getLeft());
         blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LUCKYCOBBLE_BLOCK_ULTRABEAST.getLeft());
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LUCKYCOBBLE_BLOCK_ITEM.getLeft());

    }


    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
