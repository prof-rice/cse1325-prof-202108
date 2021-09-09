Important
=========

Class Chicken will NOT compile, because it attempts to override a final method.

The point of a final method is that it cannot be overridden by any subclass.

The error given by javac 14.0.2 is

```
./Chicken.java:6: error: count() in Chicken cannot override count() in Critter
    public final void count() {if (timer++ > frequency) timer = 0;}
                      ^
  overridden method is final
1 error

```

