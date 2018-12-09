package com.hk.Models;

import java.util.ArrayList;
import java.util.List;

import com.hk.components.QA;

public class QAModel {
	public static final int totalq = 3;
	private List<QA> qaList;
	
	public QAModel() {
		qaList = new ArrayList<QA>();
	}

	public List<QA> getQaList() {
		return qaList;
	}

	public void setQaList(List<QA> qaList) {
		this.qaList = qaList;
	}
}
