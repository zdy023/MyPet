package alls.pets;
/**
*<h1>Pet</h1>
*@author David Chang
*/
public abstract class Pet
{
	protected String name;
	protected int age;
	protected int hp;
	/**
	*构造函数.
	*<p>
	*	由名字构造一只新宠物，初始年龄为0岁，初始hp为100。
	*</p>
	*@param name the name of the pet
	*/
	public Pet(String name)
	{
		this.name = name;
		this.age = 0;
		this.hp = 100;
	}
	/**
	*重命名宠物.
	*@param name the new name of your pet
	*/
	public void setName(String name)
	{
		this.name = name;
	}
	/**
	*返回宠物的名字.
	*@return the name of the pet
	*/
	public String getName()
	{
		return this.name;
	}
	/**
	*成长.
	*<p>
	*	make the age increase.
	*</p>
	*/
	public void grow()
	{
		this.age++;
	}
	/**
	*返回宠物的年龄.
	*@return the age of the pet
	*/
	public int getAge()
	{
		return this.age;
	}
	/**
	*喂养.
	*<p>
	*	使hp增加，为你的宠物回血。不需要付出什么代价。
	*</p>
	*@param food How much you want to feed your pet
	*/
	public void feed(int food)
	{
		this.hp += food;
		this.hp = (this.hp>100)?100:this.hp;
	}
	/**
	*返回现在的hp.
	*@return the HP of the pet
	*/
	public int getHP()
	{
		return this.hp;
	}
	/**
	*使hp下降.
	*<p>
	*	正常的体力消耗。
	*</p>
	*/
	public void minusHP()
	{
		this.hp--;
	}
	/**
	*重生.
	*<p>
	*	若有需要，可以重生/复活。重生后年龄仍从零开始，但重生过程消耗了大量体力，故初始hp只有85。
	*</p>
	*/
	public void rebirth()
	{
		this.age = 0;
		this.hp = 85;
	}
	/**
	*获得物种名称，由子类实现.
	*@return the species name of your pet
	*/
	public abstract String getSpecies();
	/*class InLine extends Pet
	{
		public InLine(String name)
		{
			super(name);
		}
		public String getSpecies()
		{
			return "Default.";
		}
	}*/
	public static void main(String[] args)
	{
		Pet expe_ = new InLine("Just a test!");
		System.out.println(expe_.getName());
		expe_.setName("OK,OK,I'd changed the name.");
		System.out.println(expe_.getName());
		System.out.println("Age: " + expe_.getAge());
		expe_.grow();
		System.out.println("Age Now: " + expe_.getAge());
		expe_.grow();
		expe_.grow();
		System.out.println("This time I let it grow twince: " + expe_.getAge());
		System.out.println("HP: " + expe_.getHP());
		expe_.minusHP();
		System.out.println("HP Now: " + expe_.getHP());
		expe_.minusHP();
		expe_.minusHP();
		expe_.minusHP();
		System.out.println("HP-3: " + expe_.getHP());
		expe_.feed(3);
		System.out.println("And now if the hp is the same as the 2nd one the it's right with the method feed()" + expe_.getHP());
		expe_.rebirth();
		System.out.println(expe_.getSpecies() + " " + expe_.getAge() + " " + expe_.getHP());
	}
}
class InLine extends Pet
{
	public InLine(String name)
	{
		super(name);
	}
	public String getSpecies()
	{
		return "Default.";
	}
}
