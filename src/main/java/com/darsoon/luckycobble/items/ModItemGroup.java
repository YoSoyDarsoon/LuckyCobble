package com.darsoon.luckycobble.items;

import com.darsoon.luckycobble.LuckyCobble;
import com.darsoon.luckycobble.blocks.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {

    public static final ItemGroup LUCKYCOBBLE_ITEM_GROUP = registerItemGroup("luckycobble_group",
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemGroup.luckycobble.luckycobble_group"))
                    .icon(() -> new ItemStack(ModBlocks.LUCKYCOBBLE_BLOCK.getLeft().asItem()))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.LUCKYCOBBLE_BLOCK.getLeft().asItem());
                        entries.add(ModBlocks.LUCKYCOBBLE_BLOCK_LEGENDARY.getLeft().asItem());
                        entries.add(ModBlocks.LUCKYCOBBLE_BLOCK_ULTRABEAST.getLeft().asItem());
                        entries.add(ModBlocks.LUCKYCOBBLE_BLOCK_ITEM.getLeft().asItem());
                    })
                    .build()
    );



    private static ItemGroup registerItemGroup(String itemGroupId, ItemGroup itemGroup) {
        return Registry.register(Registries.ITEM_GROUP, Identifier.of(LuckyCobble.MOD_ID, itemGroupId), itemGroup);
    }


    public static void registerItemGroups() {
        LuckyCobble.LOGGER.info("Registrando grupos de item....");
    }
}
