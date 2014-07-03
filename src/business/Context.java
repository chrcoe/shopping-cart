package business;

import java.util.HashMap;

public class Context {

	private HashMap<Class,Object> contextMap = new  HashMap<Class,Object>();
	
	public Context(Object[] objects){
		for(Object o:objects){
			contextMap.put(o.getClass(),o);
		}
	}
	
	public Object get(Class clazz) {
		return contextMap.get(clazz);
	}

}
