package ho.artisan.store.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.HashSet;
import java.util.Set;

import static ho.artisan.store.Store.MODID;

public class StoreItems {
    public static final Set<ItemStack> ITEMS = new HashSet<>();

    public static void register() {
        registerItem("stack_store_block", new BlockItem(StoreBlocks.stackStoreBlock, new Item.Settings()));
        registerItem("list_store_block", new BlockItem(StoreBlocks.listStoreBlock, new Item.Settings()));
    }

    private static void registerItem(String id, Item item) {
        Registry.register(Registries.ITEM, new Identifier(MODID, id), item);
        ITEMS.add(new ItemStack(item));
    }
}
