package test;

import static org.junit.Assert.*;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import policy.TransactionPolicy;
import business.CheckOut;
import business.UnitOfWork;

public class UnitOfWorkTests {
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
	
	@Test
	public void test_create_unitofwork() {
		UnitOfWork co = UnitOfWork.create(CheckOut.class, policyGraph);
		assertNotNull(co);
	}

}
