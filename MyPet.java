package alls;
import alls.pets.Dog;
//import java.io.FileOutputStream;
//import java.io.FileInputStream;
//import java.io.BufferedOutputStream;
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
import java.util.Scanner;
//import java.io.UTFDataFormatException;
import java.io.IOException;
//import java.io.FileNotFoundException;
//import java.io.EOFException;
import alls.pets.Pet;
import alls.pets.DataManager;
import java.util.Random;
import alls.game.GuessNumberGame;
import alls.game.GuessNumberMode;
import java.util.ArrayList;
import alls.game.Game;
public class MyPet
{
	ArrayList<Game> games;
	ArrayList<String> description;
	MyPet()
	{
		games = new ArrayList<Game>();
		description = new ArrayList<String>();
		
		games.add(new GuessNumberGame(GuessNumberMode.MASTER_GUESS_PET));
		description.add("0: Master guesses the number thinked by the pet");
		
		games.add(new GuessNumberGame(GuessNumberMode.PET_GUESS_MASTER));
		description.add("1: Pet guesses the number thinked by the master");
	}
	public static void main(String[] args)
	{
		String name = "David";
		DataManager dm = new DataManager("ProgrammeDatas/MyPetDatas");
		try
		{
			dm.read();
		}
		catch(IOException e)
		{
			System.out.println(e);
			System.exit(0);
		}
		Pet myPet = new Dog(dm.getName(),dm.getAge(),dm.getHP());
		Scanner s = new Scanner(System.in);
		Random r = new Random();
		MyPet gameList = new MyPet();
		int gns = gameList.games.size();
		for(int i = 0;i<gns;i++)
			myPet.addGame(gameList.games.get(i));
		String command;
		boolean continueOrNot = true;
		while(continueOrNot)
		{
			try
			{
				Thread.sleep(420000L);
			}
			catch(InterruptedException e)
			{
				System.out.println(e);
			}
			myPet.minusHP(r.nextInt(4)+1);
			System.out.println("Age:" + myPet.getAge());
			System.out.println("HP:" + myPet.getHP());
			System.out.println("What would you like to do with the pet now?");
			command = s.next();
			switch(command)
			{
				case "exit":
				case "quit":
					continueOrNot = false;
					break;
				case "feed":
					System.out.print("Feed how many?[1-100]: ");
					myPet.feed(s.nextInt());
					System.out.println("Now the HP is " + myPet.getHP());
					break;
				case "play":
					System.out.println("Here is the game list:");
					for(int i = 0;i<gns;i++)
						System.out.println("\t" + gameList.description.get(i));
					System.out.println("Please type in the index of the game");
					myPet.play(s.nextInt());
					myPet.minusHP(r.nextInt(30)+1);
					break;
				case "walk":
					//do nothing
					myPet.minusHP(r.nextInt(5)+1);
					break;
			}
		}
		dm.update(myPet.getAge(),myPet.getHP(),myPet.getName());
		dm.flush();
		System.out.println("Exiting...");
	}
}
