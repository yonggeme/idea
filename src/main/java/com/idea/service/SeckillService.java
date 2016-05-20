package com.idea.service;

import com.idea.dto.Exporer;
import com.idea.dto.SeckillExcution;
import com.idea.entity.Seckill;
import com.idea.entity.SuccessSeckilled;
import com.idea.exception.SeckillCloseException;
import com.idea.exception.SeckillDataReWriteException;
import com.idea.exception.SeckillException;
import com.idea.exception.SeckillRepeatException;

import java.util.Date;
import java.util.List;

/**
 * 接口设计原则：站在’使用者‘角度设计接口
 * 三个方面：方法颗粒度，参数，以及返回值（return type和抛出异常）
 * Created by Administrator on 2016/5/20.
 */
public interface SeckillService {

    /**
     * 查询列表
     * @return
     */
    List<Seckill> queryAll();

    /**
     * 通过id查询
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 暴漏秒杀接口地址
     * @return
     */
    Exporer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀接口
     * @param md5
     * @param userPhone
     * @return
     */
    SeckillExcution executeSeckill(long seckillId, String md5, long userPhone)
            throws SeckillCloseException, SeckillDataReWriteException, SeckillRepeatException, SeckillException;

}
