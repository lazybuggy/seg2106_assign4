import java.util.Random;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class Nurse implements Runnable{


 Globals vars;
 Patient p;
 Random r = new Random();
  Random rn = new Random();
long time = ManagementFactory.getThreadMXBean().getThreadCpuTime(Thread.currentThread().getId());

DoctorB doctor;


 Nurse(Globals vars)
 {
  
DoctorA docA= new DoctorA(vars);
   this.vars=vars;
   (new Thread(this)).start();
   
   doctor = vars.getDoctor();

 }

 
// public void run() {
//  try {
//     synchronized(this){
//       if (vars.done==true)
//         System.out.println(vars.get().isEmpty())
//            this.wait();      
//     }
//   while(!vars.get().isEmpty()){
//   System.out.println(vars.get().isEmpty());
//
//     p=(Patient)vars.get().dequeue();
//   
//     if (r.nextInt(100) < 15)
//     {
//       vars.getNEA().enqueue(p);
//     }
//     else
//     {
//      vars.getCW().enqueue(p);
//     }
//     try{
//     synchronized(this){
//       this.notify();
//     }}
//     catch (Exception e) {
//        // ...
//    }     
//   }
//  } catch (InterruptedException e) {
//   // TODO Auto-generated catch block
//   e.printStackTrace();
//  }
//  
// }
//
//  
// }
//
//=======
 
 public void run() {
  

   synchronized(doctor){

   
   while(!vars.get().isEmpty()){
   
      int classify = 10 + rn.nextInt(20 - 10 + 1);
      
     p=(Patient)vars.get().dequeue();
   long startTime = System.currentTimeMillis();
   System.out.println("Nurse is classifying..");
     if (r.nextInt(100) < 15)
     {
       
       try {
        
         Thread.sleep(classify*1000);
          vars.getNEA().enqueue(p);
         long timea = System.currentTimeMillis() - startTime;
         System.out.println("Nurse: Classification time nea " + timea + " ms");
       } catch(InterruptedException e) {
        }
         
     }
     else
     {
   
         
            try {
          Thread.sleep(classify*1000);
             vars.getCW().enqueue(p);
            long timea = System.currentTimeMillis() - startTime;
         System.out.println("Nurse: Classification time cw " + timea + " ms" );
          
        } catch(InterruptedException e) {
        }
     }

     System.out.println("merp");
     doctor.notify();

   }
//     
   }
   
  
 }
}

