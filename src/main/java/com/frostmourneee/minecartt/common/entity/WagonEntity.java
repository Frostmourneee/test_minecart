package com.frostmourneee.minecartt.common.entity;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.level.Level;

import java.util.ArrayList;

public class WagonEntity extends Minecart {

    WagonEntity frontCart = null;
    WagonEntity backCart = null;

    public WagonEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void tick() {
        super.tick();

        /*
        Movement implemented only along positive X axis. Connection between minecarts implemented via interact()
        only with the rear cart, not with the front one
         */
        if (frontCart != null) setPos(frontCart.position().add(-1.625D, 0.0D, 0.0D));
    }

    @Override
    public InteractionResult interact(Player pPlayer, InteractionHand pHand) {
        InteractionResult ret = super.interact(pPlayer, pHand);
        if (ret.consumesAction()) return ret;

        /*
        My code starts
         */
        ArrayList<WagonEntity> wagons = (ArrayList<WagonEntity>) level.getEntitiesOfClass(WagonEntity.class, getBoundingBox().inflate(10.0D));
        wagons.removeIf(wagonEntity -> wagonEntity.equals(this));

        if (!wagons.isEmpty()) {
            frontCart = wagons.get(0);
            frontCart.backCart = this;
        }
        /*
        My code ends
         */

        if (pPlayer.isSecondaryUseActive()) {
            return InteractionResult.PASS;
        } else if (this.isVehicle()) {
            return InteractionResult.PASS;
        } else if (!this.level.isClientSide) {
            return pPlayer.startRiding(this) ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else {
            return InteractionResult.SUCCESS;
        }
    }
}