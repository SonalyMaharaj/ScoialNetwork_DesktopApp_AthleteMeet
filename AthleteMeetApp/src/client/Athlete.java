package client;

public class Athlete implements Comparable<Athlete>
{
	//Declare user details variables
	private String username;
	private String age;
	private String sport;
	private String team;
	private String fitness;
	private String password;
	
	/**
	 * Constructor for signup
	 * @param username
	 * @param age
	 * @param sport
	 * @param team
	 * @param fitness
	 * @param password
	 */
	public Athlete(String username, String age, String sport, String team, String fitness, String password) 
	{
		this.username = username;
		this.age = age;
		this.sport = sport;
		this.team = team;
		this.fitness = fitness;
		this.password = password;
	}

	/**
	 * Constructor for login
	 * @param username
	 * @param password
	 */
	public Athlete(String username, String password) 
	{
		this.username = username;
		this.password = password;
	}

	/**
	 * @return the username
	 */
	public String getUsername() 
	{
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) 
	{
		this.username = username;
	}

	/**
	 * @return the age
	 */
	public String getAge() 
	{
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(String age) 
	{
		this.age = age;
	}

	/**
	 * @return the sport
	 */
	public String getSport() 
	{
		return sport;
	}

	/**
	 * @param sport the sport to set
	 */
	public void setSport(String sport) 
	{
		this.sport = sport;
	}

	/**
	 * @return the team
	 */
	public String getTeam() 
	{
		return team;
	}

	/**
	 * @param team the team to set
	 */
	public void setTeam(String team) 
	{
		this.team = team;
	}

	/**
	 * @return the fitness
	 */
	public String getFitness() 
	{
		return fitness;
	}

	/**
	 * @param fitness the fitness to set
	 */
	public void setFitness(String fitness) 
	{
		this.fitness = fitness;
	}

	/**
	 * @return the password
	 */
	public String getPassword() 
	{
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	@Override
	public String toString()
	{
		return this.username ;
	}

	@Override
	public int compareTo(Athlete o) 
	{
		return 0;
	}
	
}
