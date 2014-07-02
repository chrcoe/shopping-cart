package test;

import static org.junit.Assert.*;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import model.User;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import policy.TransactionPolicy;
import business.AddItemToCart;
import business.CheckOut;
import business.UnitOfWork;
import business.policy.IsRegisteredPolicy;
import business.policy.Policy;
import business.policy.PolicyContext;
import business.policy.PolicyException;

public class BusinessPolicyTests {
	TransactionPolicy policyGraph;
	
	{
		InputStream is = this.getClass().getResourceAsStream("Policies.xml");
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(TransactionPolicy.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			policyGraph  = (TransactionPolicy) jaxbUnmarshaller.unmarshal(is);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void test_PolicyException() throws PolicyException{
		UnitOfWork uow = new AddItemToCart();
		uow.policies.add(new Policy(new PolicyContext(new Object[]{})){
			@Override
			public void preCheck() throws PolicyException {
				throw new PolicyException("Item cannot be reserved");
			}

			@Override
			public void postCheck() throws PolicyException {
				// TODO Auto-generated method stub
			}});

		exception.expect(PolicyException.class);
		uow.Go();
	}
	
	@Test
	public void test_checkout_policies_unregistered() throws PolicyException{
		final User u = new User();
		
		UnitOfWork co = UnitOfWork.create(CheckOut.class, policyGraph);
		
		co.policies.add(new IsRegisteredPolicy(new PolicyContext(new Object[]{u})));
		exception.expect(PolicyException.class);
		co.Go();
	}
	
	@Test
	public void test_checkout_policies_registered() throws PolicyException{
		exception = ExpectedException.none();
		final User u = new User();
		u.setUserID(1);
		UnitOfWork co = UnitOfWork.create(CheckOut.class, policyGraph);
		co.policies.add(new IsRegisteredPolicy(new PolicyContext(new Object[]{u})));
		co.Go();
	}

}
