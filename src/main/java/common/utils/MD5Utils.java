package common.utils;
import java.math.BigInteger;
import java.security.MessageDigest;
public class MD5Utils
{
	/**
	 * 使用md5的算法进行加密
	 */
	public static String md5(String plainText)
	{
		byte[] secretBytes=null;
		String md5code=null;
		try
		{
			secretBytes=MessageDigest.getInstance("md5").digest(plainText.getBytes());
			// 16进制数字
			md5code=new BigInteger(1,secretBytes).toString(16);
			// 如果生成数字未满32位，需要前面补0
			for(int i=0;i<32-md5code.length();i++)
			{
				md5code="0"+md5code;
			}
		}
		catch(Exception e)
		{
			md5code=plainText;
		}
		return md5code;
	}
}