package com.demo.reptile.test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.lang.model.element.Element;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * 测试爬取https://blog.csdn.net/wozniakzhang/article/list/1?
 * 这是一个简单示例,可以作为入门,解析复杂的以及反爬虫的稍微复杂一丢丢,会用到正则表达式,以及httpclient哪些,都差不多,也不是很复杂
 * 
 * @author 捡矿泉水瓶瓶的张大祥
 *
 */
public class CSDN {
//	public static void main(String[] args) throws IOException {
//		Document doc = Jsoup.connect("https://blog.csdn.net/wozniakzhang/article/list/1?").get();// 爬取的网站
//		String text = doc.text();// HTML格式转文本格式(就是不带标签,只获取内容)
//		Elements elements = doc.select("div.grade-box.clearfix > dl:nth-child(2) > dd");// 这是获取到所有div里面的h2标签的元素集合,不同网站这里解析方式不一样而已
//		System.out.println("获取到指定标签的元素:\n" + elements);
//		System.out.println("\nCSDN访问量:      " + elements.attr("title"));// 获取元素属性的值
//		Integer accessNumber = accessNumber();
//		System.out.println(accessNumber);
//	}
	/**
	 * 单纯返回CSDN访问数量的接口
	 * 
	 * @return
	 */
	public static Integer accessNumber() {
		Document doc;
		Integer number = null;
		try {
			doc = Jsoup.connect("https://blog.csdn.net/wozniakzhang/article/list/1?").get();// 爬取的网站
			String text = doc.text();// HTML格式转文本格式(就是不带标签,只获取内容)
			Elements elements = doc.select("div.grade-box.clearfix > dl:nth-child(2) > dd");// 这是获取到所有div里面的h2标签的元素集合,不同网站这里解析方式不一样而已
			Elements elements2 = doc.select("#mainBox > main > div.article-list div");// 这是获取到所有div里面的h2标签的元素集合,不同网站这里解析方式不一样而已
			for (int i = 1; i < elements2.size(); i++) {
				//System.out.println(elements2.get(i).select("h4 > a").attr("href"));// 获取遍历的地址
			}
			String attr = elements.attr("title");
			number = Integer.valueOf(attr);
			// System.out.println("\nCSDN访问量: " + number);// 获取元素属性的值
		} catch (IOException e) {
			e.printStackTrace();
		}
		return number;
	}

	/**
	 * 定时爬取CSDN访问量的线程类
	 * 
	 * @return
	 */
	public static Runnable RunnableDemo() {
		// 单位: 毫秒
		final long timeInterval = 8000;
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				while (true) {
					Integer accessNumber = CSDN.accessNumber();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					//System.out.println(sdf.format(new Date()) + "   CSDN访问量:  " + accessNumber);

					System.out.println("---------------------");
					try {
						Thread.sleep(timeInterval);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		return runnable;
	}

/**
 * 获取CSDN需要爬取的博客链接
 * @param pageNum  博客页码
 * @return
 */
	public static List getUrList(Integer pageNum) {
		Document doc;
		List<String> urList = new ArrayList<String>();
		try {
			doc = Jsoup.connect("https://blog.csdn.net/wozniakzhang/article/list/" + pageNum + "?").get();// 爬取的网站
			String text = doc.text();// HTML格式转文本格式(就是不带标签,只获取内容)
			Elements elements2 = doc.select("#mainBox > main > div.article-list div");// 这是获取到所有div里面的h2标签的元素集合,不同网站这里解析方式不一样而已
			System.out.println("第"+pageNum+"页博客数量:" + (((elements2.size()-1)/2)-1));
			for (int i = 2; i < elements2.size()-1; i+=2) {
				//System.out.println("最初地址:  "+i+"  "+elements2.get(i).select("h4 > a").attr("href"));
				urList.add(elements2.get(i).select("h4 > a").attr("href"));// 获取遍历的地址
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return urList;//返回数量
	}

	public static void main(String[] args) {

//		Runnable runnable = RunnableDemo();
//		Thread thread = new Thread(runnable);
//		thread.start();
		List urList = CSDN.getUrList(1);
		System.out.println("main  "+urList.size());

	}

}
