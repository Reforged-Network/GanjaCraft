package com.reforgednetwork.ganjacraft.item.custom;

import com.reforgednetwork.ganjacraft.item.ModItems;
import com.reforgednetwork.ganjacraft.particle.ModParticles;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Blocks;
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class SpliffUse extends Item {
	public SpliffUse(Properties properties) {
		super(properties);
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);


		if (playerIn.isActualySwimming() || playerIn.isWet() || !canSmoke(worldIn, playerIn)) {
			return ActionResult.resultFail(itemstack);
		} else {
			playerIn.setActiveHand(handIn);
			return ActionResult.resultConsume(itemstack);
		}
	}

	//public static Vector3d testoffset = new Vector3d(-0.15, -0.05, 0);

	public static Vector3d fromPolar(float pitch, float yaw) {
		float f = MathHelper.cos(-yaw * ((float)Math.PI / 180) - (float)Math.PI);
		float g = MathHelper.sin(-yaw * ((float)Math.PI / 180) - (float)Math.PI);
		float h = -MathHelper.cos(-pitch * ((float)Math.PI / 180));
		float i = MathHelper.sin(-pitch * ((float)Math.PI / 180));
		return new Vector3d(g * h, i, f * h);
	}

	@Override
	public void onUse(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int countIn) {
		super.onUse(worldIn, livingEntityIn, stack, countIn);

		if (worldIn.isRemote) {
			float smokeStage = (float) Math.sin(((float) countIn / (float) this.getUseDuration(stack) * 8.0F));
			if (smokeStage < -0.5 || smokeStage > 0.5) {
				return;
			}
			Vector3d dirVec = fromPolar(livingEntityIn.rotationPitch, livingEntityIn.rotationYaw);
			Vector3d mouthPosition = new Vector3d(dirVec.x, dirVec.y, dirVec.z);

			mouthPosition = mouthPosition.scale(0.3);
			mouthPosition = mouthPosition.add(new Vector3d(livingEntityIn.getPosX(), livingEntityIn.getPosY() + livingEntityIn.getEyeHeight(), livingEntityIn.getPosZ()));
			mouthPosition = mouthPosition.add((new Vector3d(-0.15, -0.05, 0)).mul(dirVec));
			//mouthPosition.add(testoffset);
			Vector3d smokeVelocity = new Vector3d(dirVec.x, dirVec.y, dirVec.z);
			smokeVelocity = smokeVelocity.scale(0.01);
			smokeVelocity = smokeVelocity.add(new Vector3d(0, 0.03, 0));

			System.out.println(mouthPosition);
			System.out.println(smokeVelocity);

			for (int i = 0; i < 1; ++i) {
				worldIn.addParticle(ModParticles.SMOKE.get(), false, mouthPosition.x, mouthPosition.y, mouthPosition.z, smokeVelocity.x, smokeVelocity.y, smokeVelocity.z);
			}
		}
	}

	@Nonnull
	@Override
	public ItemStack onItemUseFinish (ItemStack stack, World worldIn, LivingEntity entityLiving){

		if (canSmoke(worldIn, entityLiving)) {
			entityLiving.addPotionEffect(new EffectInstance(Effects.SPEED, 400, 0));
			entityLiving.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 100, 0));
			entityLiving.addPotionEffect(new EffectInstance(Effects.HUNGER, 300, 1));

			if (entityLiving instanceof ServerPlayerEntity) {
				ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) entityLiving;
				CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
				serverplayerentity.getCooldownTracker().setCooldown(this, 200);
			}

			if (entityLiving instanceof PlayerEntity && !((PlayerEntity) entityLiving).abilities.isCreativeMode) {
				stack.shrink(1);
			}
		}
		return stack.isEmpty() ? new ItemStack(Blocks.AIR) : stack;
	}

	public int getUseDuration (ItemStack stack){
		return 32;
	}

	public UseAction getUseAction (ItemStack stack){
		return UseAction.BOW;
	}

	public boolean canSmoke (World worldIn, LivingEntity entityLiving){
		return (!worldIn.isRemote && entityLiving.getHeldItemOffhand().getItem() == ModItems.LIGHTER.get());
	}
}
