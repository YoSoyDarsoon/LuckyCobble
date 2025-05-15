package com.darsoon.luckycobble.datagen;

import com.darsoon.luckycobble.blocks.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class DatagenRecipeProvider extends FabricRecipeProvider {
    public DatagenRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
     offerCompactingRecipe(exporter, RecipeCategory.BREWING, ModBlocks.LUCKYCOBBLE_BLOCK.getLeft(), Items.STICK);
        offerCompactingRecipe(exporter, RecipeCategory.BREWING, ModBlocks.LUCKYCOBBLE_BLOCK_LEGENDARY.getLeft(), Items.ACACIA_BOAT);
        offerCompactingRecipe(exporter, RecipeCategory.BREWING, ModBlocks.LUCKYCOBBLE_BLOCK_ITEM.getLeft(), Items.SALMON);
     offerCompactingRecipe(exporter, RecipeCategory.BREWING, ModBlocks.LUCKYCOBBLE_BLOCK_ULTRABEAST.getLeft(), Items.SADDLE);
    }

}
