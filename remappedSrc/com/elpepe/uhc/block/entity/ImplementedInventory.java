package com.elpepe.uhc.block.entity;

import net.minecraft.class_1262;
import net.minecraft.class_1278;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_2350;
import net.minecraft.class_2371;
import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface ImplementedInventory extends class_1278 {
   class_2371<class_1799> getItems();

   static ImplementedInventory of(class_2371<class_1799> items) {
      return () -> {
         return items;
      };
   }

   static ImplementedInventory ofSize(int size) {
      return of(class_2371.method_10213(size, class_1799.field_8037));
   }

   default int[] method_5494(class_2350 side) {
      int[] result = new int[this.getItems().size()];

      for(int i = 0; i < result.length; result[i] = i++) {
      }

      return result;
   }

   default boolean method_5492(int slot, class_1799 stack, @Nullable class_2350 side) {
      return true;
   }

   default boolean method_5493(int slot, class_1799 stack, class_2350 side) {
      return true;
   }

   default int method_5439() {
      return this.getItems().size();
   }

   default boolean method_5442() {
      for(int i = 0; i < this.method_5439(); ++i) {
         class_1799 stack = this.method_5438(i);
         if (!stack.method_7960()) {
            return false;
         }
      }

      return true;
   }

   default class_1799 method_5438(int slot) {
      return (class_1799)this.getItems().get(slot);
   }

   default class_1799 method_5434(int slot, int count) {
      class_1799 result = class_1262.method_5430(this.getItems(), slot, count);
      if (!result.method_7960()) {
         this.method_5431();
      }

      return result;
   }

   default class_1799 method_5441(int slot) {
      return class_1262.method_5428(this.getItems(), slot);
   }

   default void method_5447(int slot, class_1799 stack) {
      this.getItems().set(slot, stack);
      if (stack.method_7947() > this.method_5444()) {
         stack.method_7939(this.method_5444());
      }

      this.method_5431();
   }

   default void method_5448() {
      this.getItems().clear();
   }

   default void method_5431() {
   }

   default boolean method_5443(class_1657 player) {
      return true;
   }
}
