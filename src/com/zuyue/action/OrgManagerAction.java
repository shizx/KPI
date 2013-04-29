package com.zuyue.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;

import com.zuyue.model.OrgInfo;
import com.zuyue.model.User;
import com.zuyue.pager.Pager;
import com.zuyue.service.OrgService;

public class OrgManagerAction extends BaseAction {
	
	private OrgService orgService;

	public OrgService getOrgService() {
		return orgService;
	}

	@Resource
	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}

	private static final long serialVersionUID = 1640426665123382335L;

	public void setPager(Pager<OrgInfo> pager) {
		pager.toHTML("");  //把HTML信息提取出来
		this.pager = pager;
		
	}

	@Override
	public String list() {
		setPager(orgService.findByPager(null));
		return LIST;
	}
	
	@Override
	public String forwardAdd() {
		setOrgInfos(orgService.findAll());	
		return ADD;
	}
	
	@Override
	public String add() {		
		if (companyImage != null) {
			try {
				FileUtils.copyFile(companyImage, new File("c:\\xxxx.txt"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		orgService.add(orgInfo);
		System.out.println("--" + orgInfo.getOrgName());
		return list();
	}
	
	private OrgInfo orgInfo;
	public OrgInfo getOrgInfo() {
		return orgInfo;
	}

	public void setOrgInfo(OrgInfo orgInfo) {
		this.orgInfo = orgInfo;
	}
	
	private User user;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private File companyImage;
	public File getCompanyImage() {
		return companyImage;
	}

	public void setCompanyImage(File companyImage) {
		this.companyImage = companyImage;
	}
	
	private Pager<OrgInfo> pager;

	public Pager<OrgInfo> getPager() {
		return pager;
	}
	
	private List<OrgInfo> orgInfos;

	public List<OrgInfo> getOrgInfos() {
		return orgInfos;
	}

	public void setOrgInfos(List<OrgInfo> orgInfos) {
		this.orgInfos = orgInfos;
	}
}
