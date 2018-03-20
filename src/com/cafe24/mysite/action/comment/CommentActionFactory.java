package com.cafe24.mysite.action.comment;

import com.cafe24.mvc.action.AbstractActionFactory;
import com.cafe24.mvc.action.Action;

public class CommentActionFactory extends AbstractActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("insert".equals(actionName)) {
			action = new InsertAction();
		} else if("delete".equals(actionName)) {
			action = new DeleteAction();
		}
		return action;
	}

}
