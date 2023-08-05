package com.laylataydragon.rustlemod.utility;

import java.util.List;
import net.minecraftforge.event.AddPackFindersEvent;
import java.util.ArrayList;

public class Genome {
	private List<String> sex;
	private List<String> color;
	private List<String> physical;
	
	public Genome(){
		sex = new ArrayList<String>();
		color = new ArrayList<String>();
		physical = new ArrayList<String>();
	}

	public void addPhysicalGene(String p)
	{
		physical.add(p);
	}

		public void addPhysicalGene(String[] p)
	{
		for(String i : p)
		{
			physical.add(i);
		}
	}

	public void addColorGene(String c)
	{
		color.add(c);
	}

	public void addColorGene(String[] c)
	{
		for(String i : c)
		{
			color.add(i);
		}
	}
	
	public String getSex()
	{
		String s = "";
		for(String a : sex)
			s+=a;
		return s;	
	}

	public void setSex(boolean s)
	{
		if(sex.size()<=0)
		{
			sex.add("X");
			if(s){
				sex.add("Y");
				return;
			}
			sex.add("X");
			return;
		}
		sex.set(0, "X");
		if(s){
			sex.set(1, "Y");
				return;
		}
		sex.set(1, "X");
	}
}
