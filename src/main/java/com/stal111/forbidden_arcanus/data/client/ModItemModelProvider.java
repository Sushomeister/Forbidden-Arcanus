package com.stal111.forbidden_arcanus.data.client;

import com.stal111.forbidden_arcanus.ForbiddenArcanus;
import com.stal111.forbidden_arcanus.block.ObsidianSkullBlock;
import com.stal111.forbidden_arcanus.init.NewModItems;
import com.stal111.forbidden_arcanus.init.NewerModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;
import net.valhelsia.valhelsia_core.data.ValhelsiaItemModelProvider;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

/**
 * Mod Item Model Provider
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.data.client.ModItemModelProvider
 *
 * @author stal111
 * @version 16.2.0
 * @since 2021-01-26
 */
public class ModItemModelProvider extends ValhelsiaItemModelProvider {

    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ForbiddenArcanus.REGISTRY_MANAGER, existingFileHelper);
    }

    @Override
    protected void register(Set<RegistryObject<Item>> items, Set<RegistryObject<Item>> blockItems) {
        //Items
        items.removeAll(Arrays.asList(NewModItems.LENS_OF_VERITATIS, NewModItems.OBSIDIAN_SKULL_SHIELD));
        forEach(items, this::simpleModel);

        //Block Items
        forEach(blockItems, item -> ((BlockItem) item).getBlock() instanceof ObsidianSkullBlock, item -> {});
        takeBlockItem(blockItems, this::simpleModel, NewerModBlocks.PIXIE_UTREM_JAR, NewerModBlocks.CORRUPTED_PIXIE_UTREM_JAR);
        takeBlockItem(blockItems, this::utremJarModel, NewerModBlocks.UTREM_JAR);

        forEach(blockItems, this::withParent);
    }

    public <T extends Item> void utremJarModel(T item) {
        String name = Objects.requireNonNull(item.getRegistryName()).getPath();
        getBuilder("utrem_jar_water").parent(getExistingFile(mcLoc("item/generated"))).texture("layer0", "item/utrem_jar_water");
        getBuilder("utrem_jar_lava").parent(getExistingFile(mcLoc("item/generated"))).texture("layer0", "item/utrem_jar_lava");

        getBuilder(name).parent(getExistingFile(mcLoc("item/generated"))).texture("layer0", "item/" + name)
        .override()
                .predicate(new ResourceLocation("water"), 1.0F)
                .predicate(new ResourceLocation("lava"), 0.0F)
                .model(getExistingFile(modLoc("item/utrem_jar_water"))).end()
        .override()
                .predicate(new ResourceLocation("water"), 0.0F)
                .predicate(new ResourceLocation("lava"), 1.0F)
                .model(getExistingFile(modLoc("utrem_jar_lava"))).end();
    }

}