package dev.ruzgar.spearswapfix.mixin;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LivingEntity.SwingState.class)
public interface SwingStateAccessor {
   @Accessor("currentSwing")
   LivingEntity.SwingDescription spearswapfix$getCurrentSwing();

   @Accessor("currentSwing")
   void spearswapfix$setCurrentSwing(LivingEntity.SwingDescription swing);
}
