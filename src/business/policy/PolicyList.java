package business.policy;

import java.util.ArrayList;

public class PolicyList extends ArrayList<Policy> {
	private static final long serialVersionUID = 1L;

	public void preCheck() throws PolicyException{
		for(Policy p:this){
			p.preCheck();
		}
	}
	
	public void postCheck() throws PolicyException{
		for(Policy p:this){
			p.postCheck();
		}
	}
}
