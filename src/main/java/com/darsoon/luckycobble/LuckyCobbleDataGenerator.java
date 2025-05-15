package com.darsoon.luckycobble;

import com.darsoon.luckycobble.datagen.DatagenModelProvider;
import com.darsoon.luckycobble.datagen.DatagenRecipeProvider;
import com.darsoon.luckycobble.datagen.LuckyCobbleItemTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class LuckyCobbleDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(DatagenModelProvider::new);
        pack.addProvider(DatagenRecipeProvider::new);
        pack.addProvider(LuckyCobbleItemTagProvider::new);

    }
}
