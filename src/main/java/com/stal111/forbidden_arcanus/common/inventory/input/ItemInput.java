package com.stal111.forbidden_arcanus.common.inventory.input;

import com.stal111.forbidden_arcanus.common.block.entity.forge.HephaestusForgeBlockEntity;
import com.stal111.forbidden_arcanus.common.inventory.InputType;
import com.stal111.forbidden_arcanus.common.loader.HephaestusForgeInputLoader;
import net.minecraft.world.item.ItemStack;

import java.util.Random;

/**
 * Item Input
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.common.container.input.ItemInput
 *
 * @author stal111
 * @version 2.0.0
 * @since 2021-07-07
 */
public class ItemInput implements HephaestusForgeInput {

    @Override
    public boolean canInput(InputType inputType, ItemStack stack) {
        return HephaestusForgeInputLoader.isValidInput(inputType, stack);
    }

    @Override
    public int getInputValue(InputType inputType, ItemStack stack, Random random) {
        return HephaestusForgeInputLoader.getInputData(inputType, stack).value();
    }

    @Override
    public void finishInput(InputType inputType, ItemStack stack, HephaestusForgeBlockEntity tileEntity, int slot, int inputValue) {
        stack.shrink(1);
    }
}
