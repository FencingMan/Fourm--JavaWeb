package com.web.bean;

import java.sql.Date;

public class Message {
		
		private Integer mid;
		private Integer aid;
		private Integer userId;
		private Integer state;
		
		public Message() {
			
		}

		public Message(Integer mid, Integer aid, Integer userId, Integer state) {
			this.mid = mid;
			this.aid = aid;
			this.userId = userId;
			this.state = state;
		}


		public Integer getMid() {
			return mid;
		}


		public void setMid(Integer mid) {
			this.mid = mid;
		}


		public Integer getAid() {
			return aid;
		}


		public void setAid(Integer aid) {
			this.aid = aid;
		}


		public Integer getUserId() {
			return userId;
		}


		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		public Integer getState() {
			return state;
		}


		public void setState(Integer state) {
			this.state = state;
		}
		
}
