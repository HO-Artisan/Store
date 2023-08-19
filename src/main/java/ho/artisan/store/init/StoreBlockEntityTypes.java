package ho.artisan.store.init;

import ho.artisan.store.Store;
import ho.artisan.store.common.tile.ListTile;
import ho.artisan.store.common.tile.StackTile;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class StoreBlockEntityTypes {
    public static BlockEntityType<StackTile> stackStore;
    public static BlockEntityType<ListTile> listStore;


    public static void register() {
        stackStore = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Store.MODID, "stack_store"),
                FabricBlockEntityTypeBuilder.create(StackTile::new).addBlock(StoreBlocks.stackStoreBlock).build());

        listStore = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Store.MODID, "list_store"),
                FabricBlockEntityTypeBuilder.create(ListTile::new).addBlock(StoreBlocks.listStoreBlock).build());

        ItemStorage.SIDED.registerForBlockEntity(((blockEntity, direction) -> blockEntity.inventoryStorage), StoreBlockEntityTypes.stackStore);
        ItemStorage.SIDED.registerForBlockEntity(((blockEntity, direction) -> blockEntity.inventoryStorage), StoreBlockEntityTypes.listStore);
    }
}
