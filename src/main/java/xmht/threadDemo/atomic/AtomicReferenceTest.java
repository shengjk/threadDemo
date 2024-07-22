package xmht.threadDemo.atomic;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author shengjk1
 * @date 7/8/2024
 */
public class AtomicReferenceTest {
    public static AtomicReference<User> atomicReference= new AtomicReference<User>();

    public static void main(String[] args) {
        User user = new User("conan", 15);
        atomicReference.set(user);
        System.out.println("user = " + user.old);
        AtomicReferenceFieldUpdater<User, String> userStringAtomicReferenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(
                User.class,String.class, "name"  //通过反射实现的
        );
        userStringAtomicReferenceFieldUpdater.compareAndSet(user,"conan","aaa");
        System.out.println("userStringAtomicReferenceFieldUpdater = " + user.name);

    }


    static class User{
        public volatile  String name;
        private  int old;

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOld() {
            return old;
        }

        public void setOld(int old) {
            this.old = old;
        }
    }
}
