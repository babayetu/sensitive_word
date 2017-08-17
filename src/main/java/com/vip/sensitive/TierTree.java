package com.vip.sensitive;

import java.util.Map.Entry;


public class TierTree {
	private String name;
	private TierNode root;
	
	public TierTree(String name) {
		this.name = name;
		root = new TierNode("root");
	}
	
	
	public TierNode getRoot() {
		return root;
	}

	/**
	 * 匹配是采用最长匹配规则，如先加入“国足”，再加入“国足垃圾”
	 * 那么最终建立的树的叶子是“圾”，匹配规则是匹配到叶子节点
	 * 这样“国足”就不是敏感词，而“国足垃圾”是敏感词
	 * @param wordArray
	 */
	public void addtoTree(String[] wordArray) {
		if (wordArray.length < 1) {
			return;
		}
		
		TierNode current = root;
		
		for (int i = 0; i < wordArray.length; i++) {
			/**
			 * 寻找当前节点
			 */
			TierNode subNode = current.findMatchSubNode(wordArray[i]);
			/**
			 * 没找到，直接层级添加
			 */
			if (subNode == null) {
				int j = i;
				while (j < wordArray.length) {
					TierNode nextlevel = new TierNode(wordArray[j]);
					current.addSubNode(nextlevel);
					j++;
					current = nextlevel;
					
				}
				break;
			} else {
				current = subNode;
			}
		}
	}
	
	/**
	 * 字符最长匹配到叶子节点
	 * @param checkMsg
	 * @return
	 */
	public boolean isSensitive(String checkMsg) {
		String utf8Msg = CharUtils.toUtf8(checkMsg);
		if (utf8Msg == null) {
			return false;
		}
		
		for (int i = 0; i < utf8Msg.length(); i++) {
			//当前扫描开始位置
			int scanPos = i;
			TierNode current = getRoot();
			while(scanPos < utf8Msg.length()) {
				//TODO 逐个字符取出，此处可以优化
				String searchKey = utf8Msg.substring(scanPos, scanPos + 1);
				TierNode msn = current.findMatchSubNode(searchKey);
						
				if ( msn != null ) {
					if (msn.isSubNodeExist()) {
						current = msn;
						scanPos++;
					} else {
						/**
						 * 匹配到叶子节点，说明存在敏感词
						 */
						return true;
					}
					
				} else {
					break;
				}				
			}
			continue;
		}		
		return false;
	}
	
	/**
	 * 打印检查树结构
	 * @param rootTN
	 */
	public void printTierTree(TierNode rootTN, int level) {
		if (level != 0) {
			System.out.println("----level" + level +"---->");
		}
		
		if (rootTN != null) {
			TierNode current = rootTN;
			if (current.isSubNodeExist()) {
				for (Entry<String, TierNode> entry : current.getChm().entrySet()) {
					System.out.println(entry.getKey());
					printTierTree(entry.getValue(), level + 1);
				}
			}
		}
	}

}
