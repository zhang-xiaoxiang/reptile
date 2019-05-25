package com.demo.reptile.test;

import java.io.IOException;

import javax.lang.model.element.Element;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * 测试爬取 http://www.171xue.com/ 官网的标题
 * 这是一个简单示例,可以作为入门,解析复杂的以及反爬虫的稍微复杂一丢丢,会用到正则表达式,以及httpclient哪些,都差不多,也不是很复杂
 * @author 捡矿泉水瓶瓶的张大祥
 *
 */
public class Test {
	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect("http://www.171xue.com/").get();//爬取的网站
		//Document doc = Jsoup.connect("https://blog.csdn.net/wozniakzhang/article/details/89250229").get();//爬取的网站
		String text = doc.text();//HTML格式转文本格式(就是不带标签,只获取内容)
		//F12复制的参考标签   #cd-timeline > div:nth-child(1) > a > div.cd-timeline-content > h2  , 但是jsoup要稍微变动一下
		Elements elements = doc.select("div.cd-timeline-content  h2");// 这是获取到所有div里面的h2标签的元素集合,不同网站这里解析方式不一样而已
		System.out.println(" http://www.171xue.com/ 网站的新闻标题获取如下:\n");
		//由于获取的是集合,需要遍历
		for (int i = 0; i < elements.size(); i++) {
			System.out.println(elements.get(i).text());
		}

	}
}
