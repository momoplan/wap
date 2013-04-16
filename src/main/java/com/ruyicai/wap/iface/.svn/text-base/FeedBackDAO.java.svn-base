package com.ruyicai.wap.iface;

import java.util.List;

import com.jrt.common.annotation.db.Sql;
import com.ruyicai.wap.bean.FeedBack;

public interface FeedBackDAO {
	//插入
	@Sql("insert into news.FeedBack(content,mobile,createTime) values (#content#,#mobile#,#createTime#)")
	public int insertFeedBack (FeedBack feedBack);
	//查询
	@Sql("select * from news.FeedBack order by createTime desc limit ?,?")
	public List<FeedBack> selectFeedBack(int startPage,int lineNumber);
	//查询(用户)
	@Sql("select * from news.FeedBack where answer<>'' order by createTime desc limit ?,?")
	public List<FeedBack> selectUserFeedBack(int startPage,int lineNumber);
	//总数
	@Sql("select count(*) from news.FeedBack")
	public int selectFeedBackCount();
	//总数(用户)
	@Sql("select count(*) from news.FeedBack where answer<>''")
	public int selectUserFeedBackCount();
	//删除
	@Sql("delete * from news.FeedBack where id = ?")
	public int deleteFeedBack(String id);
	//修改留言回复
	@Sql("update news.FeedBack set answer = ? where id = ?")
	public int updateAnswerFeedBack(String answer,int id);
	//查询留言信息
	@Sql("select * from news.FeedBack where id = ?")
	public FeedBack selectFeedBackById(int id);
}
