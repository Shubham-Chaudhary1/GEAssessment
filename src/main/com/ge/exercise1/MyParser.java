package com.ge.exercise1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MyParser implements Parser {

	private static final String EMPTY_STRING = "";
	private static final String APPLICATION = "Application";
	private static final String USERS = "users";
	private static final String GROUPS = "groups";
	private static final String USER = "User";
	private static final String GROUP = "Group";

	Application application;
	List<UserImpl> listUser;
	List<GroupImpl> listGroup;

	@Override
	public Application parseApplicationData(String data) {

		ArrayList<String> objList = createObjectList(data);
		String appId = "";
		String appName = "";

		int groupStart = 0;

		for (int i = 0; i < objList.size(); i++) {

			if (objList.get(i).equals(APPLICATION)) {
				appId = objList.get(i + 2);
				appName = objList.get(i + 4);
			} else if (objList.get(i).equals(USERS)) {
				listUser = new ArrayList<>();
			} else if (objList.get(i).equals(GROUPS)) {
				groupStart = i;
				listGroup = createGroupObject(groupStart, objList);
				break;
			} else if (objList.get(i).equals(USER) && listUser != null) {
				listUser.add(new UserImpl(objList.get(i + 2), objList.get(i + 4)));
				i = i + 4;
			}
		}

		if (!appId.equals(EMPTY_STRING) && !appName.equals(EMPTY_STRING) && listUser != null && listGroup != null) {
			application = new ApplicationImpl(appId, appName, listUser, listGroup);
		}

		return application;
	}

	

	private List<GroupImpl> createGroupObject(int groupStart,ArrayList<String> objList) {
		List<GroupImpl> groupList=new ArrayList<>();
		for (; groupStart < objList.size(); groupStart++) {
			if (objList.get(groupStart).equals(GROUP)) {
				groupList.add(new GroupImpl(objList.get(groupStart + 2), objList.get(groupStart + 4)));
				groupStart = groupStart + 4;
			} else if (objList.get(groupStart).equals(USER)) {
				UserImpl user = new UserImpl(objList.get(groupStart + 2), objList.get(groupStart + 4));
				groupList.get(groupList.size() - 1).addUser(user);
				groupStart = groupStart + 4;
			}
		}
		return groupList;
	}



	private ArrayList<String> createObjectList(String data) {

		// converting data into char array to remove special characters
		char[] dataArray = data.toCharArray();

		// creating a list of special characters to remove
		ArrayList<Character> specialCharacters = new ArrayList<>(Arrays.asList('[', ']', '(', ')', ',', ':'));

		// list formed after removing special characters
		ArrayList<String> objList = new ArrayList<>();

		// a String to form a word until a character is met
		StringBuilder word = new StringBuilder();

		// flag to continue one char after special character
		boolean isSpecialMet = false;

		for (int i = 0; i < dataArray.length - 1; i++) {

			char currChar = dataArray[i];

			if (Character.isWhitespace(currChar) && isSpecialMet) {
				continue;
			}
			if (specialCharacters.contains(currChar)) {
				isSpecialMet = true;
				if (word.length() > 0) {
					objList.add(word.toString());
				}
				word = new StringBuilder();
			} else {
				isSpecialMet = false;
				word.append(currChar);
			}

		}
		return objList;
	}

}
