package ho.artisan.store.common.tile.inf;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public abstract class ListStoreTile extends StoreTile implements SidedInventory {
    protected final DefaultedList<ItemStack> list;

    public ListStoreTile(BlockEntityType<?> type, BlockPos pos, BlockState state, int size) {
        super(type, pos, state);
        list = DefaultedList.ofSize(size, ItemStack.EMPTY);
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        return new int[list.size()];
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return true;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return true;
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public boolean isEmpty() {
        boolean flag = true;
        for (ItemStack stack : list) {
            flag = flag && stack.isEmpty();
        }
        return flag;
    }

    public DefaultedList<ItemStack> getItemList() {
        return this.list;
    }

    @Override
    public ItemStack getStack(int slot) {
        return this.list.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack result = Inventories.splitStack(this.list, slot, amount);
        if (!result.isEmpty()) {
            inventoryChanged();
        }
        return result;
    }

    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(this.list, slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        ItemStack itemStack = stack.copy();
        this.list.set(slot, itemStack);
        inventoryChanged();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        this.list.clear();
    }

    public void inventoryChanged() {
        markDirty();
        if (world != null) {
            world.updateListeners(getPos(), getCachedState(), getCachedState(), (1) | (1 << 1));
        }
    }
}
