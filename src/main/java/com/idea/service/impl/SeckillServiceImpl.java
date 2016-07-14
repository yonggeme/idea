package com.idea.service.impl;

import com.idea.dao.SeckillDao;
import com.idea.dao.SuccessSeckilledDao;
import com.idea.dto.Exposer;
import com.idea.dto.Execution;
import com.idea.entity.Seckill;
import com.idea.entity.SuccessSeckilled;
import com.idea.enums.SeckillState;
import com.idea.exception.CloseException;
import com.idea.exception.DataReWriteException;
import com.idea.exception.SeckillException;
import com.idea.exception.RepeatException;
import com.idea.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.util.DigestUtils.md5DigestAsHex;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;


/**
 * Created by Administrator on 2016/5/21.
 */
@Service
public class SeckillServiceImpl implements SeckillService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String confusion = "gdajls/gdsaj;lgajsdlk2039t9uaskldgjalksdmgals)pgajslk7";


    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessSeckilledDao successSeckilledDao;

    public List<Seckill> queryAll() {
        int offset = 0;
        int limit = 15;
        List<Seckill> seckills = seckillDao.queryAll(offset, limit);
        return seckills;
    }

    public Seckill queryById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if (seckill == null){
            return new Exposer(false, seckillId);
        }
        long startTime = seckill.getStartTime().getTime();
        long endTime = seckill.getEndTime().getTime();
        long now = new Date().getTime();
        if (now < startTime || now > endTime){
            return new Exposer(false, startTime, endTime, now);
        }
        String md5 = getMd5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    @Transactional
    public Execution executeSeckill(long seckillId, String md5, long userPhone)
            throws CloseException, DataReWriteException, RepeatException, SeckillException {
        try {
            if (md5 == null || !getMd5(seckillId).equals(md5)) {
                throw new DataReWriteException("seckill data rewrite!");
            }
            int updateCount = seckillDao.reduceNumber(seckillId, new Date());
            if (updateCount == 0) {
                throw new CloseException("seckill closed!");
            }
            int insertCount = successSeckilledDao.insertSuccessSeckilled(seckillId, userPhone);
            if (insertCount == 0) {
                throw new RepeatException("repeat seckill!");
            }

            SuccessSeckilled successSeckilled = successSeckilledDao.queryBySeckillId(seckillId, userPhone);

            return new Execution(seckillId, SeckillState.SUCCESS, successSeckilled);
        }catch (RepeatException e){
            throw e;
        }catch (DataReWriteException e){
            throw e;
        }catch (CloseException e){
            throw e;
        }catch (Exception e){
            logger.error("SeckillException = {}", e.getMessage());
            throw new SeckillException("inner error!");
        }
    }


    public String getMd5(long seckillId){
        String str = seckillId + confusion;
        try {
            return md5DigestAsHex(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            logger.error("get md5 error!, e = {}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
