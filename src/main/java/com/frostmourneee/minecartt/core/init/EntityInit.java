package com.frostmourneee.minecartt.core.init;

import com.frostmourneee.minecartt.common.entity.WagonEntity;
import com.frostmourneee.minecartt.minecartt;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {

    private EntityInit() {}

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, minecartt.MOD_ID);

    public static final RegistryObject<EntityType<WagonEntity>> WAGON_ENTITY = ENTITIES.register("wagon",
            () -> EntityType.Builder.of(WagonEntity::new, MobCategory.MISC).sized(0.98F, 0.7F)
                    .build(new ResourceLocation(minecartt.MOD_ID,"wagon").toString()));
}
