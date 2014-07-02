package business.policy;

import java.util.HashMap;

public class PolicyContext {

	private HashMap<Class,Object> contextMap = new  HashMap<Class,Object>();
	
	public PolicyContext(Object[] objects){
		for(Object o:objects){
			contextMap.put(o.getClass(),o);
		}
	}
	
	public Object get(Class clazz) {
		return contextMap.get(clazz);
	}

}
