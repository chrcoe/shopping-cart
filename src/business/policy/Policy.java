package business.policy;

public abstract class Policy {
	
	public abstract void preCheck(business.Context context) throws PolicyException;
	public abstract void postCheck(business.Context context) throws PolicyException;

}
