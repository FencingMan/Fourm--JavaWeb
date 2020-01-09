package com.web.bean;

import java.sql.Date;

public class Collectlike {
		
		private Integer clid ;
		private Integer aid ;
		private Integer userid ;
		private String cltype ;
		private String cltime;
		private Integer status;
		
		public Collectlike() {
			
		}
		
		public Collectlike(Integer clid, Integer aid, Integer userid,
				String cltype, String cltime, Integer status) {
			this.clid = clid;
			this.aid = aid;
			this.userid = userid;
			this.cltype = cltype;
			this.cltime = cltime;
			this.status = status;
		}
		
		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public Collectlike(Integer cid) {
			this.aid=cid;
		}

		public Integer getClid() {
			return clid;
		}

		public void setClid(Integer clid) {
			this.clid = clid;
		}

		public Integer getAid() {
			return aid;
		}

		public void setAid(Integer aid) {
			this.aid = aid;
		}

		public Integer getUserid() {
			return userid;
		}

		public void setUserid(Integer userid) {
			this.userid = userid;
		}

		


		public String getCltype() {
			return cltype;
		}

		public void setCltype(String cltype) {
			this.cltype = cltype;
		}

		public String getCltime() {
			return cltime;
		}

		public void setCltime(String cltime) {
			this.cltime = cltime;
		}

		@Override
		public String toString() {
			return "Collectlike [clid=" + clid + ", aid=" + aid + ", userid="
					+ userid + ", cltype=" + cltype + ", cltime=" + cltime
					+ ", status=" + status + "]";
		}

}
