import javax.swing.*;
import java.io.*;
import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.concurrent.Executors.newCachedThreadPool;

public class Distributor implements Serializable  {

    @Serial
    private static final long serialVersionUID = 1L;
    private static int journalCount,subscriberCount;
    public  String reportMsg;
    private Hashtable<String,Journal> journals = new Hashtable<>();
    private Vector<Subscriber> subscribers = new Vector<>();

    public boolean isThreadPoolDone;

    public Distributor(){
        subscriberCount = 0;
        journalCount = 0;
        isThreadPoolDone = true;
    }

    public String getReportMsg() {
        return reportMsg;
    }

    public Subscriber searchSubscriber(String name){
        for (Subscriber each: subscribers){
            if(each.getName().equalsIgnoreCase(name))
                return each;
        }
        return null;
    }
    public Journal searchJournal(String issn){
        return journals.getOrDefault(issn, null);
    }
    public boolean addJournal(Journal journal){
        if( !journals.containsKey(journal.getIssn())){
            journals.put(journal.getIssn(),journal);
            return true;
        }
        else{
            System.out.println("This journal already exists!");
            return false;
        }
    }

    public boolean addSubscriber(Subscriber subscriber){
        return subscribers.add(subscriber);
    }

    public boolean addSubscription(String issn,Subscriber subscriber,Subscription subscription){
        if (searchSubscriber(subscriber.getName()) != null && searchJournal(issn) != null){
            searchJournal(issn).addSubscription(subscription);
            return true;
        }
        else return false;
    }

    public AtomicReference<String> listAllSendingOrders(int month, int year){
        AtomicReference<String> o = new AtomicReference<>("");
        journals.forEach((key,value)->{
            for (Subscription innerEach:value.getSubscriptions()){
                // month = month + (12 * (year - innerEach.getDates().getStartYear()));
                // Not allowed , a variable in lambda expression cannot be mutated

                // checking wheter the input date is earlier than subscription date
                boolean cond1 = innerEach.getDates().getStartYear() <= year;
                boolean cond2 = (innerEach.getDates().getStartYear() != year ||
                        innerEach.getDates().getStartMonth() <= month);

                if (
                        (cond1 && cond2) &&
                        innerEach.canSend(month + (12 * (year - innerEach.getDates().getStartYear())))
                        // checking wheter its price is paid ,if so the subscriber and its Journal will be printed out
                ) {
                    o.set(o.get().concat(
                            "# " + innerEach.getCopies() + " Copies of " +
                            "journal '" +value.getName() + '\'' +
                            " will be sent to " + innerEach.getSubscriber().getName()));
                }
            }
        });
        //if ( journalCount == 0) o.set("Couldn't Find Anything to Print :(");
        return o;
    }

    public String listSendingOrders(String issn,int month,int year){
        Journal j = searchJournal(issn) ;
        String output = "";
        if ( j == null) System.out.println("Couldn't find any!");
        else {
            for (Subscription each: j.getSubscriptions()){

                // checking wheter the input date is earlier than subscription date
                boolean cond1 = each.getDates().getStartYear() <= year;
                boolean cond2 = (each.getDates().getStartYear() != year ||
                                 each.getDates().getStartMonth() <= month);

                if (
                   (cond1 && cond2) &&
                    each.canSend(month + (12 * (year - each.getDates().getStartYear())))
                   // checking wheter its price is paid ,if so the subscriber and its Journal will be printed out
                ){
                    output = output.concat(
                            "# " + each.getCopies() + " Copies of " +
                                    "journal '" +j.getName() + '\'' +
                                    " will be sent to " + each.getSubscriber().getName());
                }

            }
        }
        return output;
    }

    public String listIncompletePayments(){
        final String[] output = {""};
        journals.forEach((key,value)->{
            for (Subscription innerEach:value.getSubscriptions()){
                // month = month + (12 * (year - innerEach.getDates().getStartYear()));
                // Not allowed , a variable in lambda expression cannot be mutated
                if (!innerEach.canSend(innerEach.getDates().getEndMonth())){
                    // if its price paid , the subscriber and its Journal will be printed out
                    output[0] = output[0].concat(
                             "#"+" Total/Annual payment of journal " + value.getName()+
                                    " is yet to get paid completely by "+innerEach.getSubscriber().getName());

                }
            }
        });
        return output[0];
    }

    public String listSubscriptions(String input){
        String temp = input.replaceAll("\\d","");
        final String[] result = new String[1];
        result[0] = "";
        if ( !input.equals(temp) ){ // containing number means it's an ISSN
            Journal j = searchJournal(input);
            if(j == null) return null;
            else
                for (Subscription each:j.getSubscriptions()){
                    result[0] = result[0].concat(each + " ");
                }
                return result[0];
        }
        else { // meaning we need to search through with Subscriber Name
            Subscriber s = searchSubscriber(input);
            if(s == null) System.out.println("Couldn't find the Subscriber with this name > "+input);
            journals.forEach((key,value)->{
                for (Subscription innerEach:value.getSubscriptions()){
                    if (innerEach.getSubscriber().equals(s)){
                        result[0] = result[0].concat(String.valueOf(innerEach));
                        break;
                    }
                }
            });
            return result[0];
        }
    }

    public synchronized boolean saveState(String fileName){
        try{
            subscriberCount = subscribers.size();
            journalCount = journals.size();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(journalCount);
            oos.writeObject(subscriberCount);
            journals.forEach((key,value)->{
                try {
                    oos.writeObject(value);
                } catch (IOException e) {
                    System.out.println("Output Stream Exception : writing object "
                            +value.getName() +" "+value.getIssn());
                }
            });
            for (Subscriber each:subscribers){
                oos.writeObject(each);
            }
            oos.close();
            return true;
        }catch (IOException ioException){
            return false;
        }
    }

    public synchronized boolean loadState(String fileName){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
            journals.clear();
            subscribers.clear();
            journalCount= (int) ois.readObject();
            subscriberCount= (int) ois.readObject();
            for(int i = 0; i<journalCount ;i++){
                Journal j = (Journal) ois.readObject();
                journals.put(j.getIssn(),j);
            }
            for (int i=0; i<subscriberCount;i++)
                subscribers.add((Subscriber) ois.readObject());
            ois.close();
            return true;
        }catch (IOException | ClassNotFoundException ioException){
            ioException.printStackTrace();
            return false;
        }
    }


    public synchronized void report() {
        isThreadPoolDone = false;
        ThreadReport t;
        t = new ThreadReport();
        t.start();
        isThreadPoolDone = true;
    }
    private class ThreadReport extends Thread{
        private String outputS = "";
        @Override
        public void run() {
            journals.forEach((key, value)->{
                    String concat = String.valueOf(value);
                    outputS = outputS.concat("#>> "+ concat + "\n");
                    outputS = outputS.concat("# Subscribers of "+value.getName()+"\n");
                    outputS = outputS.concat(value.getSubscriptions().toString() +"\n");
            });
            outputS = outputS.concat("<<< All Subscribers >>>\n");
            for (Subscriber each:subscribers){
                    String concat = String.valueOf(each);
                    outputS = outputS.concat(concat +"\n");
            }
            JOptionPane.showMessageDialog(
                    null,outputS,
                    "Report",JOptionPane.INFORMATION_MESSAGE);
        }

    }


    public void setReportMsg(String currentMsg){
        reportMsg = currentMsg;
    }

}

