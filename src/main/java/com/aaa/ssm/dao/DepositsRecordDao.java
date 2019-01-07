package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:DepositsRecordDao
 * discription:
 * author:yb
 * createTime:2019-01-04 17:18
 */
public interface DepositsRecordDao {
    /**
     * 通过投资得到记录表
     * @param userid
     * @return
     */
    @Select("select * from tender where userid=#{userid}")
    List<Map> getRecordByDeposits(Integer userid);

    /**
     * 获取分页总数量
     * @param map
     * @return
     */
    @Select("<script>select count(*) cnt from tender where 1=1 and userid=#{userId} " +
            "<if test=\"borrownum!=null and borrownum!=''\">  and borrownum =#{borrownum}</if></script>")
    List<Map> getPageCount(Map map);

    /**
     * 投资列表分页
     * @param map
     * @return
     */
    @Select("<script>select * from (select rownum rn,t.* from tender t where rownum &lt; #{end} and userid=#{userId}" +
            "<if test=\"borrownum!=null and borrownum!=''\">  and borrownum =#{borrownum}</if> ) " +
            "a where a.rn &gt; #{start}</script>")
    List<Map> getTenderPage(Map map);
}
