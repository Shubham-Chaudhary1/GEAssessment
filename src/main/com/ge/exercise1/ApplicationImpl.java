package com.ge.exercise1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationImpl extends Application {

	// members for creating each object map i.e User and Group
	Map<String, GroupImpl> groupMap;
	Map<String, UserImpl> userMap;

	public ApplicationImpl(String appId, String appName, List<UserImpl> listUser, List<GroupImpl> listGroup) {
		super(appId, appName);
		this.userMap = new HashMap<>();
		this.groupMap = new HashMap<>();
		createUserMap(listUser);
		createGroupMap(listGroup);
	}

// creating a map of group from list of groups
	private void createGroupMap(List<GroupImpl> listGroup) {
		listGroup.forEach(group -> {
			groupMap.put(group.getId(), group);
		});
	}

	// creating a map of group from list of users
	private void createUserMap(List<UserImpl> listUser) {
		listUser.forEach(user -> {
			userMap.put(user.getId(), user);
		});
	}

	@Override
	public ArrayList<User> getUsers() {
		// TODO Auto-generated method stub
		ArrayList<User> listUser = new ArrayList<>();
		for (String id : userMap.keySet()) {
			listUser.add(userMap.get(id));
		}
		return listUser;
	}

	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		return userMap.get(userId);
	}

	@Override
	public ArrayList<Group> getGroups() {
		// TODO Auto-generated method stub
		ArrayList<Group> listGroup = new ArrayList<>();
		for (String id : groupMap.keySet()) {
			listGroup.add(groupMap.get(id));
		}
		return listGroup;
	}

	@Override
	public Group getGroup(String groupId) {
		// TODO Auto-generated method stub
		return groupMap.get(groupId);
	}
}
