package test;

import static org.junit.Assert.*;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import policy.TransactionPolicy;

public class XMLTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_xml_policy_config_reader() throws Exception {
		InputStream is = this.getClass().getResourceAsStream("Policies.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(TransactionPolicy.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		TransactionPolicy pol  = (TransactionPolicy) jaxbUnmarshaller.unmarshal(is);
		assertEquals(2,pol.getTransaction().size());
		assertEquals("business.CheckOut",pol.getTransaction().get(0).getId());
		//assertEquals(pol.getTransaction().get(1).getId(),"business.AddItemToCart");
	}

}
