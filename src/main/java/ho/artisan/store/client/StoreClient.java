package ho.artisan.store.client;

import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import ho.artisan.store.client.screen.ListStoreScreen;
import ho.artisan.store.client.tesr.ListTileRenderer;
import ho.artisan.store.client.tesr.StackTileRenderer;
import ho.artisan.store.init.StoreBlockEntityTypes;
import ho.artisan.store.init.StoreScreenHandlerTypes;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class StoreClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.register(StoreBlockEntityTypes.stackStore, StackTileRenderer::new);
        BlockEntityRendererRegistry.register(StoreBlockEntityTypes.listStore, ListTileRenderer::new);

        HandledScreens.register(StoreScreenHandlerTypes.screenHandlerType, ListStoreScreen::new);
    }
}
