package com.reforgednetwork.ganjacraft.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class Smoke extends SpriteTexturedParticle {

	private Smoke(ClientWorld world, double x, double y, double z, double motionX, double motionY, double motionZ, int maxAge) {
		super(world, x, y, z);
		this.multiplyParticleScaleBy(1.0F);
		this.setSize(0.5F, 0.5F);
		this.maxAge = maxAge;

		this.particleGravity = 3.0E-6F;
		this.motionX = motionX;
		this.motionY = motionY + (double) (this.rand.nextFloat() / 500.0F);
		this.motionZ = motionZ;
	}

	public void tick() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		if (this.age++ < this.maxAge && !(this.particleAlpha <= 0.0F)) {
			this.motionX += (double) (this.rand.nextFloat() / 5000.0F * (float) (this.rand.nextBoolean() ? 1 : -1));
			this.motionZ += (double) (this.rand.nextFloat() / 5000.0F * (float) (this.rand.nextBoolean() ? 1 : -1));
			this.motionY -= (double) this.particleGravity;
			this.move(this.motionX, this.motionY, this.motionZ);
			if (this.age >= this.maxAge - 60 && this.particleAlpha > 0.01F) {
				this.particleAlpha -= 0.015F;
			}

		} else {
			this.setExpired();
		}
	}

	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}

	@OnlyIn(Dist.CLIENT)
	public static class Factory implements IParticleFactory<BasicParticleType> {
		private final IAnimatedSprite spriteSet;

		public Factory(IAnimatedSprite spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			Smoke campfireparticle = new Smoke(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, 15);
			campfireparticle.setAlphaF(0.9F);
			campfireparticle.selectSpriteRandomly(this.spriteSet);
			return campfireparticle;
		}
	}

}
