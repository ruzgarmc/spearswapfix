package dev.ruzgar.spearswapfix.mixin;

import dev.ruzgar.spearswapfix.SpearSwapFixConfig;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.SwingAnimation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// 1.21.11'de kol sallamanin tipi ve suresi her tick o an eldeki itemden hesaplaniyordu:
// spear ile vurup elmaya gecince sallama elmanin kisa suresine gecip hemen bitiyordu.
// 26.3'te vurulan itemin (spear) animasyonu + uzun suresi kilitli kaliyor; yeni item
// uzun, garip bir spear/sallama hareketi yapiyor. Burada 1.21.11 davranisini geri getiriyoruz
// (sadece client tarafinda, sadece gorsel).
@Mixin(LivingEntity.class)
public abstract class LivingEntitySwingMixin {
   @Shadow
   @Final
   private LivingEntity.SwingState swingState;

   @Shadow
   private int getModifiedSwingDuration(SwingAnimation animation) {
      throw new AssertionError();
   }

   @Inject(method = "baseTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity$SwingState;tick()V"))
   private void spearswapfix$followHeldItem(CallbackInfo ci) {
      LivingEntity self = (LivingEntity)(Object)this;
      if (!self.level().isClientSide() || !SpearSwapFixConfig.isEnabled()) {
         return;
      }
      SwingStateAccessor state = (SwingStateAccessor)this.swingState;
      LivingEntity.SwingDescription swing = state.spearswapfix$getCurrentSwing();
      if (swing == null) {
         return;
      }
      ItemStack held = self.getItemInHand(InteractionHand.MAIN_HAND);
      SwingAnimation attack = held.getAttackAnimation();
      if (swing.animation().equals(attack) || swing.animation().equals(held.getInteractAnimation())) {
         return;
      }
      state.spearswapfix$setCurrentSwing(new LivingEntity.SwingDescription(swing.hand(), attack, this.getModifiedSwingDuration(attack)));
   }
}
