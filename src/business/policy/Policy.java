package business.policy;

import business.exceptions.PolicyException;

public abstract class Policy {
	private policy.Policy policyInfo;
	
	public abstract void preCheck(business.Context context) throws PolicyException;
	public abstract void postCheck(business.Context context) throws PolicyException;
	
	public policy.Policy getPolicyInfo() {
		return policyInfo;
	}
	public void setPolicyInfo(policy.Policy policyInfo) {
		this.policyInfo = policyInfo;
	}
	
}
