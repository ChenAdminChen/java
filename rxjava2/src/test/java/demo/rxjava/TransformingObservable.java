package demo.rxjava;

import demo.module.Student;
import io.reactivex.Observable;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TransformingObservable {

    /**
     * range 创建一个从 start -> end Observable
     * <p>
     * buffer() 指定一个Integer 到消息达到该数量后才一起发出
     */
    @Test
    public void testBuffer() {
        Observable.range(0, 5)
                .buffer(2)
                .subscribe(r -> {
                    System.out.println(r);
                    System.out.println("====");
                });
    }

    @Test
    public void testGroupBy() {
        Student student1 = new Student("chen1", 21, 1);
        Student student2 = new Student("chen2", 20, 1);
        Student student3 = new Student("chen3", 21, 0);
        Student student4 = new Student("chen4", 20, 0);
        Student student5 = new Student("chen5", 20, 3);
        Student student6 = new Student("chen6", 21, 0);

        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);
        list.add(student5);
        list.add(student6);

        Observable.fromIterable(list)
                .groupBy(r -> r.getSex())
                .flatMap(g -> g.toList().toObservable())
                .subscribe(r -> {
                    for (Student student : r) {
                        System.out.println(student.toString());
                    }
                });
//                .flatMap(Observable::toList)
//                .subscribe(r -> {
//                    System.out.println(r);
//                });

    }

    /**
     * 1,2,3,4,5 -> 1,3(3 = 1+ 2),6(6 = 3 + 3),10(10 = 6+4) 15(15=10+5)
     */
    @Test
    public void testScan() {
        Observable.range(1, 5)
                .scan((s, k) -> s + k)
                .subscribe(r -> {
                    System.out.println(r);
                });
        System.out.println("scan1");

        //提供一个初始值
        Observable.range(1, 5)
                .scan(3, (sum, item) -> {
                    return sum + item;
                })
                .subscribe(r -> {
                    System.out.println(r);
                });
    }


    /**
     * window与buffer 相同，但是window可 open or close but it close to onComplete()
     */
    @Test
    public void testWindow() {
        Observable.range(1, 10)
                .window(3)
                .flatMap(r -> r.toList().toObservable())
                .subscribe(r -> {
                            System.out.println(r);
                        },
                        e -> {
                        },
                        () -> {

                        }
                );
    }
}
