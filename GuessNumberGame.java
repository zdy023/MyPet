package alls.game;
import alls.game.GuessNumberMode;
import java.util.Random;
import java.util.Scanner;
import alls.game.Game;
/**
*<h1>猜数小游戏</h1>
*<p>
*	支持主人猜与宠物猜两种模式。宠物猜数时采用二分算法。
*</p>
*@author David Chang
*@version 1.1
*/
public class GuessNumberGame extends Game
{
	private GuessNumberMode mode;
	/**
	*构造方法.
	*<p>
	*	根据指定模式构造Game。
	*</p>
	*@param mode 游戏模式；可选值有：<br>GuessNumberMode.MASTER_GUESS_PET,GuessNumberMode.PET_GUESS_MASTER,分别表示主人猜与宠物猜两种模式。
	*/
	public GuessNumberGame(GuessNumberMode mode)
	{
		this.mode = mode;
	}
	/**
	*用于加载游戏的方法.
	*/
	public void load()
	{
		int objNumber = 0,inNumber = 0,counts = 0;
		char inChar = (char)0;
		Random r = new Random();
		Scanner s = new Scanner(System.in);
		System.out.print("Please input the range of the object-number: ");
		inNumber = s.nextInt();
		int p = 1,q = inNumber,mid;
		switch(mode)
		{
			case MASTER_GUESS_PET:
				objNumber = r.nextInt(inNumber)+1;
				System.out.print("Please input your guessed number: ");
				for(;(inNumber = s.nextInt())!=objNumber;)
				{
					counts++;
					if(inNumber>objNumber)
						System.out.println("too large.");
					else
						System.out.println("too small.");
					System.out.print("Please input your guessed number: ");
				}
				counts++;
				System.out.println("Congratulations!The correct answer!You guessed " + counts + " times in total.");
				break;
			case PET_GUESS_MASTER:
				mid = (p+q)>>1;
				for(;p<=q;)
				{
					System.out.print("Is the number " + mid + "? (y/n)");
					inChar = s.next().charAt(0);
					counts++;
					if(inChar=='y')
						break;
					System.out.print("Is the number I guessed bigger or smaller? (b/s)");
					inChar = s.next().charAt(0);
					if(inChar=='b')
						q = mid;
					else
						p = mid;
					mid = (p+q)>>1;
				}
				System.out.println("Hey,Mater,I totally guessed " + counts + " times.I'm excellent,am not I?");
				break;
		}
	}
	public static void main(String[] args)
	{
		new GuessNumberGame(GuessNumberMode.MASTER_GUESS_PET).load();
		new GuessNumberGame(GuessNumberMode.PET_GUESS_MASTER).load();
	}
}
