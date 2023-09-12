package ren.shuaipeng.flink.samples;

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
                new Person("三十岁以后", 35),
                new Person("小任", 18),
                new Person("小妞", 2)
        );

        // 3. 定义 流计算
        DataStream<Person> filterAdults =
                streamData.filter((FilterFunction<Person>) person -> person.age > 18);

        // 4. 打印 流信息
        filterAdults.print();

        // 5. 开启流计算
        env.execute("Stream Calc Adults");
    }

    private static class Person {
        public String name;
        public Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}