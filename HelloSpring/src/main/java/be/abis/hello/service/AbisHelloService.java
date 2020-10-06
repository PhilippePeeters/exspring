package be.abis.hello.service;

import org.springframework.stereotype.Service;

import be.abis.hello.model.Person;

@Service
public class AbisHelloService implements HelloService {

	@Override
	public Person findPerson(int id) {
		Person p = new Person("Sandy");
		return p;
	}

}
