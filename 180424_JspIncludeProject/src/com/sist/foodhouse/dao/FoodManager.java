package com.sist.foodhouse.dao;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class FoodManager {
    public static void main(String[] arg)
    {
    	FoodManager m=new FoodManager();
    	ArrayList<CategoryVO> list=m.foodCategoryData();
    	
    	FoodDAO dao=new FoodDAO();
    	for(CategoryVO vo:list)
    	{
    		dao.foodCategoryInsert(vo);
    	}
    	System.out.println("end...");
    }
    /*
     * <ul class="list-toplist-slider" style="width: 531px;">
                    <li>
                      <img class="center-croping"
                           alt="샤로수길 맛집 베스트 20곳 사진"
                           data-lazy="https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/2y-uh5itrplf4ifq.jpg?fit=around|600:400&amp;crop=600:400;*,*&amp;output-format=jpg&amp;output-quality=80" />


                      <a href="/top_lists/1280_sarosugil"
                         onclick="common_ga('PG_MAIN','CLICK_LIST');">
                        <figure class="ls-item">
                          <figcaption class="info">
                            <div class="info_inner_wrap">
                              <span class="title">샤로수길 맛집 베스트 20곳</span>

                              <p class="desc">"먹고 싶은 것은 다 있는 샤로수길!"</p>

                              <p class="hash">
                                    <span>#샤로수길 </span>
                                    <span>#서울대입구역 </span>
                                    <span>#서울대입구 </span>
                                    <span>#서울대 </span>
                              </p>
                            </div>
                          </figcaption>
                        </figure>
                      </a>
     */
    public ArrayList<CategoryVO> foodCategoryData()
    {
    	ArrayList<CategoryVO> list=
    			   new ArrayList<CategoryVO>();
    	try
    	{
    		Document doc=Jsoup.connect("https://www.mangoplate.com/").get();
    		Elements title=doc.select("figcaption.info span.title");
    		Elements subject=doc.select("figcaption.info p.desc");
    		//data-lazy
    		Elements poster=doc.select("img.center-croping");
    		
    		for(int i=0;i<9;i++)
    		{
    			System.out.println("==========================");
    			System.out.println(title.get(i).text());
    			System.out.println(subject.get(i).text());
    			System.out.println(poster.get(i).attr("data-lazy"));
    			CategoryVO vo=new CategoryVO();
    			vo.setTitle(title.get(i).text());
    			vo.setSubject(subject.get(i).text());
    			vo.setPoster(poster.get(i).attr("data-lazy"));
    			list.add(vo);
    		}
    		
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    	return list;
    }
}









