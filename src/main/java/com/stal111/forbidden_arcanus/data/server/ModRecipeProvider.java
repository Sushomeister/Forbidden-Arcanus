package com.stal111.forbidden_arcanus.data.server;

import com.stal111.forbidden_arcanus.ForbiddenArcanus;
import com.stal111.forbidden_arcanus.init.ModItems;
import com.stal111.forbidden_arcanus.init.NewModItems;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

/**
 * Mod Recipe Provider
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.data.server.ModRecipeProvider
 *
 * @author stal111
 * @version 16.2.0
 * @since 2021-01-28
 */
public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(NewModItems.OBSIDIAN_SKULL.get())
                .patternLine("#O#")
                .patternLine("OXO")
                .patternLine("#O#")
                .key('#', ModItems.OBSIDIAN_INGOT.get())
                .key('X', Items.SKELETON_SKULL)
                .key('O', Tags.Items.OBSIDIAN)
                .addCriterion("has_item", hasItem(Tags.Items.OBSIDIAN))
                .build(consumer);

        SmithingRecipeBuilder.smithingRecipe(
                Ingredient.fromItems(Items.SHIELD),
                Ingredient.fromItems(NewModItems.OBSIDIAN_SKULL.get()),
                NewModItems.OBSIDIAN_SKULL_SHIELD.get())
                .addCriterion("has_item", hasItem(NewModItems.OBSIDIAN_SKULL.get()))
                .build(consumer, new ResourceLocation(ForbiddenArcanus.MOD_ID, "smithing/obsidian_skull_shield"));

        SmithingRecipeBuilder.smithingRecipe(
                Ingredient.fromItems(NewModItems.OBSIDIAN_SKULL.get()),
                Ingredient.fromItems(ModItems.ETERNAL_STELLA.get()),
                NewModItems.ETERNAL_OBSIDIAN_SKULL.get())
                .addCriterion("has_item", hasItem(NewModItems.OBSIDIAN_SKULL.get()))
                .build(consumer, new ResourceLocation(ForbiddenArcanus.MOD_ID, "smithing/eternal_obsidian_skull"));
    }
}