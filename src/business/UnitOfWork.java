package business;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import policy.Policy;
import policy.Transaction;
import policy.TransactionPolicy;
import business.exceptions.PolicyException;
import business.policy.PolicyList;

public abstract class UnitOfWork {
	
	protected business.Context ctx;
	private PolicyList policies = new PolicyList();
	private ICallBackDelegate delegate = null;
	
	public static UnitOfWork create(Class<?> workClass,TransactionPolicy policyGraph){
		UnitOfWork u = null;
		try {
			Constructor<?> c = workClass.getDeclaredConstructor();
			c.setAccessible(true);
			u = (UnitOfWork) c.newInstance();
			for(Transaction t:policyGraph.getTransaction()){
				if(workClass.getName().equalsIgnoreCase(t.getId())){
					for(Policy polInfo : t.getPolicy()){
						Class<?> policy = Class.forName(polInfo.getId());
						business.policy.Policy bp = (business.policy.Policy) policy.getDeclaredConstructor().newInstance();
						bp.setPolicyInfo(polInfo);
						u.policies.add(bp);
					}
					break;
				}
			}
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	
	protected abstract void execute() throws PolicyException;
	
	public void Go() throws PolicyException{
		this.policies.preCheck(ctx);
		if(this.delegate==null) execute(); else this.delegate.execute();
		this.policies.postCheck(ctx);
	}

	public UnitOfWork with(Object contextObject) {
		this.ctx = new business.Context(new Object[]{contextObject});
		return this;
	}
	public UnitOfWork with(Object[] contextObjectArray) {
		this.ctx = new business.Context(contextObjectArray);
		return this;
	}
	public UnitOfWork with(business.Context context) {
		this.ctx = context;
		return this;
	}
	
	public UnitOfWork using(ICallBackDelegate delegateWork){
		this.delegate = delegateWork;
		return this;
	}

	public PolicyList getPolicies() {
		return policies;
	}

	public void setPolicies(PolicyList policies) {
		this.policies = policies;
	}
	
	public interface ICallBackDelegate{
		void execute();
	}
}
