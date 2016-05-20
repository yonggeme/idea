package com.idea.service.impl;

import com.idea.dao.SeckillDao;
import com.idea.dao.SuccessSeckilledDao;
import com.idea.dto.Exporer;
import com.idea.dto.SeckillExcution;
import com.idea.entity.Seckill;
import com.idea.entity.SuccessSeckilled;
import com.idea.enums.SeckillState;
import com.idea.exception.SeckillCloseException;
import com.idea.exception.SeckillDataReWriteException;
import com.idea.exception.SeckillException;
import com.idea.exception.SeckillRepeatException;
import com.idea.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/5/21.
 */
public class SeckillServiceImpl implements SeckillService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String confusion = "gdajls/gdsaj;lgajsdlk2039t9uaskldgjalksdmgals)pgajslk7";


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

    public Exporer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if (seckill == null){
            return new Exporer(false, seckillId);
        }
        long startTime = seckill.getStartTime().getTime();
        long endTime = seckill.getEndTime().getTime();
        long now = new Date().getTime();
        if (now < startTime || now > endTime){
            return new Exporer(false, startTime, endTime, now);
        }
        String md5 = getMd5(seckillId);
        return new Exporer(true,md5);
    }

    public SeckillExcution executeSeckill(long seckillId, String md5, long userPhone)
            throws SeckillCloseException, SeckillDataReWriteException, SeckillRepeatException, SeckillException {
        try {
            if (md5 == null || getMd5(seckillId) != md5) {
                throw new SeckillDataReWriteException("seckill data rewrite!");
            }
            int updateCount = seckillDao.reduceNumber(seckillId, new Date());
            if (updateCount == 0) {
                throw new SeckillCloseException("seckill closed!");
            }
            int insertCount = successSeckilledDao.insertSuccessSeckilled(seckillId, userPhone);
            if (insertCount == 0) {
                throw new SeckillDataReWriteException("repeat seckill!");
            }

            SuccessSeckilled successSeckilled = successSeckilledDao.queryBySeckillId(seckillId, userPhone);

            return new SeckillExcution(seckillId, SeckillState.SUCCESS, successSeckilled);
        }catch (SeckillRepeatException e){
            throw e;
        }catch (SeckillDataReWriteException e){
            throw e;
        }catch (SeckillCloseException e){
            throw e;
        }catch (Exception e){
            logger.error("SeckillException = {}", e.getMessage());
            throw new SeckillException("inner error!");
        }
    }


    public String getMd5(long seckillId){
        String str = seckillId + confusion;
        String md5 = DigestUtils.md5DigestAsHex(str.getBytes());
        return md5;
    }
}
