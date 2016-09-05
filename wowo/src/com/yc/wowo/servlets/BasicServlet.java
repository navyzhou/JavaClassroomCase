package com.yc.wowo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BasicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		//request.setCharacterEncoding("utf-8");
//		//response.setCharacterEncoding("utf-8");
//	}

	/**
	 * 返回一个状态值
	 * @param response
	 * @param result
	 */
	protected void out(HttpServletResponse response,int result){
		PrintWriter out=null;
		try {
			out=response.getWriter();
			out.print(result);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			out.close();
		}
	}
	
	/**
	 * 返回一个字符串
	 * @param response
	 * @param result
	 */
	protected void out(HttpServletResponse response,String result){
		PrintWriter out=null;
		try {
			out=response.getWriter();
			out.print(result);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			out.close();
		}
	}
	
	/**
	 * 返回单个对象
	 * @param response
	 * @param obj
	 */
	protected <T> void out(HttpServletResponse response,T obj){
		PrintWriter out=null;
		try {
			out=response.getWriter();
			out.print(JSONObject.fromObject(obj));
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			out.close();
		}
	}
	
	/**
	 * 返回一个集合的
	 * @param response
	 * @param list
	 */
	protected <T> void out(HttpServletResponse response,List<T> list){
		PrintWriter out=null;
		JSONArray json=JSONArray.fromObject(list);
		try {
			out=response.getWriter();
			out.print(json);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			out.close();
		}
	}
	
	/**
	 * 同时返回多个对象
	 * @param response
	 * @param map
	 */
	protected <T> void out(HttpServletResponse response,Map<String,List<T>> map){
		PrintWriter out=null;
		JSONArray json;
		JSONObject jb=new JSONObject();
		try {
			if(map!=null && map.size()>0){
				Set<String> keys=map.keySet();
				for(String key:keys){
					json=JSONArray.fromObject(map.get(key));
					jb.put(key,json);
				}
			}
			out=response.getWriter();
			out.print(jb.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			out.close();
		}
	}
	
	/**
	 * EasyUI中的分页json
	 * @param response
	 * @param map
	 */
	protected <T> void out(HttpServletResponse response,List<T> list,int total){
		PrintWriter out=null;
		JSONArray json=JSONArray.fromObject(list);
		JSONObject jb=new JSONObject();
		try {
			out=response.getWriter();
			jb.put("total",total);
			jb.put("rows",json);
			out.print(jb);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			out.close();
		}
	}
}
