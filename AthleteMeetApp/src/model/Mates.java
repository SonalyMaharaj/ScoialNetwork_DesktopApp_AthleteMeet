package model;

import java.util.ArrayList;
import client.Athlete;

public class Mates 
{
	//variables to declare the current user and new user
	private Athlete newAthlete;
	private Athlete currentAthlete;
	//arraylist of connected nodes
	private ArrayList<String> mates;
	
	public Mates(Athlete newAthlete, Athlete currentAthlete)
	{
		this.newAthlete = newAthlete;
		this.currentAthlete = currentAthlete;
		
		mates = new ArrayList<>();
		
		//checking for matching or common attributes 
		//if there is a match, connect these users
		if(newAthlete.getAge().equals(currentAthlete.getAge()))
		{
			mates.add(newAthlete.getAge());
		}
		
		if(newAthlete.getSport().equals(currentAthlete.getSport()))
		{
			mates.add(newAthlete.getSport());
		}
		
		if(newAthlete.getTeam().equals(currentAthlete.getTeam()))
		{
			mates.add(newAthlete.getTeam());
		}
		
		if(newAthlete.getFitness().equals(currentAthlete.getFitness()))
		{
			mates.add(newAthlete.getFitness());
		}
	}
	
	public ArrayList<String> getMates()
	{
		return mates;
	}
	
	@Override
	public String toString()
	{
		return mates.size() + "";
	}
}
