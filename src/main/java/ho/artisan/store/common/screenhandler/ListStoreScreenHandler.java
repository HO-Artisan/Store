package ho.artisan.store.common.screenhandler;

import ho.artisan.store.init.StoreScreenHandlerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class ListStoreScreenHandler extends ScreenHandler {
    private final Inventory inventory; //等于ListStoreBlockEntity

    public ListStoreScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(4));
    }

    public ListStoreScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(StoreScreenHandlerTypes.screenHandlerType, syncId);

        this.inventory = inventory;
        ListStoreScreenHandler.checkSize(inventory, 4);
        inventory.onOpen(playerInventory.player);

        this.addSlot(new Slot(inventory, 0, 70, 22));
        this.addSlot(new Slot(inventory, 1, 70 + 16 + 2, 22));
        this.addSlot(new Slot(inventory, 2, 70, 40));
        this.addSlot(new Slot(inventory, 3, 70 + 16 + 2, 40));

        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }
}
