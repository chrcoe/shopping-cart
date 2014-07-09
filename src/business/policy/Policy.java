package business.policy;

import business.exceptions.PolicyException;
import business.exceptions.UserNotRegisteredException;

public abstract class Policy {
	
	public abstract void preCheck(business.Context context) throws PolicyException, UserNotRegisteredException;
	public abstract void postCheck(business.Context context) throws PolicyException;

}
