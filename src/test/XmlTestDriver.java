package test;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import policy.Parameter;
import policy.Policy;
import policy.Transaction;
import policy.TransactionPolicy;

public class XmlTestDriver {

	public static void main(String[] args) throws JAXBException {
		System.out.println("Reading Policies.xml ...");
		InputStream is = XmlTestDriver.class.getResourceAsStream("Policies.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(TransactionPolicy.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		TransactionPolicy pol  = (TransactionPolicy) jaxbUnmarshaller.unmarshal(is);
		System.out.println("Found "+pol.getTransaction().size()+" transactions ...");
		for(Transaction t : pol.getTransaction()){
			System.out.println("Transaction: "+t.getId()+" has "+t.getPolicy().size()+" policies ...");
			for(Policy p : t.getPolicy()){
				System.out.println("\tPolicy: "+p.getId()+" has "+p.getParameter().size()+" parameters ...");
				for(Parameter pa : p.getParameter()){
					System.out.println("\t\tParamter: "+pa.getId()+" = "+pa.getValue());
				}
			}
		}
	}

}
