package com.it.springbootrabbitmq;

import com.it.springbootrabbitmq.fanout.FanoutSender;
import com.it.springbootrabbitmq.queuedemo.Sender;
import com.it.springbootrabbitmq.topic.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbooTrabbitmqApplicationTests {

	@Autowired
	private Sender sender;

	@Autowired
	private FanoutSender fanoutSender;

	@Autowired
	private TopicSender topicSender;

	@Test
	public void testTopic(){
		topicSender.send();
	}


	@Test
	public void contextLoads() {
		sender.send();
	}

	@Test
	public void testFanoutSender(){
		fanoutSender.send();
	}

}
