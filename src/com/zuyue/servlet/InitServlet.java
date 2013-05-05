package com.zuyue.servlet;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.zuyue.core.ConfigMgr;
import com.zuyue.core.ZuyueConfig;
import com.zuyue.formula.SymbolModel;
import com.zuyue.system.Environment;

/**
 * 
 * @author Z
 *
 */
public class InitServlet extends HttpServlet {
      
	private static final long serialVersionUID = 5007576052839293705L;

	/**
     * 设置Common-loggings 的属性
    
    static {
        Properties property = new Properties();
        String logPath = System.getProperty("java.util.logging.config.file");
        try {
            property.load(new FileInputStream(logPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Enumeration<Object> keys = property.keys();
        while (keys.hasMoreElements()) {
            String s = (String) keys.nextElement();
            if (s.startsWith("org.apache.commons.logging")) {
                System.setProperty(s, property.getProperty(s));
            }
        }
    }
 */
    /**
     * 容器启动时加载处理
     *
     * @throws javax.servlet.ServletException
     */
    @SuppressWarnings("unchecked")
	public void init() throws ServletException {
        try {
            //Thread.sleep(5000);
            /** 初始化所有配置性实例 */
            SAXReader reader = new SAXReader();
            String applicationsDir = getServletContext().getRealPath("/");
            String classPathName = InitServlet.class.getResource("/").getPath(); 
            
            File xoffice = new File(classPathName + File.separator + "zuyue.xml");
            Document configDocument = reader.read(xoffice);
            
            List<Node> symbolNodes = configDocument.selectNodes("/zuyue/symbols/symbol");
            for (Node node : symbolNodes) {
            	String symbol = node.getText();
            	
            	SymbolModel sm = new SymbolModel();
            	sm.setSymbol(symbol);
            	ZuyueConfig.addSymbol(sm);
            }

//            String appRoot = System.getProperty("app.root");
//            if (StringUtils.isBlank(appRoot)) {
//                throw new RuntimeException("请先设置 app.root 目录位置");
//            }

            ConfigMgr.setAppRoot(applicationsDir);

            //设置系统启动时间
            Environment.setApplicationStartTime(new Date());            
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException("启动失败", e);
        } 
    }
}
