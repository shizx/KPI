package com.zuyue.action;

import java.util.List;

import javax.annotation.Resource;

import com.zuyue.model.OrgInfo;
import com.zuyue.model.User;
import com.zuyue.pager.Pager;
import com.zuyue.service.OrgService;
import com.zuyue.service.UserService;

public class UserManagerAction extends BaseAction {

	private static final long serialVersionUID = 1925819945060229532L;
	
	

	@Override
	public String forwardAdd() {
		setOrgInfos(orgService.findAll());		
		return super.forwardAdd();
	}

	private String configPassword;
	public String getConfigPassword() {
		return configPassword;
	}

	public void setConfigPassword(String configPassword) {
		this.configPassword = configPassword;
	}

	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String list() {
		
		setPager(userService.findByPager(null));		
		return LIST;
	}
	
	
	
	@Override
	public String add() {
		userService.add(user);
		
		return list();
	}

	private List<OrgInfo> orgInfos;

	public List<OrgInfo> getOrgInfos() {
		return orgInfos;
	}

	public void setOrgInfos(List<OrgInfo> orgInfos) {
		this.orgInfos = orgInfos;
	}
	
private OrgService orgService;	
	
	@Resource
	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}
	
	private UserService userService;	

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private Pager<User> pager;



	public Pager<User> getPager() {
		return pager;
	}

	public void setPager(Pager<User> pager) {
		this.pager = pager;
	}
}
