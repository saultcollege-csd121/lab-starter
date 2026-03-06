package _05_generalization_polymorphism.constructors;

public class Example {

    static class A extends Object {
        public A() {
            System.out.println("A's constructor");
        }
    }

    static class B extends A {
        public B(int x) {
            System.out.println("x is " + x);
        }

        public B(String y) {
            System.out.println("y is " + y);
        }
    }

    static class C extends B {
        public C() {
            super("aksjdfsjak");
            System.out.println("C's constructor");
        }

        @Override
        public String toString() {
            return "aksdjksajdf";
        }
    }


    public static void main(String[] args) {
        C c = new C();
    }




}
