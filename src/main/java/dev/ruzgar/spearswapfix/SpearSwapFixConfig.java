package dev.ruzgar.spearswapfix;

import java.nio.file.Files;
import java.nio.file.Path;
import net.fabricmc.loader.api.FabricLoader;

public final class SpearSwapFixConfig {
   private static final Path FILE = FabricLoader.getInstance().getConfigDir().resolve("spearswapfix.properties");
   private static Boolean enabled;

   private SpearSwapFixConfig() {}

   public static boolean isEnabled() {
      if (enabled == null) {
         enabled = true;
         try {
            if (Files.exists(FILE)) {
               for (String line : Files.readAllLines(FILE)) {
                  if (line.trim().startsWith("enabled=")) {
                     enabled = !line.trim().substring(8).trim().equalsIgnoreCase("false");
                  }
               }
            }
         } catch (Exception ignored) {
         }
      }
      return enabled;
   }

   public static void setEnabled(boolean value) {
      enabled = value;
      try {
         Files.writeString(FILE, "# Spear Swap Fix\nenabled=" + value + "\n");
      } catch (Exception ignored) {
      }
   }
}
