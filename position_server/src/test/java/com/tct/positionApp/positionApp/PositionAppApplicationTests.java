package com.tct.positionApp.positionApp;

import com.tct.positionApp.domain.Status;
import com.tct.positionApp.service.StatusService;
import com.tct.positionApp.util.RedisUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableCaching
public class PositionAppApplicationTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
    private StatusService statusService;
	@Autowired
    private RedisUtils redisUtils;

	@Test
	public void test() throws Exception {
		stringRedisTemplate.opsForValue().set("aaa", "111");
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
	}

	@Test
	public void testObj() throws Exception {
		Status status=new Status();
		status.setId(1);
		status.setStatusName("qxt");
		ValueOperations<String, Status> operations=redisTemplate.opsForValue();
		operations.set("com.neox", status);
		operations.set("com.neo.f", status,20, TimeUnit.SECONDS);
		Thread.sleep(1000);
		//redisTemplate.delete("com.neo.f");
		boolean exists=redisTemplate.hasKey("com.neo.f");
		if(exists){
			System.out.println("exists is true");
		}else{
			System.out.println("exists is false");
		}
		// Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
		System.out.println(operations.get("com.neo.f").getStatusName());
		System.out.println(operations.get("com.neox").getStatusName());
	}


	@Test
	public void contextLoads() {

            Status status = statusService.getStatus(2);
            System.out.println(status);

	}

    @Test
    public void test2() throws Exception {
        System.out.println();
    }
}

