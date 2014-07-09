package business.policy;

import business.Context;
import model.User;

public class IsRegisteredPolicy extends Policy {

	@Override
	public void preCheck(Context context) throws PolicyException {
		User user = (User)context.get(User.class);
		if(!(user.getUserID()>0))throw new PolicyException("user is not registered");
	}

	@Override
	public void postCheck(Context context) throws PolicyException {
		// TODO Auto-generated method stub
		
	}

}
