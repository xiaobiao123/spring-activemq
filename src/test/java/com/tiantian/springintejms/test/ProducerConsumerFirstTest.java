package com.tiantian.springintejms.test;

import com.tiantian.springintejms.service.ProducerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Destination;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-first.xml")
public class ProducerConsumerFirstTest {

	@Autowired
	private ProducerService producerService;
	@Autowired
	@Qualifier("queueDestination")
	private Destination destination;

	@Test
	public void testSend() {
		for (int i=0; i<2; i++) {
			producerService.sendMessage(destination, "你好，生产者！这是消息：" + (i+1));
		}
	}

}
