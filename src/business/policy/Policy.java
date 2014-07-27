package business.policy;

import business.exceptions.PolicyException;

public abstract class Policy {
	private policy.Policy policyInfo;
	
	public abstract void rule(business.Context context) throws PolicyException;
	
	public void preCheck(business.Context context) throws PolicyException{
		if("Pre".equalsIgnoreCase(getPolicyInfo().getRule())){
			rule(context);
		}
	}
	public void postCheck(business.Context context) throws PolicyException{
		if("Post".equalsIgnoreCase(getPolicyInfo().getRule())){
			rule(context);
		}
	}
	
	public policy.Policy getPolicyInfo() {
		return policyInfo;
	}
	public void setPolicyInfo(policy.Policy policyInfo) {
		this.policyInfo = policyInfo;
	}
	
}
