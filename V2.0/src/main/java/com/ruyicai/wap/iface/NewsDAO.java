package com.ruyicai.wap.iface;

import java.util.List;

import com.jrt.common.annotation.db.Sql;
import com.ruyicai.wap.bean.Category;
import com.ruyicai.wap.bean.Channel;
import com.ruyicai.wap.bean.ChargeMode;
import com.ruyicai.wap.bean.Commentary;
import com.ruyicai.wap.bean.News;

public interface NewsDAO {
	@Sql("select n.* from news.news n where n.categoryId=? and n.checked=0 and n.type='wap' order by n.id desc limit ?,?")
	public List<News> getNewsList(String categoryId, int start, int pageSize);

	@Sql("select count(*) from news.News where categoryId=? and checked=0 and type='wap'")
	public int getNewsCount(String categoryId);
	
	@Sql("select n.* from news.News n,news.category_channel c where n.categoryId=c.categoryId and n.checked=0 and n.type='wap' and c.channelId=? and n.categoryId = ?order by n.id desc limit ?,?")
	public List<News> getNewsByChannelId(String channelId, String categoryId, int start, int pageSize);

	@Sql("select count(*) from news.News where categoryId=? and checked=0 and type='wap'")
	public int getCountNewsByChannelId(String channelId);

	@Sql("select * from news.News where id=? and checked=0 and type='wap'")
	public News getNewsContent(String id);

	@Sql("select * from news.Category where type='wap' order by id ")
	public List<Category> getCategory();

	@Sql("select enable,channelId, homeTemplate, nonTemplate, mobileReg, domain,chargeDescribe from statistics.tb_channel_site where enable=1")
	public List<Channel> findAllChannels();

	@Sql("select m.name,m.val from statistics.tb_charge_mode m,statistics.tb_channel_charge c where m.id=c.chargeId and channelId=? order by orderId")
	public List<ChargeMode> getChargeMode(String channelId);
	//更新新闻微博分享次数
	@Sql("update news.News set shareCount = ? where id = ?")
	public int updateShareCount(String shareCount,String id);
	
	//查询新闻微博分享次数
	@Sql("select n.shareCount news.News n where n.id = ?")
	public int selectShareCount(String id);
	
	//添加新闻评论
	@Sql("insert into news.Commentary (mobileid,newsid,content,createtime)values (#mobileid#,#newsid#,#content#,#createtime#)")
	public int insertNewsCommentary(Commentary commentary);
	//审核新闻评论
	@Sql("update news.Commentary set contentstate =? where id =?")
	public int updateNewsCommentaryByContentstate(int contentstate,int id);
	//删除，恢复新闻评论
	@Sql("update news.Commentary set state =? where id =?")
	public int deleteNewsCommentary(int state,int id);
	//查询新闻评论
	@Sql("select * from  news.Commentary where state =? and contentstate=? order by createtime desc limit ?,?")
	public List<Commentary> selectNewsCommentary(int state ,int contentstate,int start ,int line);
	//查询新闻评论数量
	@Sql("select count(*) from  news.Commentary where state =? and contentstate=?")
	public int selectNewsCommentaryCount(int state ,int contentstate);
	//查询每个新闻评论数量
	@Sql("select count(distinct mobileid) from  news.Commentary where newsid=? and state =? and contentstate=?")
	public int selectNewsCommentaryCount(int newsId,int state ,int contentstate);
	//查询每个新闻评论次数
	@Sql("select count(mobileid) from  news.Commentary where newsid=? and state =? and contentstate=?")
	public int selectNewsCommentaryCountBynewsId(int newsId,int state ,int contentstate);
	//查询新闻评论
	@Sql("select * from  news.Commentary where newsid =? and state =? and contentstate=? order by createtime desc limit ?,?")
	public List<Commentary> selectNewsCommentary(int newsId,int state ,int contentstate,int start ,int line);
	//添加绑定手机号获取验证码信息
	@Sql("insert into news.Phoneinfo (userno,username,bindingmobile,messagecode) values (?,?,?,?)")
	public int insertPhoneInfo(String userno,String userName ,String bindingMobile,String messageCode);
	//查询绑定手机号获取验证码信息
	@Sql("SELECT COUNT(id) FROM news.Phoneinfo WHERE username=? AND TO_DAYS(creattime) = TO_DAYS(NOW())")
	public int selectPhoneInfoCount(String userName);


}
