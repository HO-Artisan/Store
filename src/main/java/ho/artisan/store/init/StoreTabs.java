package ho.artisan.store.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static ho.artisan.store.Store.MODID;

public class StoreTabs {
    public static void register() {
        Registry.register(Registries.ITEM_GROUP, new Identifier(MODID, "main"), FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.store"))
                .entries(((displayContext, entries) -> entries.addAll(StoreItems.ITEMS)))
                .icon(() -> new ItemStack(StoreBlocks.stackStoreBlock))
                .build());
    }
}
