package business;

import java.util.HashMap;

public class Context {

	@SuppressWarnings("rawtypes")
	private HashMap<Class,Object> contextMap = new  HashMap<Class,Object>();
	
	public Context(Object[] objects){
		for(Object o:objects){
			contextMap.put(o.getClass(),o);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public Object get(Class clazz) {
		return contextMap.get(clazz);
	}
	
	public void put(Object obj){
		this.contextMap.put(obj.getClass(), obj);
	}

}
