package ho.artisan.store.init;

import ho.artisan.store.Store;
import ho.artisan.store.common.block.ListStoreBlock;
import ho.artisan.store.common.block.StackStoreBlock;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class StoreBlocks {
    public static Block stackStoreBlock;
    public static Block listStoreBlock;

    public static void register() {
        stackStoreBlock = Registry.register(Registries.BLOCK, new Identifier(Store.MODID, "stack_store_block"),
                new StackStoreBlock());

        listStoreBlock = Registry.register(Registries.BLOCK, new Identifier(Store.MODID, "list_store_block"),
                new ListStoreBlock());
    }
}
