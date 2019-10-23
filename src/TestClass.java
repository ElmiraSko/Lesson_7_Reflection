public class TestClass {
//методы-тесты:

//    @BeforeSuite
//    public static void method(){
//        System.out.println("У метода method аннотация @BeforeSuite");
//    }

    @BeforeSuite
    public static void method1(){
        System.out.println("У метода method1 аннотация @BeforeSuite");
    }
    @Test(priority = 4)
    public static void method2(){
        System.out.println("У метода method2 аннотация @Test(priority = 4)");
    }
    @Test(priority = 8)
    public static void method3(){
        System.out.println("У метода method3 аннотация @Test(priority = 8)");
    }
    @Test
    public static void method4(){
        System.out.println("У метода method4 аннотация @Test, (default 1)");
    }
    @Test(priority = 3)
    public static void method5(){
        System.out.println("У метода method5 аннотация @Test(priority = 3)");
    }
    @Test(priority = 5)
    public static void method6(){
        System.out.println("У метода method6 аннотация @Test(priority = 5)");
    }
    @Test
    public static void method7(){
        System.out.println("У метода method7 аннотация @Test, (default 1)");
    }
    @AfterSuite
    public static void method8(){
        System.out.println("У метода method8 аннотация @AfterSuite");
    }
    @Test(priority = 10)
    public static void method9(){
        System.out.println("У метода method9 аннотация @Test(priority = 10)");
    }
    @Test(priority = 5)
    public static void method10(){
        System.out.println("У метода method10 аннотация @Test(priority = 5)");
    }

}
