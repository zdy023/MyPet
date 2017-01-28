package alls.pets;
//import java.io.FileOutputStream;
//import java.io.BufferedOutputStream;
import java.io.File;
//import java.io.DataOutputStream;
import java.io.UTFDataFormatException;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.EOFException;
//import java.io.DataInputStream;
//import java.io.BufferedInputStream;
//import java.io.FileInputStream;
import java.io.RandomAccessFile;
/**
*<h1>宠物数据管理器</h1>
*/
public class DataManager
{
	private File dataF;
	private RandomAccessFile raf;
	private int age,HP;
	private String name;
	private boolean initiatedOrNot;
	/**
	*根据给定的数据文件生成一个数据管理器.
	*<p>
	*	若数据文件不存在，会尝试创建一个新文件。
	*	若文件创建失败，则会产生一个FileNotFoundException，但不会抛出。
	*	构造数据管理器时并不会从数据文件中读取数据，程序会自动产生一组默认的初始化数据。
	*	可以通过isInitial()方法判断DataManager中的数据是否经过了用户的初始化。任何时候都应当保证在用户手动初始化之后再调用flush()方法写入数据。
	*</p>
	*@param fileName the path of the data file
	*/
	public DataManager(String fileName)
	{
		this.dataF = new File(fileName);
		
		try
		{
			raf = new RandomAccessFile(dataF,"rwd");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Unknown exception unable to open or create the file");
			System.out.println(e);
		}
		
		age = 0;
		HP = 100;
		name = "Default";
		initiatedOrNot = false;
	}
	/**
	*写入数据.
	*可能产生一系列异常造成无法正确写入数据，但不会抛出异常。
	*/
	public void flush()
	{
		try
		{
			raf.writeInt(age);
			raf.writeInt(HP);
			raf.writeUTF(name);
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e);
			System.exit(0);
		}
		catch(EOFException e)
		{
			//System.out.println("node 1");
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
	}
	/**
	*一次性更新数据.
	*<p>以此为代表的若干update方法都不会真正向文件写入数据，若希望数据立刻被写入文件，需调用flush()方法。</p>
	*@param age 新年龄
	*@param HP 新HP
	*@param name 新名字
	*/
	public void update(int age,int HP,String name)
	{
		this.age = age;
		this.HP = HP;
		this.name = name;
		this.initiatedOrNot = true;
	}
	/**
	*更新年龄.
	*@param age 新年龄
	*/
	public void updateAge(int age)
	{
		this.age = age;
		this.initiatedOrNot = true;
	}
	/**
	*获得年龄.
	*@return 年龄
	*/
	public int getAge()
	{
		return age;
	}
	/**
	*更新HP
	*@param HP 新HP
	*/
	public void updateHP(int HP)
	{
		this.HP = HP;
		this.initiatedOrNot = true;
	}
	/**
	*获得HP.
	*@return HP
	*/
	public int getHP()
	{
		return HP;
	}
	/**
	*更新名字.
	*@param name 新名字
	*/
	public void updateName(String name)
	{
		this.name = name;
		this.initiatedOrNot = true;
	}
	/**
	*获得名字.
	*@return 名字
	*/
	public String getName()
	{
		return name;
	}
	/**
	*从文件读入数据.
	*@exception IOException
	*/
	public void read() throws IOException
	{
		age = raf.readInt();
		HP = raf.readInt();
		name = raf.readUTF();
		initiatedOrNot = true;
	}
	/**
	*用于判断是否数据管理器内数据已经用户手动初始化.
	*<p>若不希望造成数据的丢失，则任何时候，在调用flush()写入数据之前都应保证该方法返回值为真。</p>
	*@return 一个布尔值<br>true-已初始化<br>false-未初始化，此时数据安全可能有风险
	*/
	public boolean isInitial()
	{
		return initiatedOrNot;
	}
	public static void main(String[] args)
	{
		DataManager dm = new DataManager("ProgrammeDatas/MyPetDatas");
		if(args.length>=3)
		{
			dm.update(Integer.parseInt(args[0]),Integer.parseInt(args[1]),args[2]);
			dm.flush();
		}
		else if(args.length==0)
		{
			try
			{
				dm.read();
				System.out.println(dm.getName() + " " + dm.getAge() + " " + dm.getHP());
			}
			catch(IOException e)
			{
				System.out.println(e);
			}
		}
		else
			System.out.println("The command format:\n\tjava DataManager age HP name\n或\tjava DataManager\n\tPlease input again.");
	}
}
/*数据文件格式：年龄（int)HP(int)名称（UTF)*/
