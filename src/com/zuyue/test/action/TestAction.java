package com.zuyue.test.action;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zuyue.test.model.TestModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Component
@Scope(value="prototype")
public class TestAction extends ActionSupport implements ModelDriven<TestModel>{
	
	private static final long serialVersionUID = 1L;
	Map request;
	Map session;
	Map application;
	
	public void out() {
		request = (Map)ActionContext.getContext().get("request");
		session = (Map)ActionContext.getContext().getSession();
		application = (Map)ActionContext.getContext().getApplication();
		
		System.out.println("request-->"+request);
		System.out.println("session-->"+session);
		System.out.println("application-->"+application);
		
		request.put("request1", "request1");
		session.put("session2", "session2");
		application.put("application3", "application3");
	}
	
	private TestModel testModel = new TestModel();
	public TestModel getTestModel() {
		return testModel;
	}

	public void setTestModel(TestModel testModel) {
		this.testModel = testModel;
	}

	public String execute() {
		return SUCCESS;
	}
	
	public String register() {
		out();
		
		boolean success = true;
		if (testModel.getUserName() == null || "".equals(testModel.getUserName())) {
			this.addFieldError("userName", "用户名不能为空!!!");
			success = false;
		}
		if (testModel.getPassword() == null || "".equals(testModel.getPassword())) {
			this.addFieldError("password", "密码不能为空!!!");
			success = false;
		}
		if ("N".equals(testModel.getUserName())) {
			return "notfound";
		}
		if (!success) {
			return ERROR;
		}
		System.out.println(testModel.getUserName());
		System.out.println(testModel.getPassword());
		
		return SUCCESS;
	}

	public TestModel getModel() {
		return testModel;
	}
}
