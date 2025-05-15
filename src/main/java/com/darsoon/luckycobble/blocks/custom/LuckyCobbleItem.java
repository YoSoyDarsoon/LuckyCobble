package com.darsoon.luckycobble.blocks.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.List;
import java.util.Random;
import java.util.WeakHashMap;
import java.util.stream.Collectors;

public class LuckyCobbleItem extends Block {

    private static final WeakHashMap<World, java.util.Set<BlockPos>> recentlyUsed = new WeakHashMap<>();

    private static final TagKey<Item> DROPPABLE_ITEMS_TAG = TagKey.of(RegistryKeys.ITEM,  Identifier.of("luckycobble", "lucky_drops"));

    public LuckyCobbleItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, net.minecraft.util.hit.BlockHitResult hit) {
        if (!world.isClient) {
            dropRandomItem((ServerWorld) world, pos);
            recentlyUsed.computeIfAbsent(world, w -> java.util.Collections.newSetFromMap(new java.util.WeakHashMap<>())).add(pos);
            world.breakBlock(pos, false);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        if (!world.isClient()) {
            World serverWorld = (World) world;
            java.util.Set<BlockPos> used = recentlyUsed.get(serverWorld);
            if (used == null || !used.remove(pos)) {
                dropRandomItem((ServerWorld) serverWorld, pos);
            }
        }
        super.onBroken(world, pos, state);
    }

    private void dropRandomItem(ServerWorld world, BlockPos pos) {
        List<Item> items = Registries.ITEM.stream()
                .filter(item -> Registries.ITEM.getEntry(item).isIn(DROPPABLE_ITEMS_TAG))
                .collect(Collectors.toList());

        if (items.isEmpty()) {
            System.out.println("[LuckyCobble] No items found in tag 'lucky_drops'. Using fallback.");
            ItemStack fallback = new ItemStack(Items.COBBLESTONE);
            ItemEntity fallbackEntity = new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, fallback);
            world.spawnEntity(fallbackEntity);
            return;
        }

        System.out.println("[LuckyCobble] Found items in tag:");
        for (Item item : items) {
            System.out.println(" - " + Registries.ITEM.getId(item));
        }

        Item randomItem = items.get(new Random().nextInt(items.size()));
        ItemStack stack = new ItemStack(randomItem);
        ItemEntity entity = new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, stack);

        System.out.println("[LuckyCobble] Dropped item: " + randomItem);
        world.spawnEntity(entity);
    }
}