
package system.user;

import system.exception.UserException;
import system.util.SystemUtil;

public class UserPlan {

	public static enum planType{
		trial , standard, vip
	}

	 boolean isActive;
	 planType type;
	 
	 public UserPlan() {}

	public UserPlan(planType type,boolean isActive) {
		this.isActive = isActive;
		this.type = type;
	}
	public UserPlan createPlan(String ...strings) throws UserException {
		//Check String validity
		for(String s : strings) {
			if(!SystemUtil.isValid(s)) {
				throw new UserException("Invalid strings at Create plan. Expected 2 args");
			}
		}
		switch (strings[0]) {
		case "1":
			type = planType.trial;
			break;
		case "2":
			type = planType.standard;
			break;
		case "3":
			type = planType.vip;
			break;
		default:
			System.out.println("Unknown subscription type");
		}
		switch (strings[1]) {
		case "1":
			isActive=true;
			
			break;
		case "2":
			isActive=false;
			break;
		default :
			System.out.println("Unknown subscription type");
		}
		return new UserPlan(type,isActive);
	}
	public boolean isActive() {
		return isActive;
	}

	public planType getType() {
		return type;
	}
	public boolean isVip() {
		if (type != planType.vip) {
			return false;
		}return true;
	}

	@Override
	public String toString() {
		return " Plan: isActive= " + isActive + ", type= " + type ;
	}

}
