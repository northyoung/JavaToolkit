package com.xulingjue.jsouptest;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class JsoupTest {

	public static String clean(String key) {
		String unsafe = key;
		String safe = Jsoup.clean(unsafe, Whitelist.basicWithImages());
		System.out.println(safe);
		return safe;
	}

	public static class Test extends TestCase {
		private JsoupTest jt ;
		
		protected void setUp() {
			jt = new JsoupTest();
		}

		protected void tearDown() {
			jt = null;
		}

		private void t(String input, String result) {
			Assert.assertEquals(result, jt.clean(input));
		}

		public void test_basics() {
			t("<img src='xxx'/>>dd<>", "<img />");
		}

	}
}
