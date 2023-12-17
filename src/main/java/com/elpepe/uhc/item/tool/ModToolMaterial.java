package com.elpepe.uhc.item.tool;

import com.elpepe.uhc.item.ModItems;
import java.util.function.Supplier;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.ItemConvertible;

public enum ModToolMaterial implements ToolMaterial {
   NITHRIL(4, 512, 25.0F, 2.0F, 23, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{ModItems.NITHRIL_INGOT});
   }),
   EMERALD(0, 512, 0.0F, 5.5F, 19, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{Items.EMERALD});
   }),
   LAVA(0, 1016, 0.0F, 6.0F, 25, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{Items.LAVA_BUCKET});
   }),
   DRAGON(5, 2518, 70.0F, 7.0F, 25, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{Items.ENDER_EYE});
   });

   private final int miningLevel;
   private final int itemDurability;
   private final float miningSpeed;
   private final float attackDamage;
   private final int enchantability;
   private final Supplier<Ingredient> repairIngredient;

   private ModToolMaterial(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier repairIngredient) {
      this.miningLevel = miningLevel;
      this.itemDurability = itemDurability;
      this.miningSpeed = miningSpeed;
      this.attackDamage = attackDamage;
      this.enchantability = enchantability;
      this.repairIngredient = repairIngredient;
   }

   public int getDurability() {
      return this.itemDurability;
   }

   public float getMiningSpeedMultiplier() {
      return this.miningSpeed;
   }

   public float getAttackDamage() {
      return this.attackDamage;
   }

   public int getMiningLevel() {
      return this.miningLevel;
   }

   public int getEnchantability() {
      return this.enchantability;
   }

   public Ingredient getRepairIngredient() {
      return (Ingredient)this.repairIngredient.get();
   }
}
