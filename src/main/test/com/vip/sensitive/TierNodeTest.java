package com.vip.sensitive;

import static org.junit.Assert.*;

import org.junit.Test;

public class TierNodeTest {

	@Test
	public void testEquals() {
		TierNode tn1 = new TierNode("中国");
		TierNode tn2 = new TierNode("中国足球");
		TierNode tn3 = new TierNode("中国");

		assertNotEquals(tn1,tn2);
		assertEquals(tn1,tn3);
	}
	
	@Test
	public void testPutNode() {
		TierNode tn1 = new TierNode("中");
		tn1.addSubNode(new TierNode("国"));
		tn1.addSubNode(new TierNode("央"));
		tn1.addSubNode(new TierNode("立"));
		assertTrue(tn1.containSubNodeWord("国"));
		assertFalse(tn1.containSubNodeWord("心"));
	}
	
	@Test
	public void testFindMatchSubNode() {
		TierNode tn1 = new TierNode("中");
		tn1.addSubNode(new TierNode("国"));
		tn1.addSubNode(new TierNode("央"));
		tn1.addSubNode(new TierNode("立"));
		
		TierNode fmsn = tn1.findMatchSubNode("立");
		assertTrue(fmsn.getWord().equals("立"));
	}
}
