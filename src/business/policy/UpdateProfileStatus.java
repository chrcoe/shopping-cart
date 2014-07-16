package business.policy;

import model.User;
import business.Context;
import business.exceptions.PolicyException;
import business.exceptions.UserNotRegisteredException;

public class UpdateProfileStatus extends Policy {

	@Override
	public void preCheck(Context context) throws PolicyException {}

	@Override
	public void postCheck(Context context) throws PolicyException {
		User u = (User) context.get(User.class);
		// get user orders
		// if user orders are > 5 and status is not premium
		//		update status to premium

	}

}
