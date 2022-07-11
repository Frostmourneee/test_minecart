package com.frostmourneee.minecartt.client.event;

import com.frostmourneee.minecartt.client.renderer.WagonEntityRenderer;
import com.frostmourneee.minecartt.client.renderer.model.WagonEntityModel;
import com.frostmourneee.minecartt.core.init.EntityInit;
import com.frostmourneee.minecartt.minecartt;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = minecartt.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientModRenderEvent {

    private ClientModRenderEvent() {}

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(WagonEntityModel.LAYER_LOCATION, WagonEntityModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityInit.WAGON_ENTITY.get(), WagonEntityRenderer::new);
    }
}
