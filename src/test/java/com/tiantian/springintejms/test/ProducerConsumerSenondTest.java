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
@ContextConfiguration("/applicationContext-second.xml")
public class ProducerConsumerSenondTest {

	@Autowired
	private ProducerService producerService;
	@Autowired
	@Qualifier("sessionAwareQueue")
	private Destination sessionAwareQueue;

	@Test
	public void testSessionAwareMessageListener() {
		producerService.sendMessage(sessionAwareQueue, "测试SessionAwareMessageListener");
	}

}
