package com.ruyicai.wap.iface;

import java.util.List;
import com.jrt.common.annotation.db.Sql;
import com.ruyicai.wap.bean.WapNews;

public interface WapNewsDao {
	
	@Sql("select * from client.vol_news where state=1 and vol_typeid_fk=? order by id desc limit ?,?")
	public List<WapNews> selectWapNewsList(String type,int startLine,int endLine);
		
	@Sql("select * from client.vol_news where id=?")
	public WapNews selectWapNews(String id);
	
	@Sql("select count(*) from client.vol_news where state=1 and vol_typeid_fk=?")
	public int selectWapNewsCount(String type);
}
