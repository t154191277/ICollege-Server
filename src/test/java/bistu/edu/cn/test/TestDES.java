package bistu.edu.cn.test;

import java.util.UUID;

import org.junit.Test;

import bistu.edu.cn.utils.DESUtil;

public class TestDES {
	
//	@Test
	public void test() {
		String crypto;
		try {
			crypto  = DESUtil.encrypt("aAbB5459600");
			System.out.println(crypto);
			crypto = DESUtil.decrypt(crypto);
			System.out.println(crypto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Test
	public void testUUID()
	{
		System.out.println(UUID.randomUUID().toString().length());
	}
}
