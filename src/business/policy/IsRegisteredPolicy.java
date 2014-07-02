package business.policy;

import model.User;

public class IsRegisteredPolicy extends Policy {

	public IsRegisteredPolicy(){}
	
	public IsRegisteredPolicy(PolicyContext context) {
		super(context);
	}

	@Override
	public void preCheck() throws PolicyException {
		User user = (User)super.getContext().get(User.class);
		if(!(user.getUserID()>0))throw new PolicyException("user is not registered");
	}

	@Override
	public void postCheck() throws PolicyException {

	}

}
