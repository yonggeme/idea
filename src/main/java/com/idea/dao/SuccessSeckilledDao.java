package com.idea.dao;

import com.idea.entity.SuccessSeckilled;

import java.util.List;

/**
 * SuccessSeckilled借口
 * Created by Administrator on 2016/5/19.
 */
public interface SuccessSeckilledDao {

    /**
     * 新增秒杀成功明细
     * @param seckillId
     * @param userPhone
     * @return
     */
    int insertSuccessSeckilled(long seckillId, long userPhone);

    /**
     * 通过seckillId查询秒杀成功明细列表
     * @param seckillId
     * @return
     */
    List<SuccessSeckilled> queryBySeckillId(long seckillId);

}
