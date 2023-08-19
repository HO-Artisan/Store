package ho.artisan.store.common.block;

import ho.artisan.store.common.tile.ListTile;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ListStoreBlock extends BlockWithEntity {
    public ListStoreBlock() {
        super(Settings.copy(Blocks.DIAMOND_BLOCK));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.getBlockEntity(pos) instanceof ListTile listTile) {
            ItemStack stackInHand = player.getStackInHand(hand);
            if (!player.isSneaky()) {
                for (int i = 0; i < listTile.getItemList().size(); i++) {
                    if (listTile.getStack(i).isEmpty()) {
                        if (stackInHand.isEmpty()) {
                            return ActionResult.PASS;
                        }
                        listTile.setStack(i, stackInHand.split(1));
                        return ActionResult.SUCCESS;
                    }
                }
            }else {
                if (listTile.isEmpty()) {
                    return ActionResult.PASS;
                }
                for (int i = 0; i < listTile.getItemList().size(); i++) {
                    if (!listTile.getStack(i).isEmpty()) {
                        ItemStack stack = listTile.getStack(i).split(1);
                        if (!player.getInventory().insertStack(stack.copy())) {
                            player.dropItem(stack.copy(), false);
                        }
                        return ActionResult.SUCCESS;
                    }
                }
            }
            return ActionResult.PASS;
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof ListTile tile) {
                ItemScatterer.spawn(world, pos, tile);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ListTile(pos, state);
    }


    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
