package ho.artisan.store.client.tesr;

import ho.artisan.store.common.tile.ListTile;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Quaternionf;

public class ListTileRenderer implements BlockEntityRenderer<ListTile> {

    public ListTileRenderer(BlockEntityRendererFactory.Context ctx) {}


    @Override
    public void render(ListTile tile, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light0, int overlay) {
        var list = tile.getItemList();
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();

        if (!tile.isEmpty()) {
            int light = 0xF000F0;

            for (int i = 0; i < list.size(); i++) {
                boolean blockItem = itemRenderer.getModel(tile.getStack(i), tile.getWorld(), null, (int) tile.getPos().asLong()).hasDepth();
                matrices.push();
                float y;
                if (blockItem)
                    y = 1f + 7/24f;
                else
                    y = 1f + 1/16f;

                if (i < 2) {
                    matrices.translate(0.25f + i * 0.5, y, 0.25f);
                }else {
                    matrices.translate(0.25f + (i % 2) * 0.5, y, 0.75f);
                }
                matrices.scale(0.8f, 0.8f, 0.8f);
                matrices.multiply(new Quaternionf().rotateX((float) Math.PI * 0.5f));
                itemRenderer.renderItem(tile.getStack(i), ModelTransformationMode.FIXED, light, overlay, matrices, vertexConsumers, null, 0);
                matrices.pop();
            }
        }


    }
}
