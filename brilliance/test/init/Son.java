package init;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * java类初始化顺序——继承关系
* @ClassName: Son
* @Package init
* @Description: 
* 
* 初始化顺序：父静态变量赋值>父静态代码块>子静态变量赋值>子静态代码块>父自由代码块/构造代码块>子自由代码块/构造代码块>父构造方法>子构造方法
* 
* @author Russell Xun Jiang
* @date 2016年10月29日 下午8:00:00
*/
public class Son extends Father{
	static Log logger = LogFactory.getLog(Son.class);

	//静态变量
	private static String staticField2 = "子类静态变量/属性";
	//非静态变量
	private String field2 = "子类变量/属性";
	
	//静态代码块
	static{
		logger.info("子类静态代码块中,使用静态变量"+staticField2);//这边不能引用非静态变量field2，因为非静态变量后与静态代码块
		logger.info("子类静态代码块");
	}
	
	//自由代码快/构造代码块
	{
		logger.info("子类自由代码块,使用静态变量"+staticField2+",普通变量field2"+field2);
	}
	
	//构造方法
	public Son(){
		logger.info("子类构造方法");
	}
	
	//程序主入口
	public static void main(String[] args){
//		//创建子类对象
//		callNewObject();
		
		//只使用类中静态变量
		callStaticField();
	}
	
	/** 
	* @Title: callNewObject 
	* @Description: 创建子类对象 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* 会依次执行父类和子类的：静态变量、静态代码块、自由代码块、构造函数
	*/ 
	
	public static void callNewObject(){
		new Son();
		/**输出：
		 *  [init.Father] 父类静态代码块中,使用静态变量父类静态变量/属性
 			[init.Father] 父类静态代码块
			[init.Son] 子类静态代码块中,使用静态变量子类静态变量/属性
			[init.Son] 子类静态代码块
			[init.Father] 父类自由代码块,使用静态变量父类静态变量/属性,普通变量field父类变量/属性
			[init.Father] 父类构造方法
			[init.Son] 子类自由代码块,使用静态变量子类静态变量/属性,普通变量field2子类变量/属性
			[init.Son] 子类构造方法
		 */
	}
	
	/** 
	* @Title: callStaticField 
	* @Description: 直接使用类中静态属性 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	* 不会创建父类和子类对象（不会调用构造方法和自由代码块）。
	* 只会调用父类和子类的静态变量、静态代码块 
	*/ 
	public static void callStaticField(){
		String a = Son.staticField2;
		/**输出
		 *  [init.Father] 父类静态代码块中,使用静态变量父类静态变量/属性
			[init.Father] 父类静态代码块
			[init.Son] 子类静态代码块中,使用静态变量子类静态变量/属性
			[init.Son] 子类静态代码块
		 * 
		 */
	}
}
