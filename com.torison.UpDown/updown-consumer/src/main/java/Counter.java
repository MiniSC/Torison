public class Counter {

        private MyReechantLock myReechantLock = new MyReechantLock();
        private int count = 0;
        public int inc(){
            try {
                myReechantLock.lockInterruptibly();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            this.count++;
            myReechantLock.unlock();
            return count;
        }
}
