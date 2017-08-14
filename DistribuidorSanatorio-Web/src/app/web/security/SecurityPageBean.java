package app.web.security;

import app.schema.enumerated.UserStates;
import app.security.User;
import app.web.base.SecurityBeanBase;

public class SecurityPageBean extends SecurityBeanBase<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6193822077008977126L;

	public SecurityPageBean() {
		entity = new User();
	}

	@Override
	protected void beforeFind() {
		setWhereQL(" where upper(e.userName) = :userName");
		setParam("userName", entity.getUserName().toUpperCase());
	}

	@Override
	protected boolean eligibilities() throws Exception {
		if (resultEntity != null) {
			if (resultEntity.getState() != UserStates.Active) {
				warnMsg(getMessages().getString("message.login.error"));
				return false;
			}
//			if (!resultEntity.getPassWord().equals(
//					Utils.getEncriptyonMessage(entity.getPassWord(),
//							EncryptionType.MD5))) {
//				warnMsg(getMessages(.getString("message.login.error"));
//				return false;
//			}

//			if (resultEntity.getPerfilID() == null
//					|| !resultEntity.getPerfilID().isEstado()) {
//				errorMsg(getMsg().getString("message.login.profile.error"));
//				return false;
//			}
		} else {
			warnMsg(getMessages().getString("message.login.user.error"));
			return false;
		}
		return super.eligibilities();
	}
}