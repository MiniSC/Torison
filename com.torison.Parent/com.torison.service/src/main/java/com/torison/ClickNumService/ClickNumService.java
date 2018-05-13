package com.torison.ClickNumService;

import com.torison.ClickNum.ClickNum;
import com.torison.ClickNum.ClickNumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClickNumService {

    @Autowired
    ClickNumMapper clickNumMapper;

    /**
     *点击次数记录
     * @param routeId
     */
    public void updateTime(Integer routeId){
        ClickNum clickNum = clickNumMapper.selectByPrimaryKey(routeId);
        ClickNum clickNumforUpdate = new ClickNum();
        clickNumforUpdate.setRouteid(routeId);
        clickNumforUpdate.setTimes(clickNum.getTimes()+1);
        clickNumMapper.updateByPrimaryKey(clickNumforUpdate);
    }

    /**
     * 获取点击次数
     * @param routeId
     * @return
     */
    public Integer getTimeByRouteId(Integer routeId){
        ClickNum clickNum = clickNumMapper.selectByPrimaryKey(routeId);
        if (clickNum == null ){
            ClickNum newClickNum = new ClickNum();
            newClickNum.setRouteid(routeId);
            newClickNum.setTimes(0);
            clickNumMapper.insert(newClickNum);
        }
        if (clickNum != null && clickNum.getTimes() ==null){
            ClickNum newClickNum = new ClickNum();
            newClickNum.setRouteid(routeId);
            newClickNum.setTimes(0);
            clickNumMapper.updateByPrimaryKey(newClickNum);
        }
        clickNum = clickNumMapper.selectByPrimaryKey(routeId);
        return clickNum.getTimes();
    }

}
