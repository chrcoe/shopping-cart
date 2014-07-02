package business;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import policy.Policy;
import policy.Transaction;
import policy.TransactionPolicy;
import business.policy.PolicyException;
import business.policy.PolicyList;

public abstract class UnitOfWork {
	
	public static UnitOfWork create(Class<?> workClass,TransactionPolicy policyGraph){
		UnitOfWork u = null;
		try {
			Constructor<?> c = workClass.getDeclaredConstructor();
			c.setAccessible(true);
			u = (UnitOfWork) c.newInstance();
			for(Transaction t:policyGraph.getTransaction()){
				if(workClass.getName().equalsIgnoreCase(t.getId())){
					for(Policy p:t.getPolicy()){
						Class policy = Class.forName(p.getId());
						business.policy.Policy bp = (business.policy.Policy) policy.getDeclaredConstructor().newInstance();
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
	
	public PolicyList policies = new PolicyList();
	
	protected abstract void execute();
	
	public void Go() throws PolicyException{
		this.policies.preCheck();
		execute();
		this.policies.postCheck();
	}
}
