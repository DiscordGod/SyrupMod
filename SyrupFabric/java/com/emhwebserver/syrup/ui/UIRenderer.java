package com.emhwebserver.syrup.ui;

import com.emhwebserver.syrup.event.Event;
import com.emhwebserver.syrup.utils.CPSHelper;
import com.emhwebserver.syrup.utils.YHelper;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import java.util.Collection;
import java.util.Map;

public class UIRenderer {
  private MinecraftClient mc = MinecraftClient.getInstance();

  public void draw() {
    drawString("[FPS] " + mc.fpsDebugString.split("fps ")[0] + "fps ", 2, YHelper.y("fps"), 0xffffff);
    drawString(Event.moduleManager.coords.isEnabled() ? "[XYZ] " + Math.round(mc.player.x) + "/" + Math.round(mc.player.y) + "/" + Math.round(mc.player.z) : "", 2, YHelper.y("xyz"), 0xffffff);
    GlStateManager.scaled(2.0, 2.0, 2.0);
    drawString(Event.moduleManager.keys.isEnabled() ? "W" : "", 11, YHelper.y("keysW"), mc.options.keyForward.isPressed() ? 0x00ff00 : 0xff0000);
    drawString(Event.moduleManager.keys.isEnabled() ? "S" : "", 11, YHelper.y("keysA"), mc.options.keyBack.isPressed() ? 0x00ff00 : 0xff0000);
    drawString(Event.moduleManager.keys.isEnabled() ? "A" : "", 1, YHelper.y("keysS"), mc.options.keyLeft.isPressed() ? 0x00ff00 : 0xff0000);
    drawString(Event.moduleManager.keys.isEnabled() ? "D": "", 21, YHelper.y("keysD"), mc.options.keyRight.isPressed() ? 0x00ff00 : 0xff0000);
    GlStateManager.scaled(0.5, 0.5, 0.5);
    drawString("Sprint", 2, YHelper.y("sprintorcps"), mc.player.isSprinting() ? 0x00ff00 : 0xff0000);
    drawString("CPS: " + CPSHelper.getClicks(), 6 + mc.textRenderer.getStringWidth("Sprint"), YHelper.y("sprintorcps"), CPSHelper.getClicks() > 0  ? 0x00ff00 : 0xff0000);
    drawString("Swinging", 2, YHelper.y("swinging"), mc.player.isHandSwinging ? 0x00ff00 : 0xff0000);
    drawString(Event.moduleManager.armorStatus.isEnabled() ? "Helmet Durability: " + (mc.player.inventory.getArmorStack(3).getDurability() - mc.player.inventory.getArmorStack(3).getDamage()) + "/" + mc.player.inventory.getArmorStack(3).getDurability() : "", 2, YHelper.y("armorHelm"), mc.player.inventory.getArmorStack(3) != null ? getColorByCurrentAndMax(mc.player.inventory.getArmorStack(3).getDurability() - mc.player.inventory.getArmorStack(3).getDamage(), mc.player.inventory.getArmorStack(3).getDurability()) : 0xffffff);
    drawString(Event.moduleManager.armorStatus.isEnabled() ? "Chestplate Durability: " + (mc.player.inventory.getArmorStack(2).getDurability() - mc.player.inventory.getArmorStack(2).getDamage()) + "/" + mc.player.inventory.getArmorStack(2).getDurability() : "", 2, YHelper.y("armorChest"), mc.player.inventory.getArmorStack(2) != null ? getColorByCurrentAndMax(mc.player.inventory.getArmorStack(2).getDurability() - mc.player.inventory.getArmorStack(2).getDamage(), mc.player.inventory.getArmorStack(2).getDurability()) : 0xffffff);
    drawString(Event.moduleManager.armorStatus.isEnabled() ? "Leggings Durability: " + (mc.player.inventory.getArmorStack(1).getDurability() - mc.player.inventory.getArmorStack(1).getDamage()) + "/" + mc.player.inventory.getArmorStack(1).getDurability() : "", 2, YHelper.y("armorLeggings"), mc.player.inventory.getArmorStack(1) != null ? getColorByCurrentAndMax(mc.player.inventory.getArmorStack(1).getDurability() - mc.player.inventory.getArmorStack(1).getDamage(), mc.player.inventory.getArmorStack(1).getDurability()) : 0xffffff);
    drawString(Event.moduleManager.armorStatus.isEnabled() ? "Boots Durability: " + (mc.player.inventory.getArmorStack(0).getDurability() - mc.player.inventory.getArmorStack(0).getDamage()) + "/" + mc.player.inventory.getArmorStack(0).getDurability() : "", 2, YHelper.y("armorBoots"), mc.player.inventory.getArmorStack(0) != null ? getColorByCurrentAndMax(mc.player.inventory.getArmorStack(0).getDurability() - mc.player.inventory.getArmorStack(0).getDamage(), mc.player.inventory.getArmorStack(0).getDurability()) : 0xffffff);
  }
  
  private int getColorByCurrentAndMax(int current, int max) {
    double currentD = (double)current;
    double maxD = (double)max;
    if (currentD / maxD >= 0.9)
      return 32768;
    if (currentD / maxD <= 0.9 && currentD / maxD >= 0.8)
      return 1017600;
    if (currentD / maxD <= 0.8 && currentD / maxD >= 0.7)
      return 6336256;
    if (currentD / maxD <= 0.7 && currentD / maxD >= 0.6)
      return 11195392;
    if (currentD / maxD <= 0.6 && currentD / maxD >= 0.5)
      return 14150400;
    if (currentD / maxD <= 0.5 && currentD / maxD >= 0.4)
      return 16580096;
    if (currentD / maxD <= 0.4 && currentD / maxD >= 0.3)
      return 16764160;
    if (currentD / maxD <= 0.3 && currentD / maxD >= 0.2)
      return 16756224;
    if (currentD / maxD <= 0.2 && currentD / maxD >= 0.1)
      return 16734720;
    if (currentD / maxD <= 0.1) {
      return 16711680;
    }
    return 0xffffff;
  }
  
  private void drawString(String text, float x, float y, int color) {
    mc.textRenderer.drawWithShadow(text, x, y, color);
  }

}
