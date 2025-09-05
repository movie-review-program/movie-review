package org.example.util;

import org.example.model.dto.User;

public class SessionManagerImpl implements SessionManager {

	private static SessionManagerImpl instance = new SessionManagerImpl();
	private User loggedInUser;

	private SessionManagerImpl() {
	}

	public static SessionManagerImpl getInstance() {
		return instance;
	}

	@Override
	public void setLoggedInUser(User user) {
		this.loggedInUser = user;
	}

	@Override
	public User getLoggedInUser() {
		return this.loggedInUser;
	}

	@Override
	public void logout() {
		this.loggedInUser = null;
	}
}
