import java.io.IOException;
import java.util.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
public class Question3 {
  public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, FloatWritable>  {
    
    private Text area = new Text();
    private String val = null;
    public void map(LongWritable key, Text value, OutputCollector<Text, FloatWritable> output, Reporter reporter) throws IOException {
      String line = value.toString();
      String[] myarray = line.split("::");
         val = myarray[0];
         area.set(val);
         FloatWritable wr = new FloatWritable(Float.parseFloat(myarray[5]));
         //System.out.println(myarray[4]);
         //System.out.println(myarray[5]);
         output.collect(area, wr);
    }
  }
  public static class Reduce extends MapReduceBase implements Reducer<Text, FloatWritable, Text, FloatWritable> {
    public void reduce(Text key, Iterator<FloatWritable> values, OutputCollector<Text, FloatWritable> output, Reporter reporter) throws IOException {
      float sum = 0;
      int count=0;
      while (values.hasNext()) {
        sum += values.next().get();
        count++;
      }
      output.collect(key, new FloatWritable(sum/count));
    }
  }

//  public static RunningJob myCustomRunJob(JobConf job) throws Exception {
//	    JobClient jc = new JobClient(job);
//	    RunningJob rj = jc.submitJob(job);
//	    if (!jc.monitorAndPrintJob(job, rj)) {
//	      throw new IOException("Job failed with info: " + rj.getFailureInfo());
//	    }
//	    return rj;
//	  }
  
  public static void main(String[] args) throws Exception {
    JobConf conf = new JobConf(Question3.class);
    conf.setJobName("regionCount");
    conf.setOutputKeyClass(Text.class);
    conf.setOutputValueClass(FloatWritable.class);
    conf.setMapperClass(Map.class);
    conf.setReducerClass(Reduce.class);
    conf.setInputFormat(TextInputFormat.class);
    conf.setOutputFormat(TextOutputFormat.class);
    FileInputFormat.setInputPaths(conf, new Path("/home/rohit/workspace/Bda/input123/"));
    FileOutputFormat.setOutputPath(conf, new Path("/home/rohit/workspace/Bda/outputq3/"));
    JobClient.runJob(conf);
    //myCustomRunJob(conf);
  }
}