package com.reforgednetwork.ganjacraft.item.custom;

import com.reforgednetwork.ganjacraft.item.ModItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class IndicaPipeUse  extends Item {
	public IndicaPipeUse(Properties properties) {
		super(properties);
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);


		if (playerIn.isActualySwimming()) {
			return ActionResult.resultFail(itemstack);
		} else {
			playerIn.setActiveHand(handIn);
			return ActionResult.resultConsume(itemstack);
		}
	}

	@Nonnull
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
//		float chance = 0.50f;

		if (!worldIn.isRemote && entityLiving.getHeldItemOffhand().getItem() == ModItems.LIGHTER.get()) {
				entityLiving.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 800, 1));
				entityLiving.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 1));
				entityLiving.addPotionEffect(new EffectInstance(Effects.HUNGER, 600, 0));

			}

		if (entityLiving instanceof ServerPlayerEntity) {
			ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)entityLiving;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
			serverplayerentity.getCooldownTracker().setCooldown(this, 200); }

		if (entityLiving instanceof PlayerEntity && !((PlayerEntity)entityLiving).abilities.isCreativeMode) {
			stack.shrink(1); 	}
		return stack.isEmpty() ? new ItemStack(ModItems.EMPTYPIPE.get()) : stack;
		}

	public int getUseDuration(ItemStack stack) {
		return 32;
	}

	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BOW;
	}


}
