package com.emhwebserver.syrup.ui;

import com.emhwebserver.syrup.event.Event;
import com.emhwebserver.syrup.utils.CPSHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class UIRenderer {
  private Minecraft mc = Minecraft.getMinecraft();
  public static boolean shouldRender = false;
  
  public void draw() {
    drawString("[FPS] " + Minecraft.getDebugFPS(), 2, 1, 0xffffff);
    drawString("[XYZ] " + Math.round(mc.thePlayer.posX) + "/" + Math.round(mc.thePlayer.posY) + "/" + Math.round(mc.thePlayer.posZ), 2, 15, 0xffffff);
    GlStateManager.scale(2.0, 2.0, 2.0);
    drawString("W", 11, 16, mc.gameSettings.keyBindForward.isKeyDown() ? 0x00ff00 : 0xff0000);
    drawString("S", 11, 27, mc.gameSettings.keyBindBack.isKeyDown() ? 0x00ff00 : 0xff0000);
    drawString("A", 1, 27, mc.gameSettings.keyBindLeft.isKeyDown() ? 0x00ff00 : 0xff0000);
    drawString("D", 21, 27, mc.gameSettings.keyBindRight.isKeyDown() ? 0x00ff00 : 0xff0000);
    GlStateManager.scale(0.5, 0.5, 0.5);
    drawString("Sprint", 2, 75, mc.thePlayer.isSprinting() ? 0x00ff00 : 0xff0000);
    drawString("CPS: " + CPSHelper.getClicks(), 6 + mc.fontRendererObj.getStringWidth("Sprint"), 75, CPSHelper.getClicks() > 0  ? 0x00ff00 : 0xff0000);
    drawString("Swinging", 2, 90, mc.thePlayer.isSwingInProgress ? 0x00ff00 : 0xff0000);
    drawString(Event.moduleManager.armorStatus.isEnabled() ? "Helmet Durability: " + getDurability(mc.thePlayer.inventory.armorInventory[3]) + "/" + mc.thePlayer.inventory.armorInventory[3].getMaxDamage() : "", 2, 105, mc.thePlayer.inventory.armorInventory[3] != null ? getColorByCurrentAndMax(getDurability(mc.thePlayer.inventory.armorInventory[3]), mc.thePlayer.inventory.armorInventory[3].getMaxDamage()) : 0xffffff);
    drawString(Event.moduleManager.armorStatus.isEnabled() ? "Chestplate Durability: " + getDurability(mc.thePlayer.inventory.armorInventory[2]) + "/" + mc.thePlayer.inventory.armorInventory[2].getMaxDamage() : "", 2, 120, mc.thePlayer.inventory.armorInventory[2] != null ? getColorByCurrentAndMax(getDurability(mc.thePlayer.inventory.armorInventory[2]), mc.thePlayer.inventory.armorInventory[2].getMaxDamage()) : 0xffffff);
    drawString(Event.moduleManager.armorStatus.isEnabled() ? "Leggings Durability: " + getDurability(mc.thePlayer.inventory.armorInventory[1]) + "/" + mc.thePlayer.inventory.armorInventory[1].getMaxDamage() : "", 2, 135, mc.thePlayer.inventory.armorInventory[1] != null ? getColorByCurrentAndMax(getDurability(mc.thePlayer.inventory.armorInventory[1]), mc.thePlayer.inventory.armorInventory[1].getMaxDamage()) : 0xffffff);
    drawString(Event.moduleManager.armorStatus.isEnabled() ? "Boots Durability: " + getDurability(mc.thePlayer.inventory.armorInventory[0]) + "/" + mc.thePlayer.inventory.armorInventory[0].getMaxDamage() : "", 2, 150, mc.thePlayer.inventory.armorInventory[0] != null ? getColorByCurrentAndMax(getDurability(mc.thePlayer.inventory.armorInventory[0]), mc.thePlayer.inventory.armorInventory[0].getMaxDamage()) : 0xffffff);
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
    mc.fontRendererObj.drawStringWithShadow(text, x, y, color);
  }

  private int getDurability(ItemStack itemStack) {
    return itemStack.getMaxDamage() - itemStack.getItemDamage();
  }

}
