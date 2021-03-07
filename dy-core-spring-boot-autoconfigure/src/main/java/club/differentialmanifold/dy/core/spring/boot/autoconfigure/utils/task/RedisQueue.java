package club.differentialmanifold.dy.core.spring.boot.autoconfigure.utils.task;


import java.io.Serializable;

public interface RedisQueue<T extends Serializable> {
    T poll();
    void push(T log);
}
