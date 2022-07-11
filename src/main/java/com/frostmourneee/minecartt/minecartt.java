package com.frostmourneee.minecartt;

import com.frostmourneee.minecartt.core.init.EntityInit;
import com.frostmourneee.minecartt.core.init.ItemInit;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import static com.frostmourneee.minecartt.minecartt.MOD_ID;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MOD_ID)
public class minecartt {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "minecartt";
    private static final Logger LOGGER = LogUtils.getLogger();
    
    public minecartt() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        EntityInit.ENTITIES.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
}
