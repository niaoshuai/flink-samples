package ren.shuaipeng.flink.samples;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class FlinkJob {
    public static void main(String[] args) throws Exception {
        // 1. 获取 【流】 执行环境
        final StreamExecutionEnvironment env =
                StreamExecutionEnvironment.getExecutionEnvironment();

        // 2. 定义数据流
        DataStream<Person> streamData = env.fromElements(
                new Person()
                        .setAge(35)
                        .setName("三十岁以后"),
                new Person()
                        .setAge(18)
                        .setName("小任"),
                new Person()
                        .setAge(2)
                        .setName("小妞")
        );

        // 3. 定义 流计算
        DataStream<Person> filterAdults =
                streamData.filter((FilterFunction<Person>) person -> person.age > 18);

        // 4. 打印 计算结果
        filterAdults.print();

        // 5. 开启流计算
        env.execute("Stream Calc Adults");
    }

    @Data
    @Accessors(chain = true)
    private static class Person {
        public String name;
        public Integer age;
    }
}