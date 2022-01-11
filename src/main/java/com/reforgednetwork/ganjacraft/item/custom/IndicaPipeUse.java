package com.reforgednetwork.ganjacraft.item.custom;

import com.reforgednetwork.ganjacraft.item.ModItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
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



	@Nonnull
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		float chance = 0.35f;

		if (!worldIn.isRemote) {
			entityLiving.addPotionEffect(new EffectInstance(Effects.NAUSEA, 200, 1));
			entityLiving.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 1));
		    entityLiving.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 200, 0));


		} else if (chance <= random.nextFloat()) {
		}

		if (entityLiving instanceof ServerPlayerEntity) {
			ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)entityLiving;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);

			serverplayerentity.getCooldownTracker().setCooldown(this, 200);
		}

		if (entityLiving instanceof PlayerEntity && !((PlayerEntity)entityLiving).abilities.isCreativeMode) {
			stack.shrink(1);
		}

		return stack.isEmpty() ? new ItemStack(ModItems.EMPTYPIPE.get()) : stack;
		}



	public int getUseDuration(ItemStack stack) {
		return 32;
	}

	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BOW;
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);


		if (playerIn.abilities.isCreativeMode) {
			return ActionResult.resultFail(itemstack);
		} else {
			playerIn.setActiveHand(handIn);
			return ActionResult.resultConsume(itemstack);
		}
	}

}
