package ho.artisan.store.client;

import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import ho.artisan.store.Store;
import ho.artisan.store.client.tesr.ListTileRenderer;
import ho.artisan.store.client.tesr.StackTileRenderer;
import net.fabricmc.api.ClientModInitializer;

public class StoreClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.register(Store.STACK_STORE, StackTileRenderer::new);
        BlockEntityRendererRegistry.register(Store.LIST_STORE, ListTileRenderer::new);
    }
}
