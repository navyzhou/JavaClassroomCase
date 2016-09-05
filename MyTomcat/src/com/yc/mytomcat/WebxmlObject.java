package com.yc.mytomcat;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

/**
 * 每个项目的web.xml的解析对象
 * @author navy
 */
public class WebxmlObject {
	//每个项目对应web.xml对象中的信息
	protected static Map<String,List<ServletMapper>> webObject=new HashMap<String,List<ServletMapper>>();
	//默认页面配置信息
	protected static Map<String,List<String>> welcomeList=new HashMap<String,List<String>>();

	@Test
	public void init(){
		String basePath=Config.getConfig().getProperty("path");

		//扫描这个文件夹下面的所有目录，因为在服务器中每一个目录就是一项目，都需要去加载他们的web.xml文件的
		File fls=new File(basePath);
		//获取当前目录下的所有子目录或文件
		File[] files=fls.listFiles();

		for(File fl:files){ //循环读取每个项目下的配置文件，然后解析
			if( fl.isDirectory() ){ //如果你是一个目录说明你是一个项目
				parseXml(fl);
			}else{
				continue;
			}
		}
	}

	/**
	 * 解析指定项目下的web.xml文件
	 * @param file
	 * @throws DocumentException 
	 */
	@SuppressWarnings("unchecked")
	public void parseXml(File file){
		File webFile=null; //指向每一个web.xml文件
		List<ServletMapper> list=null; //存放每一个wab.xml中的所有servlet信息
		ServletMapper mapper=null; //存放每一个servlet信息
		Map<String,String> servletInfo=null;
		Map<String,String> servletMapping = null;
		List<String> welcomes=null;

		//读取当前项目下的WEB-INF/web.xml文件，但是如果是一个静态网站，则没有这个一项
		webFile=new File(file,"WEB-INF/web.xml");
		if(webFile.exists()){//如果存在这个配置文件，那么可能是一个动态网站
			list=new ArrayList<ServletMapper>(); //准备解析个web.xml中的servlet和welcome-list
			//使用dom4j中的sax解析
			SAXReader reader=new SAXReader();
			//设置命名空间地址
			Map<String,String> map=new HashMap<String,String>();
			map.put("design","http://java.sun.com/xml/ns/javaee"); //值就是命名空间的地址
			reader.getDocumentFactory().setXPathNamespaceURIs(map);
			try {
				Document doc=reader.read(webFile); //注意:都是dom4j中而不是w3c中的
				
				//取servlet的配置信息
				List<Element> nodes=doc.selectNodes("//design:servlet");
				//循环取出每一个servlet中的配置信息
				if(nodes.size()>0){
					servletInfo=new HashMap<String,String>();
					String servletName;
					String servletClass;
					for(Element et:nodes){
						servletName=et.selectSingleNode("design:servlet-name").getText().toString();
						servletClass=et.selectSingleNode("design:servlet-class").getText().toString();
						servletInfo.put(servletName, servletClass);
					}
				
				}
				
				//取servlet-mapping的配置信息
				nodes=doc.selectNodes("//design:servlet-mapping");
				
				//循环取出每一个servlet中的配置信息
				if(nodes.size()>0){
					servletMapping=new HashMap<String,String>();
					String servletName;
					String urlPattern;
					
					for(Element et:nodes){
						servletName=et.selectSingleNode("design:servlet-name").getText().toString();
						urlPattern=et.selectSingleNode("design:url-pattern").getText().toString();
						servletMapping.put(servletName, urlPattern);
					}
				
				}
				
				//取welcome-file-list
				nodes=doc.selectNodes("//design:welcome-file-list/design:welcome-file");
				//循环取出每一个servlet中的配置信息
				if(nodes.size()>0){
					welcomes=new ArrayList<String>();
					for(Element et:nodes){
						welcomes.add( et.getTextTrim() );
					}
				}
				
				//将servlet-mapping与servlet关联起来
				if(servletMapping!=null && servletMapping.size()>0){
					//循环取出每一个servlet-mapping中的servlet-name,到servlet中查找
					Set<String> keys=servletMapping.keySet();
					for(String key:keys){
						if( servletInfo.containsKey(key) ){
							mapper=new ServletMapper();
							mapper.setServletName(key);
							mapper.setServletClass(servletInfo.get(key));
							mapper.setServletUrl(servletMapping.get(key)); 
							//将这个对象存放到list中
							list.add(mapper);
						}else{ //如果在servlet中找不到对应的servlet-name
							throw new RuntimeException(servletMapping.get(key)+" 没有对应的处理类...");
						}
					}
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		//将这个项目中的web.xml对象存起来
		welcomeList.put(file.getName(),welcomes);
		webObject.put(file.getName(), list);
	}
}
