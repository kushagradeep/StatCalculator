import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class StatisticsImpl implements  Statistics{



    private int min=Integer.MIN_VALUE;
    private int max=Integer.MAX_VALUE;
    private int sum=0;
    private float mean;
    private int eventcount=0;
    private float variance=0f;
    private ArrayList<Element> list=new ArrayList<Element>();


    @Override
    public void event(int value) {
        if(eventcount==0) {
            min = value;
            max = value;
            variance = 0f;
            sum += value;
            eventcount++;
            mean=value;
        }

        else{
            min = min < value ? min : value;
            max = max > value ? max : value;
            mean=sum*1.0f/eventcount;
            sum += value;
            eventcount++;

            float mean2=mean+(value-mean)*1.0f/eventcount;
            /* Knuths method to calculate variance**/
            variance=variance+(value-mean)*(value-mean2);
            variance=variance*1.0f/eventcount-1;

        }


    }

    @Override
    public float mean() {
        return sum*1.0f/eventcount;
    }

    /*
    * @param: this method is used to calculate the mean in last N minutes. We assume that the size of data is not more than 100MB and the CPU and memory are 1 core and 500MB atleast respectively. If the data is huge then we can write to disk and sort the data in parts .*/
    @Override
    public float mean(int lastNMinutes) {
        ArrayList<Element> copy=(ArrayList)list.clone();
        Collections.sort(copy,(a, b)->(int)(a.getTime()-b.getTime()));// sort in reverse chroological order
        long lasttimetoconsider=copy.get(0).getTime()*60-lastNMinutes*60 ; //in seconds
        int c=0;
        int sum=0;
        for(int i=copy.size()-1;i>=0;i--){

            if(copy.get(i).getTime()>=lasttimetoconsider){
               sum+=copy.get(i).getValue();
               c++;

            }
        }
        return sum*1.0f/c;
    }

    @Override
    public float variance() {
        return variance;
    }

    @Override
    public int minimum() {
        return min;
    }

    @Override
    public int maximum() {
        return max;
    }


}
