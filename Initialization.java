import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.DataOutputStream;
import java.io.UTFDataFormatException;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.EOFException;
class Initialization
{
	public static void main(String[] args)
	{
		File file_Datas = new File("/home/david/文档/myJava/myPetDatas");
		try
		{
			DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file_Datas)));
			output.writeInt(0);
			output.writeInt(100);
			output.writeUTF("David");
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
	}
}
