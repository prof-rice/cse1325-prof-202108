interface HelloWorldA {
    public void greetSomeone(String someone);
}

public class HelloWorldAnonymousClass {
    public void sayHello() {
        HelloWorldA englishGreeting = new HelloWorldA() {
            String name = "world";
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Hello " + name);
            }
        };
        englishGreeting.greetSomeone("Professor Rice");
    }
    
    public static void main(String[] args) {
        (new HelloWorldAnonymousClass()).sayHello();
    }
}
