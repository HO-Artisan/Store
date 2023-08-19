package ho.artisan.store.client.tesr;

import ho.artisan.store.common.tile.StackTile;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.joml.Quaternionf;

import java.util.Stack;

public class StackTileRenderer implements BlockEntityRenderer<StackTile> {

    public StackTileRenderer(BlockEntityRendererFactory.Context ctx) {}

    @Override
    public void render(StackTile tile, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light0, int overlay) {
        Stack<ItemStack> stack = tile.getItemStack();
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        float y = 1;

        if (!stack.isEmpty()) {
            int light = 0xF000F0;
            for (int i = 0, stackSize = stack.size(); i < stackSize; i++) {
                boolean blockItem = itemRenderer.getModel(stack.elementAt(i), tile.getWorld(), null, (int) tile.getPos().asLong()).hasDepth();
                matrices.push();
                if (blockItem)
                    y += 7/24f;
                else
                    y += 1/16f;
                matrices.translate(0.5f, y, 0.5f);
                matrices.multiply(new Quaternionf().rotateX((float) Math.PI * 0.5f));
                itemRenderer.renderItem(stack.elementAt(i), ModelTransformationMode.FIXED, light, overlay, matrices, vertexConsumers, null, 0);
                matrices.pop();
                if (blockItem)
                    y += 5/24f;
            }
        }
    }
}
