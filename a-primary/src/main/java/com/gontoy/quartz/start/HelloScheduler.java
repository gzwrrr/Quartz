package com.gontoy.quartz.start;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 调度器
 * @author gzw
 */
public class HelloScheduler {
    public static void main(String[] args) throws SchedulerException {
        // 调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // 任务实例；withIdentity 的第一个参数为任务的名称，第二个参数为任务组的名称
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("Job-1", "Group-1")
                .build();

        // 触发器；withIdentity 的第一个参数为触发器的名称，第二个参数为触发器组的名称
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("Trigger-1", "Group-1")
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5))
                .build();

        // 让调度器关联任务和触发器
        scheduler.scheduleJob(jobDetail, trigger);

        // 启动调度器
        scheduler.start();
    }
}
