package com.xian.quartz.config;

import com.xian.quartz.job.HelloJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloQuartzConfig {

    @Bean("helloJob")
    public JobDetail helloJobDetail() {
        return JobBuilder.newJob(HelloJob.class)
                .withIdentity("DateTimeJob")
                .usingJobData("msg","靓仔先写的 Hello Quartz")
                .storeDurably() //即使没有trigger关联，也不需要删除该JobDetail
                .build();
    }


    @Bean
    public Trigger printTimeJobTrigger(){
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/1 * * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(helloJobDetail())
                .withIdentity("quartzTaskService")
                .withSchedule(cronScheduleBuilder)
                .build();
    }

//    public static void main(String[] args) throws SchedulerException {
//
//        /*
//            JobDetail:用来绑定Job，并在Job执行的时候携带一些执行的信息
//         */
//        //创建一个JobDetail实例，将该实例与HelloJob Class绑定
//        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
//                .withIdentity("myJob","group1").build();
//
//        /*
//            Trigger:用来触发Job去执行的，包括定义了什么时候去执行，
//            第一次执行，是否会一直重复地执行下去，执行几次等
//         */
//        //创建一个Trigger实例，定义该Job立即执行，并且每隔 （需求要求的时间） 重复执行一次，直到程序停止
//        /*
//            trigger实例通过builder模式来创建，TriggerBuilder.newTrigger()
//            withIdentity():定义一个标识符，定义了组
//            startNow()：定义现在开始执行
//            withSchedule(SimpleScheduleBuilder.simpleSchedule():withSchedule也是builder模式创建
//            withIntervalInSeconds().repeatForever():定义了执行频率：多少秒执行一次，不间断地重复执行
//            build():创建trigger
//         */
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .withIdentity("myTrigger","group1").startNow()
//                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//                    .withIntervalInSeconds(2).repeatForever()).build();
//
//        //创建scheduler实例
//        /*
//            scheduler区别于trigger和jobDetail,是通过factory模式创建的
//         */
//        //创建一个ScheduleFactory
//        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
//        Scheduler scheduler = schedulerFactory.getScheduler();
//        scheduler.start();
//
//        //打印当前时间
//        Date date = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        log.info("当前的时间为(HelloScheduler):" + simpleDateFormat.format(date));
//
//        //需要将jobDetail和trigger传进去，并将jobDetail和trigger绑定在一起
//        scheduler.scheduleJob(jobDetail,trigger);

}
