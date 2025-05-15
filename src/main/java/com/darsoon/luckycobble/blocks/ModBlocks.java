package com.darsoon.luckycobble.blocks;

import com.darsoon.luckycobble.LuckyCobble;
import com.darsoon.luckycobble.blocks.custom.LuckyCobbleBlock;
import com.darsoon.luckycobble.blocks.custom.LuckyCobbleLegendaryBlock;
import com.darsoon.luckycobble.blocks.custom.LuckyCobbleItem;
import com.darsoon.luckycobble.blocks.custom.LuckyCobbleUltraBeastBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

public class ModBlocks {
    public static final  Pair<Block, Item> LUCKYCOBBLE_BLOCK_ULTRABEAST = registerBlock("luckycobbleultrabeast_block", new LuckyCobbleUltraBeastBlock(AbstractBlock.Settings.create().strength(0f).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final  Pair<Block, Item> LUCKYCOBBLE_BLOCK_LEGENDARY = registerBlock("luckycobblegendary_block", new LuckyCobbleLegendaryBlock(AbstractBlock.Settings.create().strength(0f).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final  Pair<Block, Item> LUCKYCOBBLE_BLOCK_ITEM = registerBlock("luckycobbleitem_block", new LuckyCobbleItem(AbstractBlock.Settings.create().strength(0f).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final  Pair<Block, Item> LUCKYCOBBLE_BLOCK = registerBlock("luckycobble_block", new LuckyCobbleBlock(AbstractBlock.Settings.create().strength(0f).requiresTool().sounds(BlockSoundGroup.METAL)));


   private static Pair<Block, Item> registerBlock(String name, Block block){
        return new Pair<>(
                Registry.register(Registries.BLOCK, Identifier.of(LuckyCobble.MOD_ID, name), block),
                Registry.register(Registries.ITEM, Identifier.of(LuckyCobble.MOD_ID, name), new BlockItem(block, new Item.Settings()))
        );
   }
  public static void registerBlocks(){
      LuckyCobble.LOGGER.info("Registrando bloques....");
  }
}
