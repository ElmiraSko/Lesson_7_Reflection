import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class MainClass { // класс, который выполняет тесты
    private static Stack<Method> stack = new Stack<>(); // стек для "методов-тестов" класса TestClass

    public static void main(String[] args) {
        start("TestClass"); // можно так и так
        System.out.println("======================");
        start(TestClass.class);
    }

    private static void start(Object obj) throws RuntimeException{
        Class testClass = null;
        if (obj instanceof String){
            String str = (String)obj;
            try {
                testClass = Class.forName(str);
            }catch (ClassNotFoundException ex){ex.printStackTrace();}
        }else {
            if (obj instanceof Class){testClass = (Class)obj;
            }else throw new RuntimeException("Неверный параметр. Передайте или объект типа Class, или имя класса");
        }

        if (testClass != null){
            Method[] methods = testClass.getDeclaredMethods(); // получаем все "методы-тесты" объявленные в классе TestClass
            List<Method> methodsFromTest = new ArrayList<>();  // создаем список methodsFromTest только для "методов-тестов" с аннотацией @Test
            for (Method m : methods) {
                if (m.isAnnotationPresent(Test.class)) {
                    methodsFromTest.add(m);  // заполняем список "методами" с @Test
                }
            }
            Collections.sort(methodsFromTest, new Comparator<Method>() { // сортируем полученный список
                @Override
                public int compare(Method o1, Method o2) {
                    Test test1 = o1.getAnnotation(Test.class);
                    int priority1 = test1.priority();
                    Test test2 = o2.getAnnotation(Test.class);
                    int priority2 = test2.priority();
                    return (priority1-priority2);
                }
            });
            // Теперь, будем помещать "методы-тесты" в стек. Начнем с метода с аннотацией AfterSuite
            int countAfterSuite = 1; // счетчик
            for (Method method : methods){
                if (method.isAnnotationPresent(AfterSuite.class)){
                    if (countAfterSuite == 1){
                        stack.push(method);
                        countAfterSuite++;
                    }else throw new RuntimeException("Метод AfterSuite должен быть в одном экземпляре!");
                }
            }
            // далее, помещаем в стек методы с аннотацией Test
            for (int i = methodsFromTest.size()-1; i >= 0; i--) {
                stack.push(methodsFromTest.get(i));
            }
            //наконец, помещаем в стек метод с аннотацией BeforeSuite
            int countBeforeSuite = 1;
            for (Method method : methods){
                if (method.isAnnotationPresent(BeforeSuite.class)){
                    if (countBeforeSuite == 1){
                        stack.push(method);
                        countBeforeSuite++;
                    }else throw new RuntimeException("Метод BeforeSuite должен быть в одном экземпляре!");

                }
            }

            System.out.println("Методов в тестовом классе: "+ stack.size());

            while (!stack.isEmpty()){ // пока в стеке есть элемент ("метод-тест")
                try {
                    stack.pop().invoke(null); //  выполняем "метод-тест"
                }catch (InvocationTargetException ex){
                    ex.getStackTrace();
                }
                catch (IllegalAccessException ex){
                    ex.getStackTrace();
                }
            }
        }

    }
}

