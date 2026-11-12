package dev.ruzgar.spearswapfix;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public class SpearSwapFixScreen extends Screen {
   private final Screen parent;

   public SpearSwapFixScreen(Screen parent) {
      super(Component.translatable("spearswapfix.config.title"));
      this.parent = parent;
   }

   @Override
   protected void init() {
      int x = this.width / 2 - 100;
      int y = this.height / 2 - 30;
      this.addRenderableWidget(new StringWidget(x, y - 24, 200, 20, this.title, this.font));
      this.addRenderableWidget(
         CycleButton.onOffBuilder(SpearSwapFixConfig.isEnabled())
            .withTooltip(value -> Tooltip.create(Component.translatable("spearswapfix.config.enabled.tooltip")))
            .create(x, y, 200, 20, Component.translatable("spearswapfix.config.enabled"), (button, value) -> SpearSwapFixConfig.setEnabled(value))
      );
      this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, b -> this.onClose()).bounds(x, y + 30, 200, 20).build());
   }

   @Override
   public void onClose() {
      this.minecraft.gui.setScreen(this.parent);
   }
}
