package ho.artisan.store;

import ho.artisan.store.init.*;
import net.fabricmc.api.ModInitializer;

public class Store implements ModInitializer {
    public static final String MODID = "store";

    @Override
    public void onInitialize() {
        StoreBlocks.register();
        StoreItems.register();
        StoreBlockEntityTypes.register();
        StoreScreenHandlerTypes.register();
        StoreTabs.register();
    }
}
