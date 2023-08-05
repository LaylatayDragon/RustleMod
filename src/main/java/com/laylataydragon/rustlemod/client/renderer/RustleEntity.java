package com.laylataydragon.rustlemod.client.renderer;

import com.laylataydragon.rustlemod.utility.Genome;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;

public abstract class RustleEntity extends Animal implements IAnimatable {

	private String disposition = "";
	public Genome geno = new Genome();
	public String animationprocedure;
	
	protected RustleEntity(EntityType<? extends Animal> p_27557_, Level p_27558_) {
		super(p_27557_, p_27558_);
		// TODO Auto-generated constructor stub
	}
	
	public String getSex()
	{
		return geno.getSex();
	}
	
	public void setSex(boolean t)
	{
		geno.setSex(t);
	}
	
	public void setDisposition(String d)
	{
		disposition = d;
	}
	
	public String getDisposition()
	{
		return disposition;
	}
	
	 public boolean canMate(RustleEntity p_27569_) {
	      if (p_27569_ == this) {
	         return false;
	      } else if (p_27569_.getClass() != this.getClass()) {
	         return false;
	      } else if(this.getSex().equals(p_27569_.getSex()))
	      {
	    	  return false;
	      } else if(!(this.getSex().equals("XX")||this.getSex().equals("XY")))
	      {
	    	  return false;
	      }
	      else {
	         return this.isInLove() && p_27569_.isInLove();
	      }
	   }
	
	public abstract String getSyncedAnimation();

	public abstract void setAnimation(String string);

}
