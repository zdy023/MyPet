package alls;
import alls.Dog;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;
import java.io.UTFDataFormatException;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.EOFException;
public class MyPet
{
	public static void main(String[] args)
	{
		Dog myDog;
		String name = "David";
		int age = 0,hp = 100,times = 1;
		File file_Datas = new File("/home/david/文档/myJava/myPetDatas");
		try
		{
			DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream(file_Datas)));
			age = input.readInt();
			hp = input.readInt();
			name = input.readUTF();
			input.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e);
			System.exit(0);
		}
		catch(EOFException e)
		{
			System.out.println(e);
			System.exit(0);
		}
		catch(UTFDataFormatException e)
		{
			System.out.println(e);
			System.exit(0);
		}
		catch(IOException e)
		{
			System.out.println(e);
			System.exit(0);
		}
		myDog = new Dog(name,age,hp);
		Scanner s = new Scanner(System.in);
		char command;
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
			if(times==0)
			{
				myDog.grow();
				myDog.minusHP();
				myDog.minusHP();
			}
			times = (times==3)?0:(times+1);
			myDog.minusHP();
			System.out.println("Wang!\nAge:" + myDog.getAge());
			System.out.println("Wang!\nHP:" + myDog.getHP());
			System.out.println("Feed " + myDog.getName().toUpperCase() + " now or not? Please input y/n.");
			command = s.next().charAt(0);
			switch(command)
			{
				case '0':
					continueOrNot = false;
					break;
				case 'y':
					System.out.print("Feed how many?[1-100]: ");
					myDog.feed(s.nextInt());
					System.out.println("Now the HP is " + myDog.getHP());
					break;
			}
		}
		try
		{
			DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file_Datas)));
			output.writeInt(myDog.getAge());
			output.writeInt(myDog.getHP());
			output.writeUTF(myDog.getName());
			output.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e);
			System.exit(0);
		}
		catch(EOFException e)
		{
			System.out.println(e);
			System.exit(0);
		}
		catch(UTFDataFormatException e)
		{
			System.out.println(e);
			System.exit(0);
		}
		catch(IOException e)
		{
			System.out.println(e);
			System.exit(0);
		}
		System.out.println("Exiting...");
	}
}
/*数据文件格式：年龄（int)HP(int)名称（UTF)*/
