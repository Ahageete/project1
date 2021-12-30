package common.emum;
public enum SystemEmum
{
	YES("Y"),
	NO("N");
	private String value;
	private SystemEmum(String value)
	{
		this.value=value;
	}
	public String getValue()
	{
		return value;
	}
	public void setValue(String value)
	{
		this.value=value;
	}
}
