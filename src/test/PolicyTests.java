package test;

import static org.junit.Assert.*;
import model.User;

import org.junit.Test;

import business.policy.PolicyContext;

public class PolicyTests {

	@Test
	public void test_policy_context() {
		User u = new User();
		u.setUserID(1);
		Object[] contextParams = new Object[]{u};
		PolicyContext pc = new PolicyContext(contextParams);
		assertEquals(pc.get(User.class),u);
		User u1 = (User)pc.get(User.class);
		assertEquals(u1.getUserID(),1);
	}

}
