package ho.artisan.store.common.tile;

import ho.artisan.store.Store;
import ho.artisan.store.common.tile.inf.ListStoreTile;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class ListTile extends ListStoreTile {
    public final InventoryStorage inventoryStorage = InventoryStorage.of(this, null);

    public ListTile(BlockPos pos, BlockState state) {
        super(Store.LIST_STORE, pos, state, 4);
    }

    @Override
    public void read(NbtCompound nbt, boolean isClient) {
        readItems(nbt, this.list);
    }

    @Override
    public void write(NbtCompound nbt, boolean isClient) {
        writeItems(nbt, this.list);
    }
}
