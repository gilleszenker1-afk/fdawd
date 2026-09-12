package net.kelptweaks;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;

import java.util.Random;

/**
 * Mob Tweaks.
 *
 * Aktuell enthalten:
 *  - Nacht-Bonus: Zombies und Skelette, die nachts von einem Spieler
 *    besiegt werden, haben eine 15%-Chance auf einen zusätzlichen Smaragd.
 *
 * Weitere Tweaks können hier einfach ergänzt werden.
 */
public class MobTweaks {

    private static final Random RANDOM = new Random();
    private static final double DROP_CHANCE = 0.15;

    public static void init() {
        ServerLivingEntityEvents.AFTER_DEATH.register(MobTweaks::onEntityDeath);
    }

    private static void onEntityDeath(LivingEntity entity, DamageSource damageSource) {
        if (!(entity.getWorld() instanceof ServerWorld world)) {
            return;
        }

        boolean isNight = world.getTimeOfDay() % 24000 >= 13000 && world.getTimeOfDay() % 24000 <= 23000;
        boolean isTargetMob = entity instanceof ZombieEntity || entity instanceof SkeletonEntity;
        boolean killedByPlayer = damageSource.getAttacker() != null
                && damageSource.getAttacker().isPlayer();

        if (isNight && isTargetMob && killedByPlayer && RANDOM.nextDouble() < DROP_CHANCE) {
            ItemEntity drop = new ItemEntity(world, entity.getX(), entity.getY(), entity.getZ(),
                    new ItemStack(Items.EMERALD, 1));
            world.spawnEntity(drop);
        }
    }
}
