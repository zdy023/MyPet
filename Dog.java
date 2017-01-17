package alls;
import alls.pets.Pet;
public class Dog extends Pet
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
}
