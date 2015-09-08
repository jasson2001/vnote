/*
 * 作业题
 * 原来是针对选择题，
 * 可扩展用于一般的问题，id_optionals = null
 */
package com.example.geoquiz.persistence;

public class Question {
	public String id = null;

	public String voiceUrl = null;

	public String imageUrl = null;

	public String tip = null;

	public String[] id_optionals = null;

	public String id_answer = null;

	public Question(String tip, String imageUrl) {
		this.tip = tip;
		this.imageUrl = imageUrl;
	}

	public Question(String id) {
		this.id = id;
	}

	public String getOptionalString() {
		if (this.id_optionals != null) {
			StringBuffer lsb = new StringBuffer();
			for (int li = 0; li < this.id_optionals.length; li++) {
				lsb.append(id_optionals[li]);
				lsb.append(",");
			}
			return lsb.toString();
		} else {
			return null;
		}
	}

	public String toStr() {
		StringBuffer lsb = new StringBuffer();
		lsb.append(this.tip);
		System.out.println("qustion = " + this.id);
		lsb.append("~");
		for (int li = 0; li < this.id_optionals.length; li++) {
			lsb.append(id_optionals[li]);
			lsb.append("`");
		}
		lsb.append("~");
		lsb.append(this.id_answer);
		return lsb.toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId_answer() {
		return id_answer;
	}

	public void setId_answer(String id_answer) {
		this.id_answer = id_answer;
	}

	public String[] getId_optionals() {
		return id_optionals;
	}

	public void setId_optionals(String[] id_optionals) {
		this.id_optionals = id_optionals;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getQuestion() {
		return tip;
	}

	public void setQuestion(String question) {
		this.tip = question;
	}

	public String getVoiceUrl() {
		return voiceUrl;
	}

	public void setVoiceUrl(String voiceUrl) {
		this.voiceUrl = voiceUrl;
	}
}
