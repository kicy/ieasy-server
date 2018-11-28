package com.baayso.springboot.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baayso.springboot.common.service.AbstractBaseService;
import com.baayso.springboot.demo.dao.DemoUserDAO;
import com.baayso.springboot.demo.domain.DemoUserDO;
import com.baayso.springboot.demo.domain.enums.OrderStatus;


/**
 * 测试业务。
 *
 * @author ChenFangjie (2016/4/1 16:27)
 * @since 1.0.0
 */
@Service
public class DemoUserService extends AbstractBaseService<DemoUserDAO, DemoUserDO> {

    @Transactional
    public boolean saveUser() {
        DemoUserDO user1 = new DemoUserDO();
        user1.setName("code-1-" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        user1.setIntro("");
        user1.setAge(18);
        user1.initBeforeAdd();

        super.save(user1);

        DemoUserDO user2 = new DemoUserDO();
        user2.setName("code-2-" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        user2.setIntro(null);
        user2.setAge(18);
        user2.setStatus(OrderStatus.SUCCESS);
        user2.initBeforeAdd();

        super.save(user2);

        int min = 0;
        int max = 9;
        int random = (int) (Math.random() * (max - min + 1)) + min;

        int i = 1 / (random >= 5 ? 1 : 0);

        return true;
    }

    public boolean saveUsers() {
        DemoUserDO user3 = new DemoUserDO();
        user3.setName("code-555-" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        user3.setIntro("");
        user3.setAge(19);
        user3.initBeforeAdd();

        DemoUserDO user4 = new DemoUserDO();
        user4.setName("code-666-" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        user4.setIntro(null);
        user4.setAge(19);
        user4.setStatus(OrderStatus.CLOSE);
        user4.initBeforeAdd();

        List<DemoUserDO> list = new ArrayList<>();
        list.add(user3);
        list.add(user4);

        super.saveBatch(list);

        return true;
    }

    @Async
    public Future<Boolean> asyncSaveUsers() throws InterruptedException {

        TimeUnit.SECONDS.sleep(3L);

        return AsyncResult.forValue(this.saveUser());
    }

    public boolean deletes() {
        List<Long> ids = new ArrayList<>();
        ids.add(7L);
        ids.add(8L);

        return super.removeByIds(ids);
    }

    public boolean update() {
        DemoUserDO user5 = DemoUserDO.builder() //
                .id(7L) //
                .version(2) //
                .intro("测试乐观锁") //
                .status(OrderStatus.RETURN_SUCCESS) //
                .modifyBy("测试乐观锁") //
                .modifyTime(LocalDateTime.now()) //
                .build();

        return super.updateById(user5);
    }

}
