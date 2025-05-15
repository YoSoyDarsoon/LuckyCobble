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
import java.util.Set;
import java.util.WeakHashMap;
import java.util.Collections;

public class LuckyCobbleLegendaryBlock extends Block {

    private static final WeakHashMap<World, Set<BlockPos>> recentlyUsed = new WeakHashMap<>();
    private static final String[] LEGENDARIES = {
            "Articuno", "Zapdos", "Moltres", "Mewtwo",
            "Raikou", "Entei", "Suicune", "Lugia", "Ho-Oh",
            "Regirock", "Regice", "Registeel", "Latias", "Latios",
            "Kyogre", "Groudon", "Rayquaza",
            "Uxie", "Mesprit", "Azelf", "Dialga", "Palkia",
            "Heatran", "Regigigas", "Giratina", "Cresselia",
            "Cobalion", "Terrakion", "Virizion", "Tornadus", "Thundurus",
            "Reshiram", "Zekrom", "Landorus", "Kyurem",
            "Xerneas", "Yveltal", "Zygarde",
            "TypeNull", "Silvally", "TapuKoko", "TapuLele", "TapuBulu", "TapuFini",
            "Cosmog", "Cosmoem", "Solgaleo", "Lunala", "Necrozma",
            "Zacian", "Zamazenta", "Eternatus", "Kubfu", "Urshifu",
            "Regieleki", "Regidrago", "Glastrier", "Spectrier", "Calyrex", "Enamorus",
            "WoChien", "ChienPao", "TingLu", "ChiYu",
            "Koraidon", "Miraidon", "Okidogi", "Munkidori", "Fezandipiti", "Ogerpon", "Terapagos", "Victini",
            "Keldeo", "Meloetta", "Genesect", "Diancie", "Hoopa", "Volcanion", "Magearna", "Marshadow",
            "Zeraora", "Meltan", "Melmetal", "Zarude", "Pecharunt", "Mew", "Celebi", "Jirachi", "Deoxys",
            "Phione", "Manaphy", "Darkrai", "Shaymin", "Arceus"




    };

    public LuckyCobbleLegendaryBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            spawnLegendaryPokemon(world, pos);
            recentlyUsed.computeIfAbsent(world, w -> Collections.newSetFromMap(new WeakHashMap<>())).add(pos);
            world.breakBlock(pos, false);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        if (!world.isClient()) {
            World serverWorld = (World) world;
            Set<BlockPos> used = recentlyUsed.get(serverWorld);
            if (used == null || !used.remove(pos)) {
                spawnLegendaryPokemon(serverWorld, pos);
            }
        }
        super.onBroken(world, pos, state);
    }

    private void spawnLegendaryPokemon(World world, BlockPos pos) {
        Random rand = new Random();
        int level = rand.nextInt(100) + 1;
        String pokemon = LEGENDARIES[rand.nextInt(LEGENDARIES.length)];
        String command = "/pokespawn " + pokemon + " lvl=" + level;

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