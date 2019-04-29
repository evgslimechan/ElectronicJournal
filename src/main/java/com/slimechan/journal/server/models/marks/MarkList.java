package com.slimechan.journal.server.models.marks;

import java.util.List;
import java.util.Map;
import com.slimechan.journal.server.models.users.User;

public class MarkList{

	private Map<User, Mark> marks;

	public MarkList(List<User> students) {
		for(User u:students) marks.put(u, null);
	}
	
	
	
	/**
	 * Add a mark
	 * @param u user in list {@link User}
	 * @param mark the current mark {@link Mark}
	 * 
	 */
	public void setMark(User u, Mark mark) {
		if(marks.containsKey(u)) {
			marks.replace(u, mark);
		}else {
			marks.put(u, mark);
		}
	}
	/**
	 * Add a mark
	 * @param journalId Id in journal (group)
	 * @param mark the current mark {@link Mark}
	 * 
	 */
	public void setMark(int journalId, Mark mark) {
		setMark(getUserById(journalId), mark);
	}

	/**
	 * Return a mark
	 * @param journalId Id in journal(group)
	 * @return the current mark(if no mark returns null) {@link Mark}
	 * 
	 */
	public Mark getUserMark(int journalId) {
		if(getUserById(journalId)!=null) {
			return marks.get(getUserById(journalId));
		}
		return null;
	}
	private User getUserById(int journalId) {
		return (User) marks.keySet().toArray()[journalId+1];
	}
}
