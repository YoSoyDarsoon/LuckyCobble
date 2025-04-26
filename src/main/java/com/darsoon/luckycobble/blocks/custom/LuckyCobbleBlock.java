package com.darsoon.luckycobble.blocks.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.Random;
import java.util.WeakHashMap;

public class LuckyCobbleBlock extends Block {

    // Simple in-memory tracker to avoid double execution
    private static final WeakHashMap<World, java.util.Set<BlockPos>> recentlyUsed = new WeakHashMap<>();

    public LuckyCobbleBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            spawnRandomPokemon(world, pos);
            // Mark position as handled
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
                spawnRandomPokemon(serverWorld, pos);
            }
        }
        super.onBroken(world, pos, state);
    }

    private void spawnRandomPokemon(World world, BlockPos pos) {
        int level = new Random().nextInt(100) + 1;
        String command = "/pokespawn random lvl=" + level;

        ServerCommandSource source = new ServerCommandSource(
                world.getServer(),
                Vec3d.ofCenter(pos),
                Vec2f.ZERO,
                (ServerWorld) world,
                4,
                "@LuckyCobble",
                null,
                world.getServer(),
                null
        );

        world.getServer().getCommandManager().executeWithPrefix(source, command);
    }
}
