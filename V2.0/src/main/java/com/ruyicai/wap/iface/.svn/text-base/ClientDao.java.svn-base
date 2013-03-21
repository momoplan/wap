package com.ruyicai.wap.iface;

import java.util.List;
import com.jrt.common.annotation.db.Sql;
import com.ruyicai.wap.bean.Client;

public interface ClientDao {
	//查询客户端下载信息
	@Sql("select * from client.upgradeconfig where keystr like '%wap'")
	public List<Client> selectClient();

}
