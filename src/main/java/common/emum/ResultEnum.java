package common.emum;
public enum ResultEnum
{
	/**
	 * add this Comment by jsm 异常编码分类目录
	 */
	// 标准
	UNKOWN(-1,"未知异常"),
	SUCCESS(0,""),
	// 登录
	LOGIN_NOUSER(100,"此用户不存在"),
	LOGIN_USER_PWD_ERROR(101,"用户密码不相符"),
	LOGIN_NOT(110,"未登录"),
	// 角色
	SEARCH_SYS_ROLE_LIST_ERROR(800,"查询角色列表异常"),
	SEARCH_SYS_ROLE_ERROR(801,"查询角色异常"),
	ADD_SYS_ROLE_ERROR(802,"添加角色异常"),
	UPDATE_SYS_ROLE_ERROR(803,"修改角色异常"),
	DELETE_SYS_ROLE_ERROR(804,"删除角色异常"),
	// 用户-角色
	SEARCH_SYS_USER_ROLE_LIST_ERROR(820,"查询用户角色列表异常"),
	ADD_SYS_USER_ROLE_ERROR(821,"添加用户角色异常"),
	DELETE_SYS_USER_ROLE_ERROR(822,"删除用户角色异常"),
	// 菜单
	SEARCH_SYS_MENU_LIST_ERROR(840,"查询菜单列表异常"),
	SEARCH_SYS_MENU_ERROR(841,"查询菜单异常"),
	ADD_SYS_MENU_ERROR(842,"添加菜单异常"),
	UPDATE_SYS_MENU_ERROR(843,"修改菜单异常"),
	DELETE_SYS_MENU_ERROR(844,"删除菜单异常"),
	// 用户
	SEARCH_SYS_USER_LIST_ERROR(880,"查询用户列表异常"),
	SEARCH_SYS_USER_ERROR(881,"查询用户异常"),
	ADD_SYS_USER_ERROR(882,"添加用户异常"),
	UPDATE_SYS_USER_ERROR(883,"修改用户异常"),
	DELETE_SYS_USER_ERROR(884,"删除用户异常"),
	UPDATE_SYS_USER_PASSWORD_ERROR(885,"修改用户密码异常"),
	ADD_SYS_MENU_ROLE_ERROR(861,"添加角色菜单异常"),
	DELETE_SYS_MENU_ROLE_ERROR(862,"删除角色菜单异常"),
	// cas
	RESTFUL_ERROR(1800,"restful接口异常"),
	SEOPS_PROPERTIES_ERROR(1801,"获取seopsProperties异常");
	private Integer code;
	private String message;
	private ResultEnum(Integer code,String message)
	{
		this.code=code;
		this.message=message;
	}
	public Integer getCode()
	{
		return code;
	}
	public void setCode(Integer code)
	{
		this.code=code;
	}
	public String getMessage()
	{
		return message;
	}
	public void setMessage(String message)
	{
		this.message=message;
	}
}
