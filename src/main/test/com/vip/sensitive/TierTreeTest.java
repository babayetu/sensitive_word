package com.vip.sensitive;

import static org.junit.Assert.*;

import org.junit.Test;

public class TierTreeTest {

	@Test
	public void testTierTreeMatch() {
		TierTree sensitiveTree = new TierTree("one new tier tree");
		String[] array1 = new String[] {"中","国"};
		String[] array2 = new String[] {"垃","圾"};
		String[] array3 = new String[] {"屌","丝"};
		String[] array4 = new String[] {"国","足"};

		sensitiveTree.addtoTree(array1);
		sensitiveTree.addtoTree(array2);
		sensitiveTree.addtoTree(array3);
		sensitiveTree.addtoTree(array4);
		
//		sensitiveTree.printTierTree(sensitiveTree.getRoot(),0);
		
		String sendMesage = "中国足球太垃圾了";		
		assertTrue(sensitiveTree.isSensitive(sendMesage));
		
		String sendMesage2 = "国足垃圾了";		
		assertTrue(sensitiveTree.isSensitive(sendMesage2));
		
		String sendMesage3 = "国足好臭啊";		
		assertTrue(sensitiveTree.isSensitive(sendMesage3));
		
		String sendMesage4 = "垃圾";		
		assertTrue(sensitiveTree.isSensitive(sendMesage4));
		
		String sendMesage5 = "足球";		
		assertFalse(sensitiveTree.isSensitive(sendMesage5));
		
		//加入更长路径，使得国足不再是敏感词，不是叶子节点
		String[] array5 = new String[] {"国","足","垃","圾"};
		sensitiveTree.addtoTree(array5);
		assertFalse(sensitiveTree.isSensitive(sendMesage3));
	}

}
