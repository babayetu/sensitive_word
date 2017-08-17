package com.vip.sensitive;

import java.util.concurrent.ConcurrentHashMap;

public class TierNode implements Comparable<TierNode> {
	private String word = null;
	private ConcurrentHashMap<String,TierNode> chm = null;

	public TierNode(String word) {
		this.word = CharUtils.toUtf8(word);
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = CharUtils.toUtf8(word);
	}
	
	
	/**
	 * test only, should change to immutable
	 * @return
	 */
	public ConcurrentHashMap<String, TierNode> getChm() {
		return chm;
	}

	public synchronized void addSubNode(TierNode subNode) {
		if (chm == null) {
			chm = new ConcurrentHashMap<String,TierNode>();
		}
		
		if (!chm.containsKey(subNode.getWord())) {
			chm.put(subNode.getWord(), subNode);
		}
	}
	
	public boolean containSubNodeWord(String searchKey) {
		if (chm == null) {
			return false;
		}
		if (chm.containsKey(searchKey)) {
			return true;
		}
		return false;
	}
	
	public TierNode findMatchSubNode(String searchKey) {
		if (chm == null) {
			return null;
		}
		return chm.get(searchKey);
	}
	
	public boolean isSubNodeExist() {
		return chm != null;
	}



	@Override
	public int compareTo(TierNode paramT) {
		if (word==null) {
			return -1;
		}
		
		if (paramT.getWord() == null) {
			return 1;
		}
		
		return word.compareTo(paramT.getWord());
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TierNode other = (TierNode) obj;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}
	
}
