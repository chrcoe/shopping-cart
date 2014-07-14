package business.policy;

import business.Context;
import business.exceptions.PolicyException;
import business.exceptions.UserNotRegisteredException;

public class IsProductInStock extends Policy {

	@Override
	public void preCheck(Context context) throws PolicyException,
			UserNotRegisteredException {
		// TODO Auto-generated method stub

	}

	@Override
	public void postCheck(Context context) throws PolicyException {
		// TODO Auto-generated method stub

	}

}
