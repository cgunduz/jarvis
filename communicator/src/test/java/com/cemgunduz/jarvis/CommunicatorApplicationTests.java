package com.cemgunduz.jarvis;

import com.cemgunduz.jarvis.communicatior.Communication;
import com.cemgunduz.jarvis.communicatior.Schedule;
import com.cemgunduz.jarvis.communicatior.communicatee.Communicatee;
import com.cemgunduz.jarvis.communicatior.people.People;
import com.cemgunduz.jarvis.communicatior.people.Priority;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.UUID;

public class CommunicatorApplicationTests {

	@Test
	public void communicateeLogicTest() {

		Communicatee communicatee = createTestCommunicatee();
		communicatee.addPeople(createLowProNormalPerson());
		communicatee.addPeople(createMediumProEasyPerson());
		communicatee.addPeople(createHighProHardPerson());
		communicatee.addPeople(createLowProNormalPerson());
		communicatee.addPeople(createMediumProEasyPerson());
		communicatee.addPeople(createHighProHardPerson());
		communicatee.addPeople(createLowProNormalPerson());
		communicatee.addPeople(createMediumProEasyPerson());
		communicatee.addPeople(createHighProHardPerson());

		communicatee.updateSchedule();
		communicatee.getSchedule().recordPreviousCommunicationsAndFlush();
		communicatee.updateSchedule();
		communicatee.getSchedule().recordPreviousCommunicationsAndFlush();
		communicatee.updateSchedule();
		communicatee.getSchedule().recordPreviousCommunicationsAndFlush();
		communicatee.updateSchedule();
		communicatee.getSchedule().recordPreviousCommunicationsAndFlush();
		List<Communication> communicationList = communicatee.updateSchedule();

		Assert.assertNotNull(communicationList);
	}

	private People createMediumProEasyPerson()
	{
		People people = new People();
		people.setName(UUID.randomUUID().toString());
		people.setPriority(Priority.MEDIUM);
		people.setRateOfCommunication(4);

		return people;
	}

	private People createHighProHardPerson()
	{
		People people = new People();
		people.setName(UUID.randomUUID().toString());
		people.setPriority(Priority.HIGH);
		people.setRateOfCommunication(2);

		return people;
	}

	private People createLowProNormalPerson()
	{
		People people = new People();
		people.setName(UUID.randomUUID().toString());
		people.setPriority(Priority.LOW);
		people.setRateOfCommunication(3);

		return people;
	}

	private Communicatee createTestCommunicatee()
	{
		Communicatee communicatee = new Communicatee();
		communicatee.createTestCommunicatee();
		return communicatee;
	}

}
