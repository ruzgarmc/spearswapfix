# Spear Swap Fix

A small client-side Fabric mod for **Minecraft 26.3** that fixes the spear lunge animation bug.

## The bug
In 26.3, if you attack with a spear and immediately swap to another item (for example a golden apple), the new item keeps playing the spear's long lunge/stab animation. It looks huge, tilted and keeps swinging for a few seconds.

In 1.21.11 this didn't happen: the swing type and duration were recalculated every tick from the item you were holding, so after a swap the new item finished quickly with its own normal swing.

## The fix
Spear Swap Fix brings back the 1.21.11 behaviour. Each tick, if the item in your hand changed, the current swing switches to that item's own attack animation and duration.

- Client-side only, visual only — nothing is sent to the server.
- Can be toggled on/off in **Mod Menu → Spear Swap Fix → config** (saved in `config/spearswapfix.properties`).
- English and Turkish translations.

## Requirements
- Minecraft 26.3
- Fabric Loader 0.19.5+
- Mod Menu (optional, for the toggle screen)

## Building
```
./gradlew build
```
Requires Java 25. The jar ends up in `build/libs/`.

---

## Türkçe
26.3'te spear ile vurup hemen başka bir iteme (ör. altın elma) geçince yeni item spear'ın uzun lunge/dürtme animasyonunu almaya devam ediyor. Bu mod 1.21.11'deki gibi sallamanın o an eldeki itemi takip etmesini sağlar. Sadece client tarafında ve görseldir; Mod Menu'den açılıp kapatılabilir.
