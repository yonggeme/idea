package com.idea.dto;

import com.idea.entity.SuccessSeckilled;
import com.idea.enums.SeckillState;

/**
 * Created by Administrator on 2016/5/21.
 */
public class SeckillExcution {

    private long seckillId;

    private int state;

    private String stateInfo;

    private SuccessSeckilled successSeckilled;

    public SeckillExcution() {
    }

    public SeckillExcution(long seckillId, SeckillState seckillState) {
        this.seckillId = seckillId;
        this.state = seckillState.getState();
        this.stateInfo = seckillState.getStateInfo();
    }

    public SeckillExcution(long seckillId, SeckillState seckillState, SuccessSeckilled successSeckilled) {
        this.state = state;
        this.seckillId = seckillId;
        this.state = seckillState.getState();
        this.stateInfo = seckillState.getStateInfo();
        this.successSeckilled = successSeckilled;
    }
}
