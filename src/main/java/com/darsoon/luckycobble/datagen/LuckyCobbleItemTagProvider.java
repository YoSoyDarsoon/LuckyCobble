package com.darsoon.luckycobble.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.item.Item;

import java.util.concurrent.CompletableFuture;

public class LuckyCobbleItemTagProvider extends FabricTagProvider<Item> {

    public LuckyCobbleItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ITEM, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registryLookup) {
        TagKey<Item> tag = TagKey.of(RegistryKeys.ITEM,  Identifier.of("luckycobble", "lucky_drops"));

        getOrCreateTagBuilder(tag)
                .add(Items.DIAMOND)
                .add(Items.GOLD_INGOT)
                .add(Items.EMERALD);
    }
}
