package logic.beans;

import java.util.Calendar;

import logic.entity.User;

public class ViewProfileBean {
	
		private User requestedUser;
		private String requestedUsername;
		
	//all the sets on attributes
		
		public void setRequestedUsername(String requestedUsername) {
			this.requestedUsername = requestedUsername;
		}
		
		public void setRequesteUser(User requestedUser) {
			this.requestedUser = requestedUser;
		}
	
	//all the gets on attributes

		public String getRequestedUsername() {
			return this.requestedUsername;
		}
		
		public String getEmail() {
			return this.requestedUser.getEmail();
		}
		
		public String getCity() {
			return this.requestedUser.getCity();
		}
		
		public String getSurName() {
			return this.requestedUser.getSurname();
		}
		
		public String getName() {
			return this.requestedUser.getName();
		}
		
		public Calendar getBirthDate() {
			return this.requestedUser.getBDate();
		}
		
		public int getToken() {
			return this.requestedUser.getTokens();
		}
	
}
