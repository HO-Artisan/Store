package ho.artisan.store.common.tile;

import ho.artisan.store.Store;
import ho.artisan.store.common.tile.inf.StackStoreTile;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class StackTile extends StackStoreTile {
    public final InventoryStorage inventoryStorage = InventoryStorage.of(this, null);

    public StackTile(BlockPos pos, BlockState state) {
        super(Store.STACK_STORE, pos, state);
    }

    @Override
    public void read(NbtCompound nbt, boolean isClient) {
        readItems(nbt, this.stack);
    }

    @Override
    public void write(NbtCompound nbt, boolean isClient) {
        writeItems(nbt, this.stack);
    }
}
