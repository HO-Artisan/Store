package ho.artisan.store.common.tile;

import ho.artisan.store.common.screenhandler.ListStoreScreenHandler;
import ho.artisan.store.common.tile.inf.ListStoreTile;
import ho.artisan.store.init.StoreBlockEntityTypes;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class ListTile extends ListStoreTile implements NamedScreenHandlerFactory {
    public final InventoryStorage inventoryStorage = InventoryStorage.of(this, null);

    public ListTile(BlockPos pos, BlockState state) {
        super(StoreBlockEntityTypes.listStore, pos, state, 4);
    }

    @Override
    public void read(NbtCompound nbt, boolean isClient) {
        readItems(nbt, this.list);
    }

    @Override
    public void write(NbtCompound nbt, boolean isClient) {
        writeItems(nbt, this.list);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ListStoreScreenHandler(syncId, playerInventory, this);
    }


}
