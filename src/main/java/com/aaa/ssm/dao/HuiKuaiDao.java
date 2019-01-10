package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public interface HuiKuaiDao {
    /**
     * 查询每个用户每笔回款信息
     * @param map
     * @return
     */
    @Select("<script> select * from (select rownum rn,t.tamount,b.tapr,to_char(add_months(r.starttime,b.timelimit),'yyyy-mm-dd') " +
            "as starttime, " +
            "b.des,b.timelimit,b.danbaostyle from tender t " +
            "left join borrow b on t.borrownum=b.borrownum left join " +
            "repayinfo r on b.borrownum=r.borrownum where t.userid=#{userId} and rownum &lt; #{end} " +
            " <if test=\"selecttoday!=null and selecttoday!=''\">  and trunc(flowdate)=trunc(sysdate) </if> " +
            " <if test=\"selectseven!=null and selectseven!=''\">  and flowdate > sysdate - interval '7' day </if> " +
            " <if test=\"selectmonth!=null and selectmonth!=''\">  and flowdate > sysdate - interval '1' month </if> "  +
            " <if test=\"selectsix!=null and selectsix!=''\">  and flowdate > sysdate - interval '6' month </if>) " +
            "a where a.rn &gt; #{start}</script>")
    List<Map> getHuiKuaiList(Map map);
    /**
            * 获取分页总数量
     * @param map
     * @return
             */
    @Select("<script> select count(*) cnt from tender where 1=1 and userid=#{userId} " +
            " <if test=\"selecttoday!=null and selecttoday!=''\">  and trunc(flowdate)=trunc(sysdate) </if> " +
            " <if test=\"selectseven!=null and selectseven!=''\">  and flowdate > sysdate - interval '7' day </if> " +
            " <if test=\"selectmonth!=null and selectmonth!=''\">  and flowdate > sysdate - interval '1' month </if> " +
            " <if test=\"selectsix!=null and selectsix!=''\">  and flowdate > sysdate - interval '6' month </if></script>")
    List<Map> getPageCount(Map map);
}
