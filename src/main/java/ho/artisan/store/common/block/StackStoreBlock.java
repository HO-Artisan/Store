package ho.artisan.store.common.block;

import ho.artisan.store.common.tile.StackTile;
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

import java.util.Stack;

public class StackStoreBlock extends BlockWithEntity {

    public StackStoreBlock() {
        super(Settings.copy(Blocks.DIAMOND_BLOCK));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.getBlockEntity(pos) instanceof StackTile stackTile) {
            ItemStack stackInHand = player.getStackInHand(hand);
            Stack<ItemStack> stack = stackTile.getItemStack();
            if (!player.isSneaky()) {
                if (stackInHand.isEmpty()) {
                    return ActionResult.PASS;
                }
                stack.add(stackInHand.split(1));
            }else {
                if (stack.isEmpty()) {
                    return ActionResult.PASS;
                }
                ItemStack top = stack.pop();
                if (!player.getInventory().insertStack(top)) {
                    player.dropItem(top, false);
                }
            }
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof StackTile tile) {
                ItemScatterer.spawn(world, pos, tile);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new StackTile(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
