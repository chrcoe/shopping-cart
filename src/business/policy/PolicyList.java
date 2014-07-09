package business.policy;

import java.util.ArrayList;

import business.Context;
import business.exceptions.PolicyException;

public class PolicyList extends ArrayList<Policy> {
	private static final long serialVersionUID = 1L;

	public void preCheck(Context ctx) throws PolicyException{
		for(Policy p:this){
			p.preCheck(ctx);
		}
	}
	
	public void postCheck(Context ctx) throws PolicyException{
		for(Policy p:this){
			p.postCheck(ctx);
		}
	}
}
