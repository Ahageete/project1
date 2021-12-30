package project.common.mapper.provider;
import java.lang.reflect.Field;
import javax.persistence.Table;
import org.apache.ibatis.jdbc.SQL;
public class CommonKeywordSearchProvider
{
	public String QueryString(String condition,Class<?> targetClass)
	{
		SQL sql=new SQL();
		((SQL)sql.SELECT("*")).FROM(((Table)targetClass.getAnnotation(Table.class)).name());
		if((condition!=null)&&(condition!=""))
		{
			Field[] arrayOfField;
			int j=(arrayOfField=targetClass.getDeclaredFields()).length;
			for(int i=0;i<j;i++)
			{
				Field field=arrayOfField[i];
				if((!field.getName().equals("spwd"))&&(!field.getName().equals("password"))&&(!field.getName().equals("id"))&&(!field.getName().equals("serialVersionUID"))&&(field.getType().getSimpleName().equals("String")))
					((SQL)sql.OR()).WHERE("upper("+field.getName().toUpperCase()+") like '%"+condition+"%'");
			}
		}
		System.out.println(sql);
		return sql.toString();
	}
}
