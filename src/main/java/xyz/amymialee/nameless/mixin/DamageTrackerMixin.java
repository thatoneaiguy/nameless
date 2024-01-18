package xyz.amymialee.nameless.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTracker;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(DamageTracker.class)
public class DamageTrackerMixin {
    @WrapOperation(method = "getDeathMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getDisplayName()Lnet/minecraft/text/Text;"))
    private Text nameless$removeName(@NotNull LivingEntity instance, Operation<Text> original) {
        if (instance.hasStatusEffect(StatusEffects.INVISIBILITY)) {
            return Text.literal("Somebody");
        }
        return original.call(instance);
    }

    @WrapOperation(method = "getFallDeathMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getDisplayName()Lnet/minecraft/text/Text;"))
    private Text nameless$removeFallName(@NotNull LivingEntity instance, Operation<Text> original) {
        if (instance.hasStatusEffect(StatusEffects.INVISIBILITY)) {
            return Text.literal("Somebody");
        }
        return original.call(instance);
    }

    @WrapOperation(method = "getDisplayName", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getDisplayName()Lnet/minecraft/text/Text;"))
    private static Text nameless$removeName(Entity instance, Operation<Text> original) {
        if (instance instanceof LivingEntity living && living.hasStatusEffect(StatusEffects.INVISIBILITY)) {
            return Text.literal("Somebody");
        }
        return original.call(instance);
    }

    @WrapOperation(method = "getAttackedFallDeathMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getDisplayName()Lnet/minecraft/text/Text;"))
    private Text nameless$removeAttackedFallName(@NotNull LivingEntity instance, Operation<Text> original) {
        if (instance.hasStatusEffect(StatusEffects.INVISIBILITY)) {
            return Text.literal("Somebody");
        }
        return original.call(instance);
    }
}