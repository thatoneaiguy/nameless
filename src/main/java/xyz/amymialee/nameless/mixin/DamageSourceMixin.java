package xyz.amymialee.nameless.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DamageSource.class)
public class DamageSourceMixin {
    @WrapOperation(method = "getDeathMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getDisplayName()Lnet/minecraft/text/Text;"))
    private Text nameless$removeName(Entity instance, Operation<Text> original) {
        if (instance instanceof LivingEntity living && living.hasStatusEffect(StatusEffects.INVISIBILITY)) {
            return Text.translatable("nameless.somebody");
        }
        return original.call(instance);
    }
}