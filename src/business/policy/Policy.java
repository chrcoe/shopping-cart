package business.policy;


public abstract class Policy {
	private PolicyContext ctx;
	
	public Policy(){}
	public Policy(PolicyContext context){this.ctx = context;}
	
	public abstract void preCheck() throws PolicyException;
	public abstract void postCheck() throws PolicyException;
	
	public PolicyContext getContext() {
		return this.ctx;
	}

}
