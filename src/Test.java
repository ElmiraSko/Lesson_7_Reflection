import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)      // выполняется в RUNTIME
@Target(ElementType.METHOD)         // аннотация применима только к методам
public @interface Test {     //  объявление
    int priority() default 1; // метод priority для установления приоритета, по умолчанию == 1
}
