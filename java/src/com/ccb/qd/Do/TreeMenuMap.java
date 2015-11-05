package com.ccb.qd.Do;

import com.ccb.qd.common.WebStepUtils;
import com.ccb.qd.webcrawler.GongShangWebSearch;
import com.ccb.qd.webcrawler.HongdunWebSearch;
import com.ccb.qd.webcrawler.IWebSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by han on 2015/7/9.
 */
@Component("treeMenuMap")
public class TreeMenuMap extends  HashMap<String,IWebSearch>
{
    @Autowired
    public void setGongShangWebSearch(GongShangWebSearch gongShangWebSearch) {
        this.gongShangWebSearch = gongShangWebSearch;
    }
    private GongShangWebSearch gongShangWebSearch;
    @Autowired
    private HongdunWebSearch  hongdunWebSearch;
    public TreeMenuMap() {

    }
    public void init(){
       {
           put("山东工商查询", gongShangWebSearch);
           put("红盾查询", hongdunWebSearch);
      //新增菜单项列表

            WebStepUtils.getAnnotation(IWebSearch.class, "WebStep");
        }
    }


}
