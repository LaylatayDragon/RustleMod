package com.laylataydragon.rustlemod.entity.ai;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

import javax.annotation.Nullable;

import com.laylataydragon.rustlemod.client.renderer.RustleEntity;
import com.laylataydragon.rustlemod.entity.CowEntity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class AnimalBreedGoal extends Goal {
	
	   private static final TargetingConditions PARTNER_TARGETING = TargetingConditions.forNonCombat().range(8.0D).ignoreLineOfSight();
	   protected final RustleEntity animal;
	   private Class<? extends RustleEntity> partnerClass;
	   protected final Level level;
	   @Nullable
	   protected RustleEntity partner;
	   private int loveTime;
	   private final double speedModifier = 1;

	
	public AnimalBreedGoal(RustleEntity ani)
    {
        setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        animal = ani;
        level = ani.level;
        partnerClass = ani.getClass();
        this.partner = getFreePartner();
    }
	
	   @Nullable
	   private RustleEntity getFreePartner() {
	      List<? extends RustleEntity> list = this.level.getNearbyEntities(this.partnerClass, PARTNER_TARGETING, this.animal, this.animal.getBoundingBox().inflate(8.0D));
	      double d0 = Double.MAX_VALUE;
	      RustleEntity animal = null;

	      for(RustleEntity animal1 : list) {
	         if (this.animal.canMate(animal1) && this.animal.distanceToSqr(animal1) < d0) {
	        		 animal = animal1;
	        		 d0 = this.animal.distanceToSqr(animal1);
	         }
	      }

	      return animal;
	   }
	
	
	   public boolean canUse() {
		      if (!this.animal.isInLove()) {
		         return false;
		      } else {
		         this.partner = this.getFreePartner();
		         return this.partner != null;
		      }
		   }

		   public boolean canContinueToUse() {
		      return this.partner.isAlive() && this.partner.isInLove() && this.loveTime < 60;
		   }

		   public void stop() {
		      this.partner = null;
		      this.loveTime = 0;
		   }

		   public void tick() {
		      this.animal.getLookControl().setLookAt(this.partner, 10.0F, (float)this.animal.getMaxHeadXRot());
		      this.animal.getNavigation().moveTo(this.partner, this.speedModifier);
		      ++this.loveTime;
		      if (this.loveTime >= this.adjustedTickDelay(60) && this.animal.distanceToSqr(this.partner) < 9.0D) {
		         this.breed();
		      }

		   }
		   
		   protected void breed() {
			      this.animal.spawnChildFromBreeding((ServerLevel)this.level, this.partner);
			   }

}
