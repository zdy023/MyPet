package alls.pets;
import alls.pets.Pet;
import alls.pets.Barkable;
public class Dog extends Pet implements Barkable
{
	private final static String SPECIES = "Dog";
	public Dog(String name,int age,int hp)
	{
		super(name);
		this.age = age;
		this.hp = hp;
	}
	public String getSpecies()
	{
		return this.SPECIES;
	}
	public String bark()
	{
		return "Wang";
	}
}
