package ho.artisan.store.init;

import ho.artisan.store.common.screenhandler.ListStoreScreenHandler;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

import static ho.artisan.store.Store.MODID;

public class StoreScreenHandlerTypes {
    public static ScreenHandlerType<ListStoreScreenHandler> screenHandlerType;

    public static void register() {
        screenHandlerType = Registry.register(Registries.SCREEN_HANDLER, new Identifier(MODID, "list_store"),
                new ScreenHandlerType<>(ListStoreScreenHandler::new, FeatureFlags.VANILLA_FEATURES));
    }
}
