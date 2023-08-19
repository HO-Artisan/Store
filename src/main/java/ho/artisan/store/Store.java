package ho.artisan.store;

import ho.artisan.store.common.block.ListStoreBlock;
import ho.artisan.store.common.block.StackStoreBlock;
import ho.artisan.store.common.tile.ListTile;
import ho.artisan.store.common.tile.StackTile;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class Store implements ModInitializer {
    public static final String MODID = "store";

    public static final Block STACK_STORE_BLOCK = Registry.register(Registries.BLOCK, new Identifier(MODID, "stack_store_block"),
            new StackStoreBlock());
    public static final Block LIST_STORE_BLOCK = Registry.register(Registries.BLOCK, new Identifier(MODID, "list_store_block"),
            new ListStoreBlock());

    public static final BlockEntityType<StackTile> STACK_STORE =  Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MODID, "stack_store"),
            FabricBlockEntityTypeBuilder.create(StackTile::new).addBlock(STACK_STORE_BLOCK).build());

    public static final BlockEntityType<ListTile> LIST_STORE =  Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MODID, "list_store"),
            FabricBlockEntityTypeBuilder.create(ListTile::new).addBlock(LIST_STORE_BLOCK).build());
    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM, new Identifier(MODID, "stack_store_block"), new BlockItem(STACK_STORE_BLOCK, new Item.Settings()));
        Registry.register(Registries.ITEM, new Identifier(MODID, "list_store_block"), new BlockItem(LIST_STORE_BLOCK, new Item.Settings()));

        Registry.register(Registries.ITEM_GROUP, new Identifier(MODID, "main"), FabricItemGroup.builder()
                        .displayName(Text.translatable("itemGroup.store"))
                        .entries(((displayContext, entries) -> entries.add(STACK_STORE_BLOCK)))
                        .icon(() -> new ItemStack(STACK_STORE_BLOCK))
                        .build());

        ItemStorage.SIDED.registerForBlockEntity(((blockEntity, direction) -> blockEntity.inventoryStorage), STACK_STORE);
        ItemStorage.SIDED.registerForBlockEntity(((blockEntity, direction) -> blockEntity.inventoryStorage), LIST_STORE);
    }
}
