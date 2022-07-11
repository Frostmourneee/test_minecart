package com.frostmourneee.minecartt.common.item;

import com.frostmourneee.minecartt.common.entity.WagonEntity;
import com.frostmourneee.minecartt.core.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseRailBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

public class WagonItem extends Item {

    public WagonItem(Properties itemProperties) {
        super(itemProperties);
    }

    private static final DispenseItemBehavior DISPENSE_ITEM_BEHAVIOR = new DefaultDispenseItemBehavior() {
        private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

        public ItemStack execute(BlockSource blockSource, ItemStack itemStack) {
            Direction direction = blockSource.getBlockState().getValue(DispenserBlock.FACING);
            Level level = blockSource.getLevel();
            BlockPos blockpos = blockSource.getPos().relative(direction);
            BlockState blockstate = level.getBlockState(blockpos);

            if (!blockstate.is(BlockTags.RAILS)) {
                if (!blockstate.isAir() || !level.getBlockState(blockpos.below()).is(BlockTags.RAILS)) {
                    return defaultDispenseItemBehavior.dispense(blockSource, itemStack);
                }
            }

            itemStack.shrink(1);
            return itemStack;
        }

        protected void playSound(BlockSource blockSource) {
            blockSource.getLevel().levelEvent(1000, blockSource.getPos(), 0);
        }
    };

    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        ItemStack itemstack = context.getItemInHand();

        if (!blockstate.is(BlockTags.RAILS)) {
            return InteractionResult.FAIL;
        }

        BaseRailBlock rail = (BaseRailBlock) blockstate.getBlock();


        if (!level.isClientSide) { //LEVELSIDE IS AS IT IS IN VANILLA CODE
            WagonEntity wagonEntity = new WagonEntity(EntityInit.WAGON_ENTITY.get(), level);;

            RailShape railshape = rail.getRailDirection(blockstate, level, blockpos, null);
            double d0 = 0.0D;
            if (railshape.isAscending()) {
                d0 = 0.5D;
            }

            wagonEntity.setPos(blockpos.getX() + 0.5D, blockpos.getY() + 0.0625D + d0, blockpos.getZ() + 0.5D);

            switch (railshape) {
                case NORTH_SOUTH -> wagonEntity.setYRot(context.getPlayer().getZ() > wagonEntity.getZ() ? 180.0F : 0.0F);
                case EAST_WEST -> wagonEntity.setYRot(context.getPlayer().getX() > wagonEntity.getX() ? 90.0F : 270.0F);
                case ASCENDING_EAST -> wagonEntity.setYRot(90.0F);
                case ASCENDING_NORTH -> wagonEntity.setYRot(0.0F);
                case ASCENDING_WEST -> wagonEntity.setYRot(270.0F);
                case ASCENDING_SOUTH -> wagonEntity.setYRot(180.0F);
            }

            level.addFreshEntity(wagonEntity);
            level.gameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, blockpos);
        }
        itemstack.shrink(1);

        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}