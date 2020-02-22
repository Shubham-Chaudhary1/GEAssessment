package com.ge.exercise1;

import java.util.ArrayList;

public class GroupImpl extends Group {

	// add a list of user as each group contains a list of users
	ArrayList<UserImpl> listUser;

	public GroupImpl(String id, String name) {
		super(id, name);
		this.listUser = new ArrayList<>();
	}

	// methods to add and delete user
	public void addUser(UserImpl user) {
		listUser.add(user);
		size++;
	}

	public void deleteUser(UserImpl user) {
		listUser.remove(user);
		size--;
	}

}
