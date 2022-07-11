package com.frostmourneee.minecartt.core.init;

import com.frostmourneee.minecartt.common.item.WagonItem;
import com.frostmourneee.minecartt.minecartt;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public final class ItemInit {
    private ItemInit() {}

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, minecartt.MOD_ID);

    public static final RegistryObject<WagonItem> WAGON_ITEM = ITEMS.register("wagon",
            () -> new WagonItem(new Item.Properties().tab(CreativeModeTab.TAB_TRANSPORTATION).stacksTo(64)));

}
