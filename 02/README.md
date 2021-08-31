Lecture 02
==========

The files in this folder represent the anticipated
evolution of the file to be developed "live" in this lecture.
But you never know where we may wander in class!

---

To compare 2 files, use either diff (command line)
or meld (GUI, recommended if your VM isn't CPU-starved).
So, to compare A.java to B.java:

* In a narrow terminal

``diff A.java B.java``

* In a wide terminal

``diff -y A.java B.java``

* In a GUI

``meld A.java B.java``

---

To build **all** of these programs, just type ``make``.
To just build [program].java, type ``make [program]``.

So, ``make A`` will compile A.java into a file named A.class. Then, ``java A`` will run it.

---

Type ``make clean`` to delete all of the class files.

