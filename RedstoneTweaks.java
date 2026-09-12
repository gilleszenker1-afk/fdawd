package net.kelptweaks;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.RepeaterBlock;
import net.minecraft.block.enums.ComparatorMode;
import net.minecraft.item.Items;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

/**
 * Redstone Tweaks.
 *
 * Aktuell enthalten:
 *  - "Schnell-Repeater": Duckt sich ein Spieler und rechtsklickt einen
 *    Redstone-Repeater mit einem Redstone-Stab (Blaze Rod) in der Hand,
 *    springt die Verzögerung um 2 Stufen statt um 1 weiter.
 *
 * Weitere Tweaks können hier einfach ergänzt werden.
 */
public class RedstoneTweaks {

    public static void init() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (world.isClient) {
                return ActionResult.PASS;
            }

            BlockPos pos = hitResult.getBlockPos();
            BlockState state = world.getBlockState(pos);

            boolean holdingBlazeRod = player.getStackInHand(hand).isOf(Items.BLAZE_ROD);

            if (state.getBlock() instanceof RepeaterBlock && player.isSneaking() && holdingBlazeRod) {
                IntProperty delayProperty = net.minecraft.block.RepeaterBlock.DELAY;
                int currentDelay = state.get(delayProperty);
                int nextDelay = currentDelay + 1; // ein Schritt = 2 Redstone-Ticks
                if (nextDelay > delayProperty.getValues().stream().mapToInt(i -> i).max().orElse(4)) {
                    nextDelay = delayProperty.getValues().stream().mapToInt(i -> i).min().orElse(1);
                }

                world.setBlockState(pos, state.with(delayProperty, nextDelay));
                world.playSound(null, pos, net.minecraft.sound.SoundEvents.BLOCK_LEVER_CLICK,
                        net.minecraft.sound.SoundCategory.BLOCKS, 0.3F, 0.6F);
                return ActionResult.SUCCESS;
            }

            return ActionResult.PASS;
        });
    }
}
