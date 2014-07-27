package business.policy;

import business.Context;
import business.exceptions.PolicyException;
import business.exceptions.UserNotRegisteredException;
import model.User;

public class IsUserRegistered extends Policy {

	@Override
	public void rule(Context context) throws PolicyException {
		User user = (User)context.get(User.class);
		if(!(user.getUserID()>0))throw new UserNotRegisteredException("user is not registered");
	}

}
